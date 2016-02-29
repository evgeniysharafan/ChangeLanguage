package com.evgeniysharafan.changelanguage.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evgeniysharafan.changelanguage.R;
import com.evgeniysharafan.changelanguage.model.Chat;
import com.evgeniysharafan.changelanguage.util.MessageUtils;
import com.evgeniysharafan.utils.L;
import com.evgeniysharafan.utils.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    public interface OnChatClickListener {
        void onChatClick(int position);

        void onChatIconClick(int position);
    }

    private final List<Chat> chats;
    private final OnChatClickListener listener;

    private Transformation roundedTransformation = new CircleTransformation(0, 0);
    private Context context;

    public ChatsAdapter(List<Chat> chats, OnChatClickListener listener) {
        this.chats = chats;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }

        View v = LayoutInflater.from(context).inflate(R.layout.row_chat, parent, false);

        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        L.d("Element " + position + " set.");

        Chat chat = chats.get(position);

        Picasso.with(context)
                .load(R.drawable.ic_test)
                .transform(roundedTransformation)
                .into(holder.icon);
        holder.name.setText(chat.name);
//        holder.message.setText(chat.messages.get(0).text);
        holder.message.setText(R.string.app_name);
        holder.time.setText(MessageUtils.formatTime(context, chat.messages.get(0).timeMillis, false));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.icon)
        ImageButton icon;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.message)
        TextView message;
        @Bind(R.id.time)
        TextView time;

        ViewHolder(View v, OnChatClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);

            setOnClickListener(v, listener);
            setOnClickListener(icon, listener);
        }

        private void setOnClickListener(View v, final OnChatClickListener listener) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        int id = v.getId();
                        if (id == R.id.icon) {
                            listener.onChatIconClick(clickedPosition);
                        } else {
                            listener.onChatClick(clickedPosition);
                        }
                    } else {
                        L.i("clickedPosition == RecyclerView.NO_POSITION");
                    }
                }
            });
        }
    }

}
