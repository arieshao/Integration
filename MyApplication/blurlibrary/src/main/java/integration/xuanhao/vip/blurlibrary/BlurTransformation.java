package integration.xuanhao.vip.blurlibrary;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class BlurTransformation extends BitmapTransformation {


    private BlurBitmap blurBitmap;

    public BlurTransformation(Context context) {
        super(context);
        blurBitmap = new BlurBitmap(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

        Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap blur = blurBitmap.blur(blurredBitmap);

//        RenderScript rs = RenderScript.create(GuideActivity.this);
//
//                        Allocation input = Allocation.createFromBitmap(
//                                rs,
//                                blurredBitmap,
//                                Allocation.MipmapControl.MIPMAP_FULL,
//                                Allocation.USAGE_SHARED
//                        );
//                        Allocation output = Allocation.createTyped(rs, input.getType());
//                        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//                        script.setInput(input);
//                        // Set the blur radius
//                        script.setRadius(25);
//                        // Start the ScriptIntrinisicBlur
//                        script.forEach(output);
//                        // Copy the output to the blurred bitmap
//                        output.copyTo(blurredBitmap);
        blurredBitmap.recycle();
        toTransform.recycle();
        return blur;
    }

    @Override
    public String getId() {
        return "BlurTransformation";
    }
}
