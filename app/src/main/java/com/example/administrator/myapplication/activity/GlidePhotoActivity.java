package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlidePhotoActivity extends AppCompatActivity {

    @BindView(R.id.btnLoadImg)
    Button btnLoadImg;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.btnLoadGift)
    Button btnLoadGift;
    @BindView(R.id.image_gift)
    ImageView imageGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_photo);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnLoadImg, R.id.btnLoadGift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLoadImg:
                loadImage();
                break;
            case R.id.btnLoadGift:
                loadGift();
                break;
        }
    }

    private void loadGift() {
        String url = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageGift);
    }

    private void loadImage() {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
}
