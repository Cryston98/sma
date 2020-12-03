package com.example.sma_lab.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sma_lab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseLab extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button login,register;
    TextView emaillog,passlog,userreg,passreg,emailreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_lab);

        mAuth = FirebaseAuth.getInstance();
        initialize();
        registerFirebase();
        loginFirebase();

    }
    void initialize(){
        login=findViewById(R.id.firebase_log_btn);
        register=findViewById(R.id.firebase_reg_btn);
        emaillog=findViewById(R.id.firebase_emaillog);
        passlog=findViewById(R.id.firebase_passlog);
        emailreg=findViewById(R.id.firebase_emailreg);
        passreg=findViewById(R.id.firebase_passreg);
        userreg=findViewById(R.id.firebase_userreg);
    }


    void registerFirebase()
    {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailreg.getText().toString().trim();
                String password=passreg.getText().toString().trim();
                String username=userreg.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //verif valid email
                    emailreg.setError("Please provide valid email");
                    emailreg.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    emailreg.setError("Email is required");
                    emailreg.requestFocus();
                    return;
                }
                if (username.isEmpty()){
                    userreg.setError("Password is required");
                    userreg.requestFocus();
                    return;
                }
                if (password.length()<6){
                    passreg.setError("Password need to 6 characters");
                    passreg.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    UserFirebase user= new UserFirebase(username,email,password);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(FirebaseLab.this,"Succesful register",Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(FirebaseLab.this,"Faile register",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }else{
                                    Toast.makeText(FirebaseLab.this,"Faile 2 register",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    void loginFirebase()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emaillog.getText().toString().trim();
                String password=passlog.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //verif valid email
                    emaillog.setError("Please provide valid email");
                    emaillog.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    emaillog.setError("Email is required");
                    emaillog.requestFocus();
                    return;
                }

                if (password.length()<6){
                    passlog.setError("Password need to 6 characters");
                    passlog.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    startActivity(new Intent(FirebaseLab.this, ProfileFirebase.class));

                                }else{
                                    Toast.makeText(FirebaseLab.this,"Faile login",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}