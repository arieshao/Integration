package vip.xuanhao.integration.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vip.xuanhao.integration.R;

public class launchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
