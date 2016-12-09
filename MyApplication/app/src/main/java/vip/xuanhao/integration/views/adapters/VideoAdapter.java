package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.ImageBean;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/10/21.
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ImageBean> dataSource;
    private LayoutInflater mInflater;

    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;

    public void setiOnRecycleViewItemClickListener(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }

    public VideoAdapter(Context mContext, List<ImageBean> dataSource) {
        this.mContext = mContext;
        this.dataSource = dataSource;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return dataSource == null && dataSource.isEmpty() ? 0 : dataSource.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.video_content_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder itemHolder = (ViewHolder) holder;
            bandEvent(itemHolder, position);
            bandData(itemHolder, position);
        }
    }

    private void bandData(ViewHolder itemHolder, int position) {

        Glide.with(mContext)
                .load(dataSource.get(position).getUrl())
                .into(itemHolder.imgVideoItem);

    }

    private void bandEvent(ViewHolder itemHolder, final int position) {

        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnRecycleViewItemClickListener.onItemClick(v, position);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_video_item)
        ImageView imgVideoItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
