package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.ImageBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.ipresenter.IHomePresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IHomeView;
import vip.xuanhao.integration.views.activitys.DetailActivity;
import vip.xuanhao.integration.views.adapters.HomeAdapter;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {


    private Context context;
    private List<Integer> integers;
    private List<ImageBean> imageBeans = new ArrayList<>();  //总数据
    private NetManager netManager;
    private HomeAdapter homeAdapter;
    private List<ImageBean> bannerDatas = new ArrayList<>();  //总数据


    final public Integer img[] = {R.mipmap.bunner_01
            , R.mipmap.bunner_02
            , R.mipmap.bunner_03
            , R.mipmap.bunner_04
            , R.mipmap.bunner_05};


    @Inject
    public HomePresenter(Fragment fragment, NetManager netManager) {
        this.context = fragment.getActivity();
        this.netManager = netManager;
    }


    public List<ImageBean> getImageDatas() {
        return imageBeans;
    }

    public List<ImageBean> getBannerDatas() {
        return bannerDatas;
    }


    public void getDataSource() {
        Subscription rxSubscription = netManager
                .getWebApiService()
                .getImageData()
                .flatMap(new Func1<ResponseBody, Observable<String>>() {
                    @Override
                    public Observable<String> call(ResponseBody responseBody) {
                        try {
                            return Observable.just(responseBody.string());
                        } catch (IOException e) {
                            return Observable.error(new IOException(e));
                        }
                    }
                })
                .flatMap(new Func1<String, Observable<Element>>() {
                    @Override
                    public Observable<Element> call(String s) {
                        Document document = Jsoup.parse(s);
                        Element element = document.body();
                        Elements elements = element.getElementsByTag("a");
                        return Observable.from(elements);
                    }
                })
                .filter(new Func1<Element, Boolean>() {
                    @Override
                    public Boolean call(Element element) {
                        return element.getElementsByTag("img").size() != 0;
                    }
                })
                .flatMap(new Func1<Element, Observable<ImageBean>>() {
                    @Override
                    public Observable<ImageBean> call(Element element) {

                        String link = element.attr("href");
                        String url = element.getElementsByTag("img").get(0).attr("src");
                        return Observable.just(new ImageBean(link, url));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Logger.w("doOnCompleted is running");
                        bannerDatas.clear();
                        for (int i = 0; i < 4; i++) {
                            ImageBean imageBean = imageBeans.get(i);
                            bannerDatas.add(imageBean);
                        }
                    }
                })
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onCompleted() {
                        Logger.v("onCompleted is running");
                        view.upDataUI();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        imageBeans.add(imageBean);
                    }
                });

        addSubscriber(rxSubscription);
    }

    public HomeAdapter getHomeAdapter(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(" " + i);
        }
        homeAdapter = new HomeAdapter(context, strings);
        homeAdapter.setiOnRecycleViewItemClickListener(iOnRecycleViewItemClickListener);
        return homeAdapter;
    }

    public void onItemClick(int position) {
        Toast.makeText(context, bannerDatas.get(position).getLink(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void release() {
        if (integers != null) {
            integers.clear();
            integers = null;
        }
        imageBeans.clear();
        imageBeans = null;
        context = null;
    }

}
