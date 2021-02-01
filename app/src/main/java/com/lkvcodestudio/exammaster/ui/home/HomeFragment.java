package com.lkvcodestudio.exammaster.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.models.Exam;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {


    private String TAG="Home Fragment";
    FirebaseFirestore fstore;
    @BindView(R.id.examRecyclerView)
    RecyclerView examListRecycler;
    @BindView(R.id.addExamBtn)
    View addExamBtn;
    @BindView(R.id.loader)
    View examLoader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        fstore = FirebaseFirestore.getInstance();

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        AppCompatActivity activity  = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) root.findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getString(R.string.app_name));
        examListRecycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        loadExams();
        addExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SelectExamActivity.class);
                startActivityForResult(i, 1111);
            }
        });

        return root;
    }
    private void loadExams() {
        examLoader.setVisibility(View.VISIBLE);
        List<Exam> examList = new ArrayList<>();
        CollectionReference ref = fstore.collection("/exams");
        ref.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            examList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.get("name") != null ? document.get("name").toString() : "";
                                String id = document.getId();
                                examList.add(new Exam(name,id));
                                //  Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            // Collections.sort(jokeList);
                            ExamViewAdapter adapter = new ExamViewAdapter(HomeFragment.this,examList);
                            examListRecycler.setAdapter(adapter);
                            examLoader.setVisibility(View.GONE);

                        } else {
                            Log.e(TAG, "Error getting exams.", task.getException());
                        }
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111) {
            if (resultCode == Activity.RESULT_OK) {
                loadExams();
            }
        }
    }

}