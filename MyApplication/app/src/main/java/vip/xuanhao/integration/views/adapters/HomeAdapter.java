package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/9/26.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENT_VIDEO = 1;
    private static final int TYPE_CONTENT_POST = 2;


    private Context mContext;
    private List<String> strings;
    private LayoutInflater inflater;


    public void setiOnRecycleViewItemClickListener(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }

    public HomeAdapter(Context mContext, List<String> strings) {
        this.strings = strings;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_CONTENT_VIDEO;
        } else {
            return TYPE_CONTENT_POST;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_HEADER:
                View headerView = inflater.inflate(R.layout.layout_home_button, parent, false);
                return new HeaderViewHolder(headerView);
            case TYPE_CONTENT_VIDEO:
                View videoView = inflater.inflate(R.layout.home_referee, parent, false);
                return new VideoViewHolder(videoView);
            case TYPE_CONTENT_POST:
            default:
                View postView = inflater.inflate(R.layout.home_posts, parent, false);
                return new PostViewHolder(postView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_CONTENT_POST) {
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            View itemView = postViewHolder.itemView;
            bindData(itemView, position);
//            bindListener(itemView, position);
        }
    }

//    private void bindListener(View itemView, final int position) {
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iOnRecycleViewItemClickListener.onItemClick(v, position);
//            }
//        });
//    }

    private void bindData(View itemView, int position) {

    }

    @Override
    public int getItemCount() {
        return strings == null && strings.isEmpty() ? 0 : strings.size();
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_01)
        Button btn01;
        @BindView(R.id.btn_02)
        Button btn02;
        @BindView(R.id.btn_03)
        Button btn03;
        @BindView(R.id.btn_04)
        Button btn04;

        HeaderViewHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this, headerView);
        }


        @OnClick({R.id.btn_01,
                R.id.btn_02,
                R.id.btn_03,
                R.id.btn_04})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_01:
                    Toast.makeText(view.getContext(), "1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_02:
                    Toast.makeText(view.getContext(), "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_03:
                    Toast.makeText(view.getContext(), "3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_04:
                    Toast.makeText(view.getContext(), "4", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
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
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.img_home_video_01,
                R.id.img_home_video_02,
                R.id.img_home_video_03,
                R.id.img_home_video_04,
                R.id.img_home_video_more})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_home_video_01:
                    Toast.makeText(view.getContext(), "1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.img_home_video_02:
                    Toast.makeText(view.getContext(), "2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.img_home_video_03:
                    Toast.makeText(view.getContext(), "3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.img_home_video_04:
                    Toast.makeText(view.getContext(), "4", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.img_home_video_more:
                    Toast.makeText(view.getContext(), "More", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
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
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "position" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(v.getContext(), "position" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
