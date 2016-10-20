package vip.xuanhao.integration.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vip.xuanhao.integration.R;

public class SwipeDeckAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    private LayoutInflater mInflater;

    public SwipeDeckAdapter(Context context, List<String> data) {
        this.data = data;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            // normally use a viewholder
            v = mInflater.inflate(R.layout.swipe_card_item, parent, false);
        }
        //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
        ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
//            Picasso.with(context).load(R.mipmap.guide_bg_02).fit().centerCrop().into(imageView);

        Glide.with(context).load(R.mipmap.guide_bg_02).centerCrop().into(imageView);
        TextView textView = (TextView) v.findViewById(R.id.sample_text);
        String item = (String) getItem(position);
        textView.setText(item);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
                    /*Intent i = new Intent(v.getContext(), BlankActivity.class);
                    v.getContext().startActivity(i);*/
            }
        });
        return v;
    }
}