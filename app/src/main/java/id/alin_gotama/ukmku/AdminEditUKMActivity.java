package id.alin_gotama.ukmku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import id.alin_gotama.ukmku.MyHelper.FileDeleter;
import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.MyHelper.RequestReadImage;
import id.alin_gotama.ukmku.MyHelper.SaveToInternalStorage;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAdminAdapter;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class AdminEditUKMActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String UKM_ID = "UKM_ID";
    private static final int MY_IMAGE_REQUEST = 1;


    private EditText etNama,etDescription,etLink;
    private ImageView ivCover;
    private Button btnImage,btnSimpan;
    private UKM ukm;
    private MyDatabase myDatabase;

    private File imageFile;
    private int imageStatus = 0;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_u_k_m);

        Long ukm_id = getIntent().getLongExtra(UKM_ID, -1);
        myDatabase = MyDatabase.createDatabase(this);
        ukm = myDatabase.myDao().ambilSatuUKMid(ukm_id);

        etNama = findViewById(R.id.etAdminEditNamaUKM);
        etDescription = findViewById(R.id.etAdminEditDescription);
        etLink = findViewById(R.id.etAdminEditOfficialSite);

        ivCover = findViewById(R.id.ivAdminEditImage);
        btnImage = findViewById(R.id.btnAdminEditImage);
        btnSimpan = findViewById(R.id.btnAdminEditSubmit);

        btnSimpan.setOnClickListener(this);
        btnImage.setOnClickListener(this);

        etNama.setText(ukm.getUkm_nama());
        etDescription.setText(ukm.getDescription());
        etLink.setText(ukm.getUkm_link());
        Bitmap bitmap = ReadFromLocalStorage.readImage(this,ukm.getImage_name());
        ivCover.setImageBitmap(bitmap);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdminEditSubmit){
            StringBuilder error = new StringBuilder();
            error.append("");
            if(etNama.getText().toString().matches("")){
                error.append("Tolong tambahkan nama UKM\n");
            }
            if(etDescription.getText().toString().matches("")){
                error.append("Tolong tambahkan description\n");
            }
            if(etLink.getText().toString().matches("")){
                error.append("Tolong tambahkan Link\n");
            }

            if(error.toString().matches("")){
                if(imageStatus == 1){
                    FileDeleter.Delete(ukm.image_name);
                    String imagePath = SaveToInternalStorage.saveToInternalStorage(bitmap,imageFile.getName(),"image",this);
                    Log.d("imagePath",imagePath);
                    ukm.setImage_name(imagePath);
                }

                ukm.setUkm_nama(etNama.getText().toString());
                ukm.setDescription(etDescription.getText().toString());
                ukm.setUkm_link(etLink.getText().toString());
                myDatabase.myDao().updateSatuUKM(ukm);
                Intent data = new Intent();
                data.putExtra(DetailAdminUKM.UKM_ID,ukm.ukm_id);
                setResult(-1,data);
                finish();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("ERROR !");
                builder.setMessage(error.toString());
                builder.setCancelable(true);
                builder.create();
                builder.show();
            }

        }
        else if(v.getId() == R.id.btnAdminEditImage) {
            Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
            fileintent.setType("image/*");
            try {
                startActivityForResult(fileintent, 1);
            } catch (ActivityNotFoundException e) {
                Log.d("error", "SOMETHING WRONG" + e.getMessage());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("reqest code",String.valueOf(requestCode));
        Log.d("result code",String.valueOf(requestCode));

        if(requestCode == MY_IMAGE_REQUEST){
            if(resultCode == RESULT_OK) {
                Log.d("result", String.valueOf(resultCode));
                Uri selectedImage = data.getData();
                RequestReadImage requestReadImage = new RequestReadImage();

                String result = requestReadImage.getPath(selectedImage, this);
                imageFile = new File(result);

                if (imageFile.exists()) {
                    imageStatus = 1;
                    bitmap = BitmapFactory.decodeFile(result);
                    ivCover.setImageBitmap(bitmap);
                } else {
                    imageStatus = 0;
                }
                CustomAdapterAdminAdapter.ukms = myDatabase.myDao().ambilSemuaUKM();

            }
            else{
            }
        }
    }
}