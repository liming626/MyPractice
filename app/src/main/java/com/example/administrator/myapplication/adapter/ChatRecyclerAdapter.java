package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.ChatMessageInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/17.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ChatMessageInfo> mData;
    private int mLayoutId;

    // 数据怎么办？ 布局怎么办？ 绑定怎么办？
    public ChatRecyclerAdapter(Context context, List<ChatMessageInfo> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = mInflater.inflate(mLayoutId, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflateView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int type = mData.get(position).getType();
        if(type==1){
            holder.llSend.setVisibility(View.VISIBLE);
            holder.llReceive.setVisibility(View.GONE);
            holder.textSendChatTime.setText(mData.get(position).getTime());
            holder.textSendChatMessage.setText(mData.get(position).getMessage());
        }else if(type==2) {
            holder.llSend.setVisibility(View.GONE);
            holder.llReceive.setVisibility(View.VISIBLE);
            holder.textChatTime.setText(mData.get(position).getTime());
            holder.textChatMessage.setText(mData.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textChatTime;
        TextView textChatMessage;
        TextView textSendChatTime;
        TextView textSendChatMessage;
        LinearLayout llSend;
        LinearLayout llReceive;

        public MyViewHolder(View itemView) {
            super(itemView);
            textChatTime = itemView.findViewById(R.id.text_chatTime);
            textChatMessage = itemView.findViewById(R.id.text_chatMessage);
            textSendChatTime = itemView.findViewById(R.id.text_sendChatTime);
            textSendChatMessage = itemView.findViewById(R.id.text_sendChatMessage);
            llSend = itemView.findViewById(R.id.llSend);
            llReceive = itemView.findViewById(R.id.llReceive);
        }
    }
}


