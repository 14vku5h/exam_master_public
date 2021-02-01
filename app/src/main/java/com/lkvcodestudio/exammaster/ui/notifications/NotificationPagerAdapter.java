package com.lkvcodestudio.exammaster.ui.notifications;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationPagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    private List<Notification> notificationList,
            upComingNotifications =new ArrayList<>(), pastNotifications =new ArrayList<>();
    public NotificationPagerAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs, List<Notification> notificationList) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
        this.notificationList = notificationList;
        filterExams();
    }

    private void filterExams() {
        upComingNotifications.clear();
        pastNotifications.clear();
        Notification e;
        for(int i = 0; i< notificationList.size(); i++) {
            e = notificationList.get(i);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(e.getDateTime().isAfter(LocalDateTime.now())){
                    upComingNotifications.add(e);
                }else{
                    pastNotifications.add(e);
                }
            }
        }

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new UpcomingExamFragment(upComingNotifications);
            case 1:
                return new PastExamFragment(pastNotifications);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
