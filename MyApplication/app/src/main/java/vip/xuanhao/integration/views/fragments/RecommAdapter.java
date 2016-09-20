package vip.xuanhao.integration.views.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;


/**
 * Created by Xuanhao on 2016/8/2.
 */

public class RecommAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_ITEM = 0x0001;
    private static final int TYPE_HEADER = 0x0002;
    private static final int TYPE_FOOTER = 0x0003;

    private final List<View> mHeaders = new ArrayList<>();
    private final List<View> mFooters = new ArrayList<>();
    private Context mContext;

    private LayoutInflater mInflater;

    private List<String> dataSource;

    private onItemClickListener onItemClickListener;


    public void setHeaderView(@NotNull View headerView) {

        if (headerView == null)
            throw new IllegalArgumentException("You can't have a null header!");

        if (!mHeaders.contains(headerView))
            mHeaders.add(headerView);
    }

    public View getHeaderView() {
        return !mHeaders.isEmpty() ? mHeaders.get(0) : null;
    }


    public void setFooterView(@NotNull View footerView) {

        if (footerView == null)
            throw new IllegalArgumentException("You can't have a null footer!");

        if (!mFooters.contains(footerView))
            mFooters.add(footerView);
    }

    public View getFooterView() {
        return !mFooters.isEmpty() ? mFooters.get(0) : null;
    }


    public void setHeaderViewVisibility(boolean shouldShow) {
        for (View header : mHeaders) {
            header.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }

    public void setFooterViewVisibility(boolean shouldShow) {
        for (View footer : mFooters) {
            footer.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }


    public void setOnItemClickListener(@NotNull RecommAdapter.onItemClickListener onItemClickListener) {
        if (onItemClickListener == null)
            throw new IllegalArgumentException("The onItemClickListener must not null !");
        this.onItemClickListener = onItemClickListener;
    }


    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public RecommAdapter(Context mContext, List<String> dataSource) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.dataSource = dataSource;
    }

    @Override
    public int getItemCount() {
        if (!dataSource.isEmpty() && dataSource != null) {
            return dataSource.size() + mHeaders.size() + mFooters.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
//        if (!mHeaders.isEmpty() && position == 0) //第一条是headerView
//            return TYPE_HEADER;
//        if (!mFooters.isEmpty() && position == getItemCount() - 1) //最后一条是footerView
//            return TYPE_FOOTER;
//        return TYPE_ITEM;


        if (getItemCount() - dataSource.size() == 2) { //header  footer 都不为空
            if (position == 0)
                return TYPE_HEADER;
            if (position == dataSource.size() + 1)
                return TYPE_FOOTER;
            return TYPE_ITEM;
        }
        if (getItemCount() - dataSource.size() == 1) {
            if (!mHeaders.isEmpty()) { //没有footerview
                if (position == 0)
                    return TYPE_HEADER;
            } else if (position == dataSource.size() + 1) { //没有HeaderView
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }
        return TYPE_ITEM;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                View itemView = mInflater.inflate(R.layout.recom_cardview_item_test, parent, false);
                return new ItemViewHolder(itemView);
            case TYPE_HEADER:
                return new HeaderViewHolder(getHeaderView());
            case TYPE_FOOTER:
                return new FooterViewHolder(getFooterView());
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            ItemOnClick(itemViewHolder, position - mHeaders.size());
            BindItemData(itemViewHolder, position - mHeaders.size());
        }
    }

    private void BindItemData(ItemViewHolder holder, int position) {
        holder.tvTest.setText(dataSource.get(position));
    }

    private void ItemOnClick(ItemViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_test)
        TextView tvTest;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
