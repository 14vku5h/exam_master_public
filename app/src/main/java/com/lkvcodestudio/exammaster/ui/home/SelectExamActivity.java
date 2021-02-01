package com.lkvcodestudio.exammaster.ui.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.lkvcodestudio.exammaster.R;

public class SelectExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exam);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Select your exam");
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