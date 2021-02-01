package com.lkvcodestudio.exammaster.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.Utils;

import java.util.List;

public class NotificationViewAdapter extends RecyclerView.Adapter<NotificationViewAdapter.NotificationViewHolder> {
    List<Notification> notificationList;

    public NotificationViewAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        NotificationViewHolder holder = new NotificationViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.notName.setText(notification.getName());
        holder.subtitle.setText(notification.getSubtitle());
        holder.dateTime.setText(Utils.convertDateTimeToStr(notification.getDateTime(),Utils.displayFormat));

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public TextView notName,subtitle,dayCount,dayMSg,dateTime;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notName=itemView.findViewById(R.id.notName);
            subtitle=itemView.findViewById(R.id.notSubtitle);
            dayCount=itemView.findViewById(R.id.dayCount);
            dayMSg=itemView.findViewById(R.id.dayMsg);
            dateTime=itemView.findViewById(R.id.dateTime);
        }
    }
}
