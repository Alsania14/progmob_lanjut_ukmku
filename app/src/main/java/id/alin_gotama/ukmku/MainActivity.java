package id.alin_gotama.ukmku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.alin_gotama.ukmku.MyHelper.GetPathFromUri;
import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.MyHelper.RequestReadImage;
import id.alin_gotama.ukmku.MyHelper.SaveToInternalStorage;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;


public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int MY_IMAGE_REQUEST = 1;
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);

//        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
//        fileintent.setType("image/*");
//        try{
//            startActivityForResult(fileintent, 1);
//        }catch (ActivityNotFoundException e){
//            Log.d("error","some error");
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_IMAGE_REQUEST){
            Log.d("result",String.valueOf(resultCode));
            Uri selectedImage = data.getData();
            RequestReadImage requestReadImage = new RequestReadImage();

            String result = requestReadImage.getPath(selectedImage,this);
            imageFile = new File(result);

            if(imageFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(result);
                SaveToInternalStorage.saveToInternalStorage(bitmap,imageFile.getName(),"image",getBaseContext());

                Bitmap image = ReadFromLocalStorage.readImage(this,result);
                Toast.makeText(this, String.valueOf(image.getHeight()), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "TIDAK DAPAT MENGGUNAKAN GAMBAR TERSEBUT !", Toast.LENGTH_LONG).show();
            }

        }
    }
}