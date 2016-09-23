package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

/**
 * Created by Xuanhao on 2016/9/23.
 */

public class HomeContentListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> strings;
    private LayoutInflater mInflater;

    public HomeContentListAdapter(Context mContext, List<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return strings == null && strings.isEmpty() ? 0 : strings.size();

    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_referee, parent, false);
            viewHolder = new ViewHolder(convertView);
            viewHolder.bindData(strings.get(position));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_home_video_01)
        ImageView imgHomeVideo01;
        @BindView(R.id.img_home_video_02)
        ImageView imgHomeVideo02;
        @BindView(R.id.img_home_video_03)
        ImageView imgHomeVideo03;
        @BindView(R.id.img_home_video_04)
        ImageView imgHomeVideo04;
        @BindView(R.id.img_home_video_more)
        TextView imgHomeVideoMore;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindData(String strings) {

        }
    }
}
