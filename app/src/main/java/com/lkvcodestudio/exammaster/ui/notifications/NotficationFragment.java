package com.lkvcodestudio.exammaster.ui.notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotficationFragment extends Fragment {
    FirebaseFirestore fstore;
    GoogleSignInAccount signedInAccount;
    String TAG = "Notification";

    List<Notification> notificationList = new ArrayList<>();
    RecyclerView examListRecycler;
    @BindView(R.id.addExamBtn)
    View addExamBtn;
    @BindView(R.id.examLoader)
    View examLoader;
    @BindView(R.id.examsTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.examsViewPager)
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, root);
        fstore = FirebaseFirestore.getInstance();
        signedInAccount = GoogleSignIn.getLastSignedInAccount(root.getContext());
        addExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), AddNotificationActivity.class), 6969);
            }
        });
        loadExams();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6969) {
            if (resultCode == Activity.RESULT_OK) {
                loadExams();
            }
        }
    }

    private void loadExams() {
        examLoader.setVisibility(View.VISIBLE);
        List<Notification> notificationList = new ArrayList<>();
        CollectionReference ref = fstore.collection("users/" + signedInAccount.getId() + "/notifications");
        ref.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            notificationList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.get("name") != null ? document.get("name").toString() : "";
                                String subTitle = document.get("subtitle") != null ? document.get("subtitle").toString() : "";
                                String dateTime = document.get("dateTime") != null ? document.get("dateTime").toString() : "";
                                AlertType alertType = document.get("alertType") != null ? AlertType.valueOf(document.get("alertType").toString()) : AlertType.DEFAULT;
                                notificationList.add(new Notification(document.getId(),
                                        name,
                                        subTitle,
                                        Utils.convertStrToDateTime(dateTime, Utils.displayFormat),
                                        alertType));
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                            }


                            // Collections.sort(jokeList);
                            //  ExamViewAdapter adapter = new ExamViewAdapter(examList);
                            // examListRecycler.setAdapter(adapter);

                            NotificationPagerAdapter notificationPagerAdapter = new NotificationPagerAdapter(getChildFragmentManager(), 1, tabLayout.getTabCount(), notificationList);
                            viewPager.setAdapter(notificationPagerAdapter);
                            examLoader.setVisibility(View.GONE);

                        } else {
                            Log.e(TAG, "Error getting exams.", task.getException());
                        }
                    }
                });
    }
}