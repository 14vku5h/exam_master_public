package com.lkvcodestudio.exammaster.ui.notifications;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lkvcodestudio.exammaster.R;
import com.lkvcodestudio.exammaster.Utils;

import java.time.LocalDateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNotificationActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.subTitle)
    EditText subtitle;
    @BindView(R.id.dateView)
    View dateView;
    @BindView(R.id.dateText)
    TextView dateText;
    @BindView(R.id.timeView)
    View timeView;
    @BindView(R.id.timeText)
    TextView timeText;
    @BindView(R.id.alertView)
    View alertView;
    public static TextView alertText;
    @BindView(R.id.saveExam)
    Button saveExam;
    public static final Notification NOTIFICATION = new Notification();
    public static Dialog typeSelDialog;
    FirebaseFirestore fstore;
    GoogleSignInAccount signInAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Add Exam");
        ButterKnife.bind(this);
        fstore = FirebaseFirestore.getInstance();
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        alertText = findViewById(R.id.alertText);

        final Integer[] date = new Integer[5]; // dd|mm|yyyy|hh|mm
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime d = LocalDateTime.now();
            dateText.setText(Utils.convertDateTimeToStr(d, "dd MMM yyyy"));
            date[0] = d.getYear();
            date[1] = d.getMonthValue();
            date[2] = d.getDayOfMonth();
            date[3] = 9;
            date[4] = 0;
        }
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddNotificationActivity.this);
                dialog.setContentView(R.layout.date_dialog);
                dialog.setCanceledOnTouchOutside(false);
                Window w = dialog.getWindow();
                w.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                w.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                w.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                DatePicker datePicker = dialog.findViewById(R.id.datePicker);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    datePicker.init(date[0], date[1] - 1, date[2], null);
                }
                Button okBtn = dialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date[0] = datePicker.getYear();
                        date[1] = datePicker.getMonth() + 1;
                        date[2] = datePicker.getDayOfMonth();
                        //dateText.setText(date[0] + "-" + date[1] + "-" + date[2]);
                        dateText.setText(Utils.convertStrToDateStr(date[0], date[1], date[2]));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddNotificationActivity.this);
                dialog.setContentView(R.layout.clock_dialog);
                dialog.setCanceledOnTouchOutside(false);
                Window w = dialog.getWindow();
                w.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                w.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                w.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                TimePicker timePicker = dialog.findViewById(R.id.timePicker);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    timePicker.setHour(date[3]);
                    timePicker.setMinute(date[4]);
                }
                Button okBtn = dialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date[3] = timePicker.getCurrentHour();
                        date[4] = timePicker.getCurrentMinute();
                        timeText.setText(Utils.convertToTimeStr(date[3], date[4]));
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        alertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeSelDialog = new Dialog(AddNotificationActivity.this);
                typeSelDialog.setContentView(R.layout.alert_type_dialog);
                typeSelDialog.setCanceledOnTouchOutside(false);
                Window w = typeSelDialog.getWindow();
                w.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                w.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                w.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                RecyclerView recyclerView = typeSelDialog.findViewById(R.id.alertTypeRecycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new AlertTypeViewAdapter());
                View closeAl = typeSelDialog.findViewById(R.id.closeAl);
                closeAl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        typeSelDialog.dismiss();
                    }
                });

                typeSelDialog.show();
            }
        });
        saveExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExam.setEnabled(false);
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter the title");
                    saveExam.setEnabled(true);
                } else {
                    NOTIFICATION.setName(name.getText().toString());
                    if (!subtitle.getText().toString().isEmpty()) {
                        NOTIFICATION.setSubtitle(subtitle.getText().toString());
                    }
                    if (date[0] != null) {
                        if (date[3] != null) {
                            LocalDateTime dateTime = Utils.convertIntsToDateTime(date[0], date[1], date[2], date[3], date[4]);
                            NOTIFICATION.setDateTime(dateTime);
                            Toast.makeText(AddNotificationActivity.this, "Saving Data....", Toast.LENGTH_SHORT).show();
                            CollectionReference ref = fstore.collection("users/"+signInAccount.getId()+"/notifications");
                            ref.add(NOTIFICATION.getMap())
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(AddNotificationActivity.this, "Exam addedd successfully: ",Toast.LENGTH_SHORT).show();
                                            Log.e("FSTORE", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            goBack();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("FSTORE", "Error adding document", e);
                                            Toast.makeText(AddNotificationActivity.this, "Failed to add exam !",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            saveExam.setEnabled(true);
                            Toast.makeText(AddNotificationActivity.this, "Please change the date", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        saveExam.setEnabled(true);
                        Toast.makeText(AddNotificationActivity.this, "Please change the time", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void goBack() {
        //super.onBackPressed();
        Intent intent = new Intent();
      //  intent.putExtra("editTextValue", "value_here")
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}