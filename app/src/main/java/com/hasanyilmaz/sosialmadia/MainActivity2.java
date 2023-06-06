package com.hasanyilmaz.sosialmadia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth=FirebaseAuth.getInstance();
    }

    @Override
    //burda menu ile baglama islemini yapiyoruz
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu baglama icin kullandigimiz kod bulogu
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
      auth.signOut();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //burda da select ler secilince ne olacagini yaziyoruz
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.add){
            Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.out){
            Intent intent2=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent2);
            finish();


        }

        return super.onOptionsItemSelected(item);
    }
}