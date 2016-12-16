package vip.xuanhao.integration.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.DailyListBean;

/**
 * Created by Xuanhao on 2016/12/2.
 */

public class BannerHelper implements CBViewHolderCreator<BannerHelper.BannerHomeHolder> {

    @Override
    public BannerHomeHolder createHolder() {
        return new BannerHomeHolder();
    }


    class BannerHomeHolder implements Holder<DailyListBean.TopStoriesBean> {

//        private ImageView imageView;

        private ViewHolder viewHolder;

        @Override
        public View createView(Context context) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null, false);
            if (view != null) {
                viewHolder = new ViewHolder(view);
            }
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, DailyListBean.TopStoriesBean topStoriesBean) {
            ImageLoaderHelper.loadImage(context, topStoriesBean.getImage(), viewHolder.imgBannerItem);
            viewHolder.tvBannerDese.setText(topStoriesBean.getTitle());
        }

        class ViewHolder {
            @BindView(R.id.img_banner_item)
            ImageView imgBannerItem;
            @BindView(R.id.tv_banner_dese)
            TextView tvBannerDese;

            public View itemView;

            ViewHolder(View view) {
                this.itemView = view;
                ButterKnife.bind(this, view);
            }


        }
    }

}
