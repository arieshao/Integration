package vip.xuanhao.integration.utils;

import android.support.design.widget.AppBarLayout;

/**
 * Created by Xuanhao on 2016/9/23.
 */

public interface AppBarLayoutHelper {


    abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {


        public static final int STATE_EXPANDED = 1;
        public static final int STATE_COLLAPSED = 2;
        public static final int STATE_IDLE = 3;

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            if (verticalOffset == 0) {
                onOffsetChanged(STATE_EXPANDED);
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                onOffsetChanged(STATE_COLLAPSED);
            } else {
                onOffsetChanged(STATE_IDLE);
            }

        }

        public abstract void onOffsetChanged(int verticalOffset);

    }
}
