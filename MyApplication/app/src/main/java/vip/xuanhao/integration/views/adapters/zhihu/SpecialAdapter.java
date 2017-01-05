package vip.xuanhao.integration.views.adapters.zhihu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.SectionListBean;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class SpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<SectionListBean.DataBean> mList;
    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;

    public SpecialAdapter(Context mContext, List<SectionListBean.DataBean> mList, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.inflater = LayoutInflater.from(mContext);
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_theme, parent, false);
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
        SectionListBean.DataBean dataBean = mList.get(position);
        ImageLoaderHelper.loadImage(mContext, dataBean.getThumbnail(), viewHolder.imgThemeItem);
        viewHolder.tvThemeItem.setText(dataBean.getName());
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }


    class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_theme_item)
        ImageView imgThemeItem;
        @BindView(R.id.tv_theme_item)
        TextView tvThemeItem;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

