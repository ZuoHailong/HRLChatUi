package com.hrl.chaui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrl.chaui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZuoHailong on 2020/4/8.
 */
public class OtherActivity extends Activity {
    @BindView(R.id.common_toolbar_back)
    RelativeLayout commonToolbarBack;
    @BindView(R.id.common_toolbar_title)
    TextView commonToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);

        commonToolbarTitle.setText(getIntent().getStringExtra("title"));
        commonToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
