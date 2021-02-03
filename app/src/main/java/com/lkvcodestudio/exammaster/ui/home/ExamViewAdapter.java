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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.models.Exam;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExamViewAdapter extends RecyclerView.Adapter<ExamViewAdapter.ExamViewHolder> {
    List<Exam> examList;
    Fragment fragment;
    FirebaseFirestore firestore;
    String userId;

    public ExamViewAdapter(Fragment fragment, List<Exam> examList) {
        this.fragment = fragment;
        this.examList = examList;
        this.firestore = FirebaseFirestore.getInstance();
    }

    public ExamViewAdapter(List<Exam> examList, String userId) {
        this.userId = userId;
        this.examList = examList;
        this.firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);

        ExamViewHolder holder = new ExamViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.examName.setText(exam.getName());
        TextView exn = holder.examName;
        exn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int green = v.getContext().getResources().getColor(R.color.green);
                int white = v.getContext().getResources().getColor(R.color.white);
                if (exam.isSelected()) {
                    v.setBackgroundColor(white);
                    holder.parentV.setBackgroundColor(white);
                    holder.selectedIcon.setVisibility(View.GONE);
                    exam.setSelected(false);
                    String t = v.getTag().toString().trim();
                    Task<Void> writeResult = firestore.collection("/users/" + userId + "/exams")
                            .document(t).delete();
                    writeResult.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.e("Delete Result", "Deleted successfully");
                            Toast.makeText(holder.itemView.getContext(), "Remove Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                    writeResult.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG ERRor", e.getMessage());
                            Toast.makeText(holder.itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Log.e("ExamViewAdapter", "unchecked");
                    v.setBackgroundColor(green);
                    holder.parentV.setBackgroundColor(green);
                    holder.selectedIcon.setVisibility(View.VISIBLE);
                    exam.setSelected(true);
                    Log.e("ExamViewAdapter", "checked");
                    firestore.collection("/users/" + userId + "/exams")
                            .add(exam)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    v.setTag(documentReference.getId());
                                    Toast.makeText(holder.itemView.getContext(), "Exam selected successfully: ", Toast.LENGTH_SHORT).show();
                                    Log.e("SelectSubAd", "DocumentSnapshot  added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("SelectSubAd", "Error adding document", e);
                                    Toast.makeText(holder.itemView.getContext(), "Failed to add subject !", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView examName;
        public View selectedIcon, parentV;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            examName = itemView.findViewById(R.id.examName);
            selectedIcon = itemView.findViewById(R.id.selectedIcon);
            parentV = itemView.findViewById(R.id.parentV);
        }
    }
}
