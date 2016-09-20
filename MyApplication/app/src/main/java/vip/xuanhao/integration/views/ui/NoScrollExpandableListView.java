package vip.xuanhao.integration.views.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class NoScrollExpandableListView extends ExpandableListView implements OnGroupClickListener {

    public NoScrollExpandableListView(Context context) {
        super(context);
        init();
    }

    public NoScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoScrollExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        setOnGroupClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        return false;
    }
}
