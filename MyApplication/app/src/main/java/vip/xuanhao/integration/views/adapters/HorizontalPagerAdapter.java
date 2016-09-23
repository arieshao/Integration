package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Integer> imageSrc;

    public HorizontalPagerAdapter(final Context context, List<Integer> imageSrc) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.imageSrc = imageSrc;
    }

    @Override
    public int getCount() {

        return imageSrc.isEmpty() && imageSrc == null ? 6 : imageSrc.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.banner_item, container, false);
        BannerViewHolder viewHolder = new BannerViewHolder(view);
        viewHolder.setData(position, imageSrc, mContext);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }


    class BannerViewHolder {
        @BindView(R.id.img_banner_item)
        ImageView imgBannerItem;

        BannerViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(final int position, List<Integer> imageSrc, final Context mContext) {
            Integer imgRes = imageSrc.get(position);
            Glide.with(mContext).load(imgRes).into(imgBannerItem);

            imgBannerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
