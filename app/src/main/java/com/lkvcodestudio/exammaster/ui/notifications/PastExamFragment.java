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


public class PastExamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Notification> pastNotifications;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastExamFragment() {
        // Required empty public constructor
    }

    public PastExamFragment(List<Notification> pastNotifications) {
        this.pastNotifications = pastNotifications;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastExamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastExamFragment newInstance(String param1, String param2) {
        PastExamFragment fragment = new PastExamFragment();
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
        View root= inflater.inflate(R.layout.fragment_past_notificaiton, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.pastExamsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        NotificationViewAdapter notificationViewAdapter = new NotificationViewAdapter(pastNotifications);
        recyclerView.setAdapter(notificationViewAdapter);
        return root;
    }
}