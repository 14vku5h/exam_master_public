package com.lkvcodestudio.exammaster.ui.me;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.lkvcodestudio.exammaster.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("About the App");

        LinearLayout developerApps = findViewById(R.id.developerApps);
        developerApps.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://search?q=pub:LKV+Code+Studio"));
                startActivity(intent);
            }
        });

        LinearLayout writeAnEmail = findViewById(R.id.write_an_email);
        writeAnEmail.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:lkvcodestudio@gmail.com"));
                intent.putExtra(Intent.EXTRA_EMAIL, "lkvcodestudio@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                startActivity(Intent.createChooser(intent, "E-Mail"));
            }
        });

        LinearLayout shareApp = findViewById(R.id.sharApp);
        shareApp.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                // Add data to the intent, the receiving app will decide
                // what to do with it.
                //  share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name)+"\n"+"https://play.google.com/store/apps/details?id=com.lkvcodestudio.exammaster");
                startActivity(Intent.createChooser(share, "Share To"));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}