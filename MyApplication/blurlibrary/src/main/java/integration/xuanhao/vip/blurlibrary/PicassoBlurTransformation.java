package integration.xuanhao.vip.blurlibrary;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class PicassoBlurTransformation implements Transformation {

    private BlurBitmap blurBitmap;
    public PicassoBlurTransformation(Context mContext) {
        blurBitmap = new BlurBitmap(mContext);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap copy = source.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap blur = blurBitmap.blur(copy);
        copy.recycle();
        source.recycle();
        return blur;
    }

    @Override
    public String key() {
        return "PicassoBlurTransformation";
    }
}
