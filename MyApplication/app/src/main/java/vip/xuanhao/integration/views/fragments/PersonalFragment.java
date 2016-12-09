package vip.xuanhao.integration.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.PersonalPresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IPersonalView;
import vip.xuanhao.integration.views.ui.DividerGridItemDecoration;

import static vip.xuanhao.integration.R.id.rclyview_choose;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements IPersonalView, IOnRecycleViewItemClickListener {


    private static final String TAG = PersonalFragment.class.getSimpleName();

    @BindView(R.id.img_personal_icon)
    ImageView imgPersonalIcon;
    @BindView(R.id.tv_personal_nickname)
    TextView tvPersonalNickname;
    @BindView(rclyview_choose)
    RecyclerView recyclerView;

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        recyclerView.setAdapter(presenter.getAdapter(mContext, this));
        Glide.with(mContext)
                .load(R.mipmap.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(mContext))
                .override(70, 70)
                .into(imgPersonalIcon);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void cleanCache() {
        presenter.cleanCache();
    }

    @OnClick(R.id.img_personal_icon)
    public void onClick() {
        presenter.chooseIcon(mContext);
    }


    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(mContext, view, position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
