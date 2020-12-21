package id.alin_gotama.ukmku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.MyHelper.RequestReadImage;
import id.alin_gotama.ukmku.MyHelper.SaveToInternalStorage;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAdminAdapter;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class AdminTambahActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int MY_IMAGE_REQUEST = 1;
    private File imageFile;
    private EditText etNama,etDescription,etSite;
    private ImageView ivCover;
    private Button btnImage,btnSubmit;
    private Bitmap bitmap;
    private String imagePath;
    private int imageStatus = 0;
    private ProgressBar pbSubmit;

    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah);

        myDatabase = MyDatabase.createDatabase(this);
        imagePath = "";
        etNama = findViewById(R.id.etAdminTambahNamaUKM);
        etSite = findViewById(R.id.etAdminTambahOfficialSite);
        etDescription = findViewById(R.id.etAdminTambahDescription);

        btnImage = findViewById(R.id.btnAdminTambahImage);
        btnSubmit = findViewById(R.id.btnAdminTambahSubmit);

        btnImage.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        pbSubmit = findViewById(R.id.pbAdminTambah);
        this.ivCover = findViewById(R.id.ivAdminTambahImage);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAdminTambahImage){
            Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
            fileintent.setType("image/*");
            try{
                startActivityForResult(fileintent, 1);
            }catch (ActivityNotFoundException e){
                Log.d("error","SOMETHING WRONG"+e.getMessage());
        }
        }
        else if(v.getId() == R.id.btnAdminTambahSubmit){
            StringBuilder errors = new StringBuilder();
            errors.append("");
            if(this.etNama.getText().toString().matches("")){
                errors.append("Mohon mengisi field nama\n");
            }
            if(this.etDescription.getText().toString().matches("")){
                errors.append("Mohon mengisi field description\n");
            }
            if(this.etSite.getText().toString().matches("")){
                errors.append("Mohon mengisi field Site\n");
            }
            if(this.imageStatus == 0){
                errors.append("Mohon memilih cover image\n");
            }

            if(errors.toString().matches("")){
                pbSubmit.setVisibility(ProgressBar.VISIBLE);
                this.imagePath = SaveToInternalStorage.saveToInternalStorage(bitmap,imageFile.getName(),"image",getBaseContext());

                    UKM ukm = new UKM();
                    ukm.setUkm_nama(this.etNama.getText().toString());
                    ukm.setUkm_link(this.etSite.getText().toString());
                    ukm.setDescription(this.etDescription.getText().toString());
                    ukm.setImage_name(this.imagePath);
                    Log.d("imagePathTambah",imagePath);
                    myDatabase.myDao().insertUKM(ukm);
                pbSubmit.setVisibility(ProgressBar.INVISIBLE);
                AlertDialog.Builder sukses = new AlertDialog.Builder(this);
                sukses.setTitle("SUKSES");
                sukses.setMessage("Selamat UKM anda telah dibentuk");
                sukses.setCancelable(true);
                sukses.setOnCancelListener(new AlertDialog.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        List<UKM> ukms = myDatabase.myDao().ambilSemuaUKM();
                        CustomAdapterAdminAdapter.ukms.clear();
                        CustomAdapterAdminAdapter.ukms.addAll(ukms);
                        AdminActivity.CustomAdapterAdminAdapter.notifyDataSetChanged();
                        finish();
                    }
                });
                sukses.create();
                sukses.show();

            }
            else{
                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Error");
                alert.setMessage(errors.toString());
                alert.setCancelable(true);
                alert.setPositiveButton("OKE", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeOptionsMenu();
                    }
                });
                alert.create();
                alert.show();
            }



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "ADA DONG HE", Toast.LENGTH_SHORT).show();
                    bitmap = BitmapFactory.decodeFile(result);
                    ivCover.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(this, "TIDAK DAPAT MENGGUNAKAN GAMBAR TERSEBUT !", Toast.LENGTH_LONG).show();
                    imageStatus = 0;
                }
                }
                else{
                    Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();
                }
        }
    }
}