package vip.xuanhao.integration.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

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

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, DailyListBean.TopStoriesBean topStoriesBean) {
            ImageLoaderHelper.loadImage(context, topStoriesBean.getImage(), imageView);

        }
    }

}
