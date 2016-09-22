package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class GuideAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private List<Integer> integers;

    private Context mContext;

    public GuideAdapter(Context mContext, List<Integer> integers) {
        this.integers = integers;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return integers == null && integers.isEmpty() ? 0 : integers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.item_guide, container, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bindData(mContext, position, integers);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    class ViewHolder {
        @BindView(R.id.img_guide_item)
        ImageView imgGuideItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindData(Context mContext, int pos, List<Integer> integers) {

            Glide.with(mContext).load(integers.get(pos)).into(imgGuideItem);
        }
    }
}
