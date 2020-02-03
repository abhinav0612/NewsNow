package com.example.newsnow.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsnow.MainLayout;
import com.example.newsnow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Fragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText et_email,et_password;
    Button signin,signup;
    String email,password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login,container,false);
        et_email = view.findViewById(R.id.login_username_et);
        et_password = view.findViewById(R.id.login_password_et);
        signin = view.findViewById(R.id.login_btn);
        signup = view.findViewById(R.id.goTo_signup_btn);
        mAuth = FirebaseAuth.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();
                loginUser();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Fragment.this.getContext(),Register_Fragment.class);
                startActivity(intent);
            }
        });

        return view;
    }
    void loginUser()
    {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(Login_Fragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(Login_Fragment.this.getContext(), MainLayout.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login_Fragment.this.getActivity(),"Authentication Failed!",Toast.LENGTH_SHORT);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("---","Failure : " + e.toString());
            }
        });
    }
}
