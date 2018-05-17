package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnChat, R.id.btnDrawBoard, R.id.btnTallyBook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChat:
                startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                break;
            case R.id.btnDrawBoard:
                break;
            case R.id.btnTallyBook:
                break;
        }
    }
}
