package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.PersonalViewModel;
import vip.xuanhao.integration.presenters.ipresenter.IPersonalPresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IPersonalView;
import vip.xuanhao.integration.views.adapters.PersonalAdapter;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class PersonalPresenter extends GodPresenter implements IPersonalPresenter {


    private PersonalAdapter personalAdapter;

    private IPersonalView mIPersonalView;
    private Context mContext;

    @Inject
    public PersonalPresenter(Fragment fragment) {
        if (fragment instanceof IPersonalView)
            this.mIPersonalView = (IPersonalView) fragment;
        this.mContext = fragment.getActivity();
    }

    @Override
    public boolean cleanCache() {
        return false;
    }

    @Override
    public List<PersonalViewModel> getModelData() {
        List<PersonalViewModel> personalViewModels = new ArrayList<>();
        PersonalViewModel post = new PersonalViewModel("我的帖子", R.mipmap.mypost_n, PersonalViewModel.TYPE_JUMP, null);
        PersonalViewModel keep = new PersonalViewModel("我的收藏", R.mipmap.keep_n, PersonalViewModel.TYPE_JUMP, null);
        PersonalViewModel like = new PersonalViewModel("我的喜欢", R.mipmap.like_n, PersonalViewModel.TYPE_JUMP, null);
        PersonalViewModel message = new PersonalViewModel("我的消息", R.mipmap.message_n, PersonalViewModel.TYPE_JUMP, null);
        PersonalViewModel notify = new PersonalViewModel("我的通知", R.mipmap.notify_n, PersonalViewModel.TYPE_JUMP, "1");
        PersonalViewModel cache = new PersonalViewModel("清空缓存", R.mipmap.cache_n, PersonalViewModel.TYPE_UNJUMP, null);
        PersonalViewModel feedback = new PersonalViewModel("意见反馈", R.mipmap.feedback_n, PersonalViewModel.TYPE_JUMP, null);
        PersonalViewModel about = new PersonalViewModel("关于我们", R.mipmap.about_n, PersonalViewModel.TYPE_JUMP, "new");

        personalViewModels.add(post);
        personalViewModels.add(keep);
        personalViewModels.add(like);
        personalViewModels.add(message);
        personalViewModels.add(notify);
        personalViewModels.add(cache);
        personalViewModels.add(feedback);
        personalViewModels.add(about);
        return personalViewModels;
    }


    @Override
    public void jumpPage(int poisiton) {

    }

    @Override
    public String chooseIcon(Context mContext) {
        return null;
    }

    @Override
    public PersonalAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        personalAdapter = new PersonalAdapter(mContext, getModelData());
        personalAdapter.setiOnRecycleViewItemClickListener(iOnRecycleViewItemClickListener);
        return personalAdapter;
    }

    @Override
    public void onItemClick(Context mContext, View view, int position) {
        Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
    }





    @Override
    public void release() {
        personalAdapter = null;
    }
}
