package com.lkvcodestudio.exammaster.ui.me;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.lkvcodestudio.exammaster.LoginActivity;
import com.lkvcodestudio.exammaster.R;
import com.squareup.picasso.Picasso;

public class MeFragment extends Fragment {

    ImageView dp;
    TextView username,email;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_me, container, false);
        dp = root.findViewById(R.id.dpView);
        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.useremail);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(root.getContext());
        if(signInAccount != null){
            username.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            Picasso.get().load(signInAccount.getPhotoUrl()).placeholder(R.drawable.usr_img).error(R.drawable.usr_img).into(dp);
        }

        View aboutView = root.findViewById(R.id.aboutView);
        aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(root.getContext(), AboutActivity.class));
            }
        });

        View logoutView = root.findViewById(R.id.logoutView);
        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(root.getContext());

                dialog.setContentView(R.layout.logout_dialog);
                dialog.setCanceledOnTouchOutside(false);
                Window w = dialog.getWindow();
                w.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                w.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                w.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                Button btCancel = dialog.findViewById(R.id.cancel);
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Button btLogout = dialog.findViewById(R.id.logout);
                btLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                       // Toast.makeText(root.getContext(),"Logging you out ....",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(root.getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                dialog.show();
            }
        });
        return root;
    }
}