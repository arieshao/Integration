package integration.xuanhao.vip.testdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Xuanhao on 2016/12/2.
 * 嵌套viewpager 内部可以滑动的
 */

public class ParentViewPager extends ViewPager {

    public ParentViewPager(Context context) {
        super(context);
    }

    public ParentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean controlScroll = true;// defult is true


    public void setControlScroll(boolean controlScroll) {
        this.controlScroll = controlScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (controlScroll)
            return super.onInterceptTouchEvent(ev);
        else
            return controlScroll;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (controlScroll)
            return super.onTouchEvent(ev);
        else
            return controlScroll;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v != this && v instanceof ViewPager) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}
