package com.bwie.aizhonghui.ri_kaone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        SwipeBackLayout backLayout = getSwipeBackLayout();
        backLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
}
