package vip.xuanhao.integration.presenters;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.PersonalViewModel;
import vip.xuanhao.integration.presenters.ipresenter.IPersonal;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class PersonalPresenter implements IPersonal {


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
    public boolean checkUser() {
        return false;
    }


    @Override
    public void onResume(Context mContext, String pageName) {
        MobclickAgent.onResume(mContext);
        MobclickAgent.onPageStart(pageName);
    }

    @Override
    public void onPause(Context mContext, String pageName) {
        MobclickAgent.onPageEnd(pageName);
        MobclickAgent.onPause(mContext);
    }

    @Override
    public void release() {

    }
}
