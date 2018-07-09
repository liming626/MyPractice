package com.example.administrator.myapplication.activity;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.MyToastUtils;
import com.example.administrator.myapplication.view.MyDrawView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MyDrawBoardActivity extends AppCompatActivity {

    @BindView(R.id.mydraw)
    MyDrawView mydraw;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.image_sign)
    ImageView imageSign;
    private Context context;
    private Bitmap bitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_draw_board);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick({R.id.btn_clear, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                mydraw.clear();
                Glide.clear(imageSign);
                break;
            case R.id.btn_save:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
                permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入", R.drawable.permission_ic_storage));
                permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "读取", R.drawable.permission_ic_storage));
                HiPermission.create(context)
                        .title("申请权限")
                        .msg("亲,请批准这些权限,我才能继续为您服务")
                        .permissions(permissonItems)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i("Permission", "onClose");
                                MyToastUtils.showToast(context, "用户关闭权限申请");
                            }

                            @Override
                            public void onFinish() {
                                Log.i("Permission", "所有权限申请完成");
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                Log.i("Permission", "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                Log.i("Permission", "onGuarantee");
                            }
                        });


                if (mydraw.getTouched()) {
                    if (mydraw.getBitMap() != null) {
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sign.jpg";
                        try {
                            mydraw.save(path);
                        } catch (IOException e) {
                            MyToastUtils.showToast(context, "保存失败");
                            e.printStackTrace();
                        }
                        File file = new File(path);
                        Glide.clear(imageSign);
                        Glide.with(context)
                                .load(file)
                                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                                .placeholder(R.drawable.loading_image)
                                .error(R.drawable.no_image)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(imageSign);
                    } else {
                        MyToastUtils.showToast(context, "获取失败");
                    }
                } else {
                    MyToastUtils.showToast(context, "您还没有签字呢");
                }


                break;
        }
    }
}
