package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

/**
 * Created by Xuanhao on 2016/8/11.
 */

public class ThereAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> dataSource;
    private Context mContext;

    private static final int ITEM_CONTENT = 1;
    private static final int ITEM_FOOTER = 2;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ThereAdapter(Context mContext, List<String> dataSource) {
        this.mContext = mContext;
        this.dataSource = dataSource;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return ITEM_FOOTER;
        } else {
            return ITEM_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_FOOTER:
                View footerView = mInflater.inflate(R.layout.footerview_test, parent, false);
                return new FooterHolder(footerView);
            case ITEM_CONTENT:
            default:
                View contentView = mInflater.inflate(R.layout.recom_cardview_item_test, parent, false);
                return new ContentHolder(contentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_CONTENT) {
            ContentHolder contentHolder = (ContentHolder) holder;
            bindDataSource(contentHolder, position);
            setListener(contentHolder, position);
        }
    }

    private void bindDataSource(ContentHolder contentHolder, int position) {
        contentHolder.tvTest.setText(dataSource.get(position));
    }

    private void setListener(ContentHolder contentHolder, final int position) {
        contentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size() + 1;
    }


    class ContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_test)
        TextView tvTest;

        ContentHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_test)
        TextView tvTest;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
