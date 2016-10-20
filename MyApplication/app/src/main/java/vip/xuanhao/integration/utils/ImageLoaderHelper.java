package vip.xuanhao.integration.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by Xuanhao on 2016/9/22.
 * <p>
 * 图片加载助手.方便更换 图片加载框架
 */

public class ImageLoaderHelper {


    public static void loadImage(@NotNull Context mContext, @NotNull int resource, @NotNull ImageView view) {
        Glide.with(mContext).load(resource).into(view);
    }

    public static void loadImage(@NotNull Context mContext, @NotNull int resource, @NotNull ImageView view, BitmapTransformation transformation) {
        Glide.with(mContext).load(resource).transform(transformation).into(view);
    }


    public static void loadImage(@NotNull Context mContext, @NotNull File imageFile, @NotNull ImageView view) {

    }
}
