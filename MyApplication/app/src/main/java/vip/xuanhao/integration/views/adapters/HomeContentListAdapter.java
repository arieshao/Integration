package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

/**
 * Created by Xuanhao on 2016/9/23.
 */

public class HomeContentListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> strings;
    private LayoutInflater mInflater;
    private static final int TYPE_VIDEO = 1;
    private static final int TYPE_NORMAL = 2;

    private int itemType;


    public HomeContentListAdapter(Context mContext, List<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return strings == null && strings.isEmpty() ? 0 : strings.size();

    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_VIDEO;
//        else
//            return TYPE_NORMAL;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoViewHolder videoViewHolder;
        PostViewHolder postViewHolder;
//        itemType = getItemViewType(position);
//        switch (itemType) {
//            case TYPE_VIDEO:
//                if (convertView == null) {
//                    convertView = mInflater.inflate(R.layout.home_referee, null, false);
//                    videoViewHolder = new VideoViewHolder(convertView);
//                    convertView.setTag(videoViewHolder);
//                } else {
//                    videoViewHolder = (VideoViewHolder) convertView.getTag();
//                }
//                break;
//            case TYPE_NORMAL:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.home_posts, parent, false);
                    postViewHolder = new PostViewHolder(convertView);
                    convertView.setTag(postViewHolder);
                } else {
                    postViewHolder = (PostViewHolder) convertView.getTag();
                }
//                break;
//        }
        return convertView;
    }

    static class VideoViewHolder {
        @BindView(R.id.img_home_video_01)
        ImageView imgHomeVideo01;
        @BindView(R.id.img_home_video_02)
        ImageView imgHomeVideo02;
        @BindView(R.id.img_home_video_03)
        ImageView imgHomeVideo03;
        @BindView(R.id.img_home_video_04)
        ImageView imgHomeVideo04;
        @BindView(R.id.img_home_video_more)
        TextView imgHomeVideoMore;

        VideoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindData(String strings) {

        }
    }

    static class PostViewHolder {
        @BindView(R.id.tv_home_post_title)
        TextView tvHomePostTitle;
        @BindView(R.id.tv_home_post_type)
        TextView tvHomePostType;
        @BindView(R.id.img_posts_01)
        ImageView imgPosts01;
        @BindView(R.id.tv_posts_readcount)
        TextView tvPostsReadcount;
        @BindView(R.id.img_posts_02)
        ImageView imgPosts02;
        @BindView(R.id.img_posts_03)
        ImageView imgPosts03;

        PostViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindData(String strings) {

        }
    }
}
