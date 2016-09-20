package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.PersonalViewModel;
import vip.xuanhao.integration.utils.ScreenUtils;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class PersonalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;
    private List<PersonalViewModel> personalViewModels;
    private Context mContext;

    private LayoutInflater mInflater;
    private int width;


    public void setiOnRecycleViewItemClickListener(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }

    public PersonalAdapter(Context mContext, List<PersonalViewModel> personalViewModels) {
        this.personalViewModels = personalViewModels;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

        width = ScreenUtils.getScreenWidth(mContext) / 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_personal, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            bindData(itemHolder, position);
            setListener(itemHolder, position);
        }
    }

    private void setListener(ItemViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnRecycleViewItemClickListener.onItemClick(v, position);
            }
        });
    }

    private void bindData(ItemViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = width;
        layoutParams.width = width;
        holder.itemView.setLayoutParams(layoutParams);

        PersonalViewModel personalViewModel = personalViewModels.get(position);


        Picasso.with(mContext)
                .load(personalViewModel.getItemDrawable())
                .resize(80, 80)
                .centerCrop()
                .into(holder.imgItemPersonal);
        holder.tvItemPersonal.setText(personalViewModel.getItemName());
    }

    @Override
    public int getItemCount() {
        return personalViewModels != null && !personalViewModels.isEmpty() ? personalViewModels.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_item_personal)
        ImageView imgItemPersonal;
        @BindView(R.id.tv_item_personal)
        TextView tvItemPersonal;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.tv_item_notify)
        TextView tvItemNotify;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
