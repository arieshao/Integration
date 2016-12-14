package vip.xuanhao.integration.views.adapters.zhihu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.HotListBean;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater mInflater;
    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;
    private List<HotListBean.RecentBean> mList;

    public HotAdapter(Context mContext, List<HotListBean.RecentBean> mList, IOnRecycleViewItemClickListener onclick) {

        Logger.w("HotAdapter  is created");
        this.mContext = mContext;
        this.mList = mList;
        this.iOnRecycleViewItemClickListener = onclick;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_dailycontent, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            bindData(viewHolder, position);
            bindEvent(viewHolder, position);
        }
    }

    private void bindEvent(ContentViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnRecycleViewItemClickListener.onItemClick(v, position);
            }
        });
    }

    private void bindData(ContentViewHolder viewHolder, int position) {
        HotListBean.RecentBean recentBean = mList.get(position);
        viewHolder.tvDailyItem.setText(recentBean.getTitle());
        ImageLoaderHelper.loadImage(mContext, recentBean.getThumbnail(), viewHolder.imgDailyItem);
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_daily_item)
        ImageView imgDailyItem;
        @BindView(R.id.tv_daily_item)
        TextView tvDailyItem;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

