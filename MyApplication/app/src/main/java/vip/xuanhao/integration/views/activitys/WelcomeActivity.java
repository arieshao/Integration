package vip.xuanhao.integration.views.activitys;

import android.widget.TextView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.WelcomePresenter;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IWelcomeView;

/**
 * Created by Xuanhao on 2016/11/28.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {


    @BindView(R.id.tv_wel_countdown)
    TextView tv_countDown;

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    protected void setStatusBar() {
//        StatusBarUtil.setTranslucentForImageView(this, 0, null);
//        StatusBarUtil.setTranslucent(this, 0);
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        // AD or other initialized  eg: tinker  hotfix  and so on
    }

    @Override
    public void initData() {
        presenter.setCountDown(3);

    }

    @Override
    public void initEvent() {
        presenter.jumpPage();
    }

    @Override
    public void updateCountDown(String countDown) {
        tv_countDown.setText(countDown);
    }
}
