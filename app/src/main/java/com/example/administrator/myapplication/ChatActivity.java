package com.example.administrator.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.ChatRecyclerAdapter;
import com.example.administrator.myapplication.bean.ChatMessageInfo;
import com.example.administrator.myapplication.bean.ChatReceiveInfo;
import com.example.administrator.myapplication.bean.ChatSendInfo;
import com.example.administrator.myapplication.netApi.ChatNetworks;
import com.example.administrator.myapplication.utils.MyDateUtils;
import com.example.administrator.myapplication.utils.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.edtInput)
    EditText edtInput;
    @BindView(R.id.btnSend)
    Button btnSend;
    private Context context;
    private Gson gson;
    private List<ChatMessageInfo> list;
    private ChatRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        gson = new Gson();
        context = this;
        initRecycler();
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new ChatRecyclerAdapter(context, list, R.layout.item_chat_recy);
        recycler.setAdapter(adapter);
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
        String input = edtInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(context, "请输入城市名称", Toast.LENGTH_SHORT).show();
            return;
        }
        edtInput.setText("");
        ChatMessageInfo info = new ChatMessageInfo();
        info.setTime(MyDateUtils.getDateStr());
        info.setMessage(input);
        info.setType(1);
        list.add(info);
        adapter.notifyDataSetChanged();
        recycler.scrollToPosition(adapter.getItemCount()-1);
        ChatNetworks retrofit = RetrofitUtils.createRetrofit(context, ChatNetworks.class);
        Call<ChatReceiveInfo> call = retrofit.sendMessage(input);
        call.enqueue(new Callback<ChatReceiveInfo>() {
            @Override
            public void onResponse(Call<ChatReceiveInfo> call, Response<ChatReceiveInfo> response) {
                if (response.isSuccessful()) {
                    ChatReceiveInfo body = response.body();
                    if (body != null) {
                        int error_code = body.getError_code();
                        if(error_code==0){
                            ChatReceiveInfo.DataBean data = body.getData();
                            ChatMessageInfo info = new ChatMessageInfo();
                            info.setTime(MyDateUtils.getDateStr());
                            List<ChatReceiveInfo.DataBean.NowBean> now = data.getNow();
                            List<ChatReceiveInfo.DataBean.Forecast7Bean> forecast7 = data.getForecast7();
                            ChatReceiveInfo.DataBean.NowBean nowBean = now.get(0);
                            info.setMessage("查询结果"+"\n"
                                    + "温度：" + nowBean.getNow_temperature() + "\n"
                                    + "风向：" + nowBean.getNow_wind_direction() + "\n"
                                    + "湿度：" + nowBean.getNow_humidity() + "\n"
                                    + "气温：" + nowBean.getNow_icomfort() + "\n"
                                    + "空气质量：" + nowBean.getNow_rcomfort() + "\n"
                                    + "风力：" + nowBean.getNow_wind_power() + "\n"
                            );
                            info.setType(2);
                            list.add(info);
                            adapter.notifyDataSetChanged();
                            recycler.scrollToPosition(adapter.getItemCount()-1);
                        }else if(error_code==113){
                            ChatMessageInfo info = new ChatMessageInfo();
                            info.setTime(MyDateUtils.getDateStr());
                            info.setMessage("暂无该城市");
                            info.setType(2);
                            list.add(info);
                            adapter.notifyDataSetChanged();
                            recycler.scrollToPosition(adapter.getItemCount()-1);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ChatReceiveInfo> call, Throwable t) {
                Log.d("response", "onFailure");
            }
        });
    }
}
