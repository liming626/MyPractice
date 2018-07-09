package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnChat)
    Button btnChat;
    @BindView(R.id.btnDrawBoard)
    Button btnDrawBoard;
    @BindView(R.id.btnTallyBook)
    Button btnTallyBook;
    @BindView(R.id.btnGlide)
    Button btnGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnChat, R.id.btnDrawBoard, R.id.btnTallyBook,R.id.btnGlide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChat:
                startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                break;
            case R.id.btnDrawBoard:
                startActivity(new Intent(getApplicationContext(), MyDrawBoardActivity.class));
                break;
            case R.id.btnTallyBook:
                break;
            case R.id.btnGlide:
                startActivity(new Intent(getApplicationContext(), GlidePhotoActivity.class));
                break;
        }
    }
}
