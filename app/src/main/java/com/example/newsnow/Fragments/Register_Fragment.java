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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register_Fragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText et_email,et_password,username_et,phone_et;
    Button signin,signup;
    String email,password,userName,phoneNo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register,container,false);
        et_email = view.findViewById(R.id.register_username_et);
        et_password = view.findViewById(R.id.register_password_et);
        username_et = view.findViewById(R.id.register_displayName_et);
        phone_et = view.findViewById(R.id.register_phone_et);
        signup = view.findViewById(R.id.register_btn);
        signin = view.findViewById(R.id.goTo_login_btn);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();
                userName = username_et.getText().toString().trim();
                phoneNo = phone_et.getText().toString().trim();
                createUser();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
    void createUser()
    {
        Log.d("---------","name pass :"+email + password);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register_Fragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("-----","successful");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(userName)
                                    .build();
                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                    Log.d("---","Updated Display Name");
                                    }
                                }
                            });
                            Intent intent = new Intent(Register_Fragment.this.getContext(), MainLayout.class);
                            startActivity(intent);
                        }
                        else {
                            Log.d("-----","failed");
                            Toast.makeText(Register_Fragment.this.getActivity(),"Authentication Failed!",Toast.LENGTH_SHORT);
                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("-----","Success : " + authResult);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register_Fragment.this.getActivity(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                Log.d("-----","error : " + e.toString());
            }
        });
    }
}
