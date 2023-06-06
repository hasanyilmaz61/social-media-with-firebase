package com.hasanyilmaz.sosialmadia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hasanyilmaz.sosialmadia.databinding.ActivityMain3Binding;
import com.hasanyilmaz.sosialmadia.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Uri imagedata;
    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registirlaunher();
    }


    public void upload(View view) {

    }
    public  void selectimage(View view){

        //1.manifeste izini ekleyecgiz
        //2.izin varmi onu kontrol edecegiz
        // 3. eger izin varsa galeriye gidecegiz
        // 4. yoksa once mantigi gosterip sonra izin isteyecegiz

        // eger izin verilmedi ise
     if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
          // izin verme mantigini gostermeliyiz
         if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
             Snackbar.make(view,"izne ihtiyacim var",Snackbar.LENGTH_INDEFINITE).setAction("izin ver", new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

             permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                 }
             }).show();
         }else{
             permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

         }
     }else{
         //izin verildi ise          git resmi al nerden medistordan ve url sini al
         Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          activityResultLauncher.launch(intent);
     }

    }

    private  void registirlaunher(){
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK){
                    Intent intentresult=result.getData();
                    if (intentresult !=null){
                        imagedata=intentresult.getData();
                        binding.imageView.setImageURI(imagedata);
                    }
                }
            }
        });

        permissionLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result==true){
                    Intent intentgalery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentgalery);
                }else{
                    Toast.makeText(MainActivity3.this, "izne ihtiyacim var", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}