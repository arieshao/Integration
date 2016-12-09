package vip.xuanhao.integration.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import vip.xuanhao.integration.utils.PreferenceHelper;
import vip.xuanhao.integration.views.Iviews.IWelcomeView;
import vip.xuanhao.integration.views.activitys.GuideActivity;
import vip.xuanhao.integration.views.activitys.MainActivity;

/**
 * Created by Xuanhao on 2016/11/28.
 */

public class WelcomePresenter extends BasePresenter<IWelcomeView> {

    private PreferenceHelper preferenceHelper;
    private Context mContext;


    @Inject
    public WelcomePresenter(Activity activity, PreferenceHelper preferenceHelper) {
        mContext = activity;
        this.preferenceHelper = preferenceHelper;
    }

    public void jumpPage() {
        final Intent intent = new Intent();
        Observable.just(preferenceHelper.isFirstInstallApp())
                .delay(3 * 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean)
                            intent.setClass(mContext, GuideActivity.class);
                        else
                            intent.setClass(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                    }
                });
    }



    Long countStart = 3l;

    Long reverse;

    /**
     * 倒计时
     * @param field
     */
    public void setCountDown(int field) {
        Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take(field)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return reverse = countStart - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        view.updateCountDown(aLong.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.w(throwable.toString());
                    }
                });

    }
}
