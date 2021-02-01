package com.lkvcodestudio.exammaster.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.models.Exam;

import java.io.Serializable;
import java.util.List;


public class ExamViewAdapter extends RecyclerView.Adapter<ExamViewAdapter.ExamViewHolder> {
    List<Exam> examList;
    Fragment fragment;
    FirebaseFirestore firestore;

    public ExamViewAdapter(Fragment fragment, List<Exam> examList) {
        this.fragment=fragment;
        this.examList = examList;
        this.firestore= FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam,parent,false);

       ExamViewHolder holder = new ExamViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.examName.setText(exam.getName());
      /*  holder.examName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SubjectActivity.class);
                intent.putExtra("exam", (Serializable) exam);
                v.getContext().startActivity(intent);
            }
        });*/
      /*  holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), holder.options);
                popup.inflate(R.menu.exam_optionmenu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.editExam:
                                //right your action here
                                Intent editIntent = new Intent(v.getContext(), AddExamActivity.class);
                                editIntent.putExtra("exam",exam);
                                fragment.startActivityForResult(editIntent,1111);
                                return true;
                            case R.id.deleteExam:
                                deleteExam(exam,v.getContext(),position);
                                return true;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
*/
    }


    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView examName;
       // public View options;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examName=itemView.findViewById(R.id.examName);
            //options=itemView.findViewById(R.id.options);
        }
    }
}
