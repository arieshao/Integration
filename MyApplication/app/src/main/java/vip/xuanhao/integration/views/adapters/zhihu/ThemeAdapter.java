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
import vip.xuanhao.integration.model.domain.ThemeListBean;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ThemeListBean.OthersBean> mList;// = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;


    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;

    public ThemeAdapter(Context mContext, List<ThemeListBean.OthersBean> mList , IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }


    public void setiOnRecycleViewItemClickListener(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_theme, parent, false);
        return new ThemeContentHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ThemeContentHolder) {
            ThemeContentHolder contentHolder = (ThemeContentHolder) holder;
            bindData(contentHolder, position);
            bindEvent(contentHolder, position);
        }
    }

    private void bindEvent(ThemeContentHolder contentHolder, final int position) {
        contentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iOnRecycleViewItemClickListener != null) {
                    iOnRecycleViewItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    private void bindData(ThemeContentHolder contentHolder, int position) {
        ThemeListBean.OthersBean othersBean = mList.get(position);
        contentHolder.tvThemeItem.setText(othersBean.getName());
        ImageLoaderHelper.loadImage(mContext,othersBean.getThumbnail(),contentHolder.imgThemeItem);
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }

    class ThemeContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_theme_item)
        ImageView imgThemeItem;
        @BindView(R.id.tv_theme_item)
        TextView tvThemeItem;
        public ThemeContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
