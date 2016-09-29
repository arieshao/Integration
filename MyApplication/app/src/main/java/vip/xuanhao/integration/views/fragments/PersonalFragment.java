package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.BaseApplication;
import vip.xuanhao.integration.presenters.PersonalPresenter;
import vip.xuanhao.integration.presenters.ipresenter.IPersonal;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IPersonalView;
import vip.xuanhao.integration.views.ui.DividerGridItemDecoration;

import static vip.xuanhao.integration.R.id.rclyview_choose;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class PersonalFragment extends BaseFragment implements IPersonalView, IOnRecycleViewItemClickListener {


    private static final String TAG = PersonalFragment.class.getSimpleName();

    @BindView(R.id.img_personal_icon)
    ImageView imgPersonalIcon;
    @BindView(R.id.tv_personal_nickname)
    TextView tvPersonalNickname;
    @BindView(rclyview_choose)
    RecyclerView recyclerView;

    private IPersonal iPersonal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, personalRootView);
        return personalRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iPersonal = new PersonalPresenter();
        initView();

    }


    @Override
    public void onResume() {
        super.onResume();
        iPersonal.onResume(mContext, TAG);
    }


    @Override
    public void onPause() {
        super.onPause();
        iPersonal.onPause(mContext, TAG);
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        recyclerView.setAdapter(iPersonal.getAdapter(mContext, this));
        Glide.with(mContext)
                .load(R.mipmap.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(mContext))
                .override(70, 70)
                .into(imgPersonalIcon);

//        ImageLoaderHelper.loadImage(mContext, R.mipmap.ic_launcher, imgPersonalIcon, new CropCircleTransformation(mContext));
    }

    @Override
    public void cleanCache() {
        iPersonal.cleanCache();
    }

    @OnClick(R.id.img_personal_icon)
    public void onClick() {
        iPersonal.chooseIcon(mContext);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPersonal.release();
        iPersonal = null;
        RefWatcher refWatcher = BaseApplication.refWatcher(getActivity());
        refWatcher.watch(this);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
    }
}
