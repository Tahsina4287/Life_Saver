package com.example.life_saver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginWindow extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btn;
    private Button userlog;
    private EditText mail,pass;
    private FirebaseAuth mauth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Life_Saver);
        setContentView(R.layout.login);
        btn =findViewById(R.id.back1);
        userlog = findViewById(R.id.sign_in_btn);
        mail = findViewById(R.id.sign_in_phone);
        pass = findViewById(R.id.sign_in_pass);
        mauth = FirebaseAuth.getInstance();
        btn.setOnClickListener(this);
        userlog.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back1)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.sign_in_btn){
            UserLogin();
        }
    }

    private void UserLogin() {

        String phn = mail.getText().toString().trim();
        String upass = pass.getText().toString().trim();
        if(phn.isEmpty())
        {
            mail.setError("Enter a Valid Email Address");
            mail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(phn).matches()){
            mail.setError("Enter a Valid Email Address");
            mail.requestFocus();
            return;
        }
        if (upass.isEmpty() || upass.length()<6){
            pass.setError("Enter a Valid Password");
            pass.requestFocus();
            return;
        }
        else{
            isUser();
        }



    }

    private void isUser() {
        String email = mail.getText().toString();
        String upass = pass.getText().toString();
        mauth.signInWithEmailAndPassword(email,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "login Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*private void isUser() {

        String phn1 = phone.getText().toString();
        String ps1 = pass.getText().toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User Profile");
        Query chkuser = ref.orderByChild("phone").equalTo(phn1);
        chkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                String passwordfromdb = snapshot.child(phn1).child("pass").getValue(String.class);
                if(passwordfromdb.equals(ps1)){
                    Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    intent.putExtra("phone",passwordfromdb);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"User doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/
}
