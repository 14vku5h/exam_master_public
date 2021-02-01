package com.lkvcodestudio.exammaster.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lkvcodestudio.exammaster.R;

import java.util.List;


public class UpcomingExamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Notification> upcomingNotifications;

    public UpcomingExamFragment() {
        // Required empty public constructor
    }
    public UpcomingExamFragment(List<Notification> upcomingNotifications){
        this.upcomingNotifications = upcomingNotifications;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingExamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingExamFragment newInstance(String param1, String param2) {
        UpcomingExamFragment fragment = new UpcomingExamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_upcoming_notification, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.upcomingExamsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        NotificationViewAdapter notificationViewAdapter = new NotificationViewAdapter(upcomingNotifications);
        recyclerView.setAdapter(notificationViewAdapter);

        return root;
    }
}