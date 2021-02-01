package com.lkvcodestudio.exammaster.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lkvcodestudio.exammaster.R;

public class AlertTypeViewAdapter extends RecyclerView.Adapter<AlertTypeViewAdapter.AlertTypeViewHolder> {

    private AlertType[]  alertTypes = AlertType.values();


    @NonNull
    @Override
    public AlertTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_type, parent, false);
        AlertTypeViewHolder holder = new AlertTypeViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlertTypeViewHolder holder, int position) {
        final AlertType type = alertTypes[position];
        TextView textView = holder.getTitleView();
        textView.setText(type.getValue());
        View v  = holder.itemView;
        v.setTag(type.name());
        v.findViewById(R.id.typeContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals(AlertType.DEFAULT)){
                    AddNotificationActivity.NOTIFICATION.setAlertType(AlertType.ONE_DAY_BEFORE);
                }else {
                    AddNotificationActivity.NOTIFICATION.setAlertType(type);
                }
                AddNotificationActivity.typeSelDialog.dismiss();
                AddNotificationActivity.alertText.setText(type.getValue());
            }
        });
        if(AddNotificationActivity.NOTIFICATION.getAlertType().equals(type)){
            holder.getCheckBtn().setVisibility(View.VISIBLE);
        }else{
            holder.getCheckBtn().setVisibility(View.GONE);
        }
        if(AddNotificationActivity.NOTIFICATION.getAlertType().equals(AlertType.ONE_DAY_BEFORE) && type.equals(AlertType.DEFAULT)){
            holder.getCheckBtn().setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return alertTypes.length;
    }

    public class AlertTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView ;
        private View checkBtn;
        public AlertTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            checkBtn= itemView.findViewById(R.id.checkBtn);
        }
        public TextView getTitleView() {
            return titleView;
        }

        public View getCheckBtn() {
            return checkBtn;
        }
    }
}
