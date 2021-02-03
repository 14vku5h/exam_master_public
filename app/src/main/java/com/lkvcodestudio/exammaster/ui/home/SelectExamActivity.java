package com.lkvcodestudio.exammaster.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.models.Exam;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectExamActivity extends AppCompatActivity {

    String tag = "Select Exam";
    GoogleSignInAccount signedInAccount;
    FirebaseFirestore fstore;
    @BindView(R.id.loader)
    View examLoader;
    @BindView(R.id.selectExamRecycler)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exam);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Select your exam");
        ButterKnife.bind(this);
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        fstore=FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        loadExams();
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
                            ExamViewAdapter adapter = new ExamViewAdapter(examList,signedInAccount.getId());
                            recyclerView.setAdapter(adapter);
                            examLoader.setVisibility(View.GONE);

                        } else {
                            Log.e(tag, "Error getting exams.", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
         /*   case R.id.addExam:
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void goBack() {
        //super.onBackPressed();
        Intent intent = new Intent();
        //  intent.putExtra("editTextValue", "value_here")
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goBack();
    }
}