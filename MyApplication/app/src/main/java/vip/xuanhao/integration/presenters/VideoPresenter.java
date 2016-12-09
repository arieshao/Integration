package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import vip.xuanhao.integration.model.domain.ImageBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.ipresenter.IVideoPresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IVideoView;
import vip.xuanhao.integration.views.adapters.VideoAdapter;

/**
 * Created by Xuanhao on 2016/9/28.
 */

public class VideoPresenter extends BasePresenter <IVideoView> implements IVideoPresenter {

    private VideoAdapter mAdapter;
    private Context mContext;
    private NetManager netManager;
    private List<ImageBean> imageBeen = new ArrayList<>();


    @Inject
    public VideoPresenter(Fragment fragment, NetManager netManager) {
        this.mContext = fragment.getActivity();
        this.netManager = netManager;
    }

    @Override
    public void getDataSource() {
        getHtmlImageData();

    }


    public void getHtmlImageData() {
        netManager
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
//                        Logger.w(element.toString());
                        return element.getElementsByTag("img").size() != 0;
                    }
                })
                .flatMap(new Func1<Element, Observable<List<ImageBean>>>() {
                    @Override
                    public Observable<List<ImageBean>> call(Element element) {

                        String link = element.attr("href");
                        String url = element.getElementsByTag("img").get(0).attr("src");
                        imageBeen.add(new ImageBean(link, url));
                        return Observable.just(imageBeen);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ImageBean> imageBeen) {
                        view.updateUI();
                    }
                });

    }

    @Override
    public VideoAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new VideoAdapter(mContext, imageBeen);
        }
        return mAdapter;
    }


    public void UpdateUI() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setListener(IOnRecycleViewItemClickListener listener) {
        mAdapter.setiOnRecycleViewItemClickListener(listener);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(mContext, imageBeen.get(position).getLink(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void release() {
        super.release();
        imageBeen.clear();
        imageBeen = null;
    }
}
