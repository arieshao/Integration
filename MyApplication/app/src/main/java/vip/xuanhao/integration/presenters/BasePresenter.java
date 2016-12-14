package vip.xuanhao.integration.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import vip.xuanhao.integration.presenters.ipresenter.IBasePresenter;
import vip.xuanhao.integration.views.IBase;

/**
 * Created by Xuanhao on 2016/11/4.
 */

public class BasePresenter<T extends IBase> implements IBasePresenter<T> {

    public T view;

    private CompositeSubscription compositeSubscription;


    protected void addSubscriber(Subscription s) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(s);
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
