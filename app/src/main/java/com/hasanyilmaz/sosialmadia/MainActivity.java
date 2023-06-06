package com.hasanyilmaz.sosialmadia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hasanyilmaz.sosialmadia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
// firebase de kullanici menusu icin bu kodu kullaniyoruz
  mAuth=FirebaseAuth.getInstance();

        FirebaseUser user=mAuth.getCurrentUser();
    // burda kullanici bir defa kayit olmussa surekli kayit sayfasi gelmemesi icin
        if (user !=null){
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
        }


    }
    // burda kayit olmus isek bilgilerimizle giriyoruz
    public void signin(View view){
        String email=binding.editTextTextPersonName.getText().toString();
        String passwort=binding.editTextTextPassword.getText().toString();
  // email ve sifre bossa mesaj veriyor
        if (email.equals("")||passwort.equals("")){
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
        }else{
   // eger bos degil dogru isi iki sonuc olabilir hersey dogru oldugunda yonlendirilen sayfasi git veya hata ver
            mAuth.signInWithEmailAndPassword(email,passwort).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
 // burda da once kayit islemlerini yapiyoruz
    public  void signup(View view){
        String email=binding.editTextTextPersonName.getText().toString();
        String passwort=binding.editTextTextPassword.getText().toString();

if (email.equals("") ||passwort.equals("")){

    Toast.makeText(this, "enter  email and password", Toast.LENGTH_SHORT).show();
}else{

    mAuth.createUserWithEmailAndPassword(email,passwort).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    });

}



    }

}