package vip.xuanhao.integration.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import vip.xuanhao.integration.app.base.IBaseView;
import vip.xuanhao.integration.presenters.ipresenter.IBasePresenter;

/**
 * Created by Xuanhao on 2016/11/4.
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    public T view;

    private CompositeSubscription compositeSubscription;


    protected void addSubscriber(Subscription s) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(s);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"Thrad1").start();
    }

    private void unSubscriber() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }


    @Override
    public void attach(T t) {
         this.view = t;
    }


    @Override
    public void release() {
        unSubscriber();
    }
}
