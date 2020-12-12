package id.alin_gotama.ukmku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAdminAdapter;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class DetailAdminUKM extends AppCompatActivity {
    public static final String MODEL = "MODEL";
    private static final int MY_REQUEST_FOR_EDIT = 1;
    public static final String UKM_ID = "UKM_ID";
    public static CustomAdapterAdminAdapter customAdapterAdminAdapter;

    private TextView tvNama,tvDescription,tvLink;
    private Button btnEdit;
    private ImageView ivCover;
    private Long Ukm_id;
    private String imagePathandName;
    private UKM ukm;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin_u_k_m);
        Ukm_id = getIntent().getLongExtra(MODEL,-1);

        myDatabase = MyDatabase.createDatabase(this);
        ukm = myDatabase.myDao().ambilSatuUKMid(Ukm_id);
        Bitmap bitmap = ReadFromLocalStorage.readImage(this,ukm.getImage_name());

        tvNama = findViewById(R.id.tvDetailAdminUKMNama);
        tvDescription = findViewById(R.id.tvDetailAdminUKMDescription);
        tvLink = findViewById(R.id.tvDetailAdminUKMOfficialSite);
        ivCover = findViewById(R.id.ivAdminDetailUKMCover);
        ivCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivCover.setImageBitmap(bitmap);

        tvNama.setText(ukm.getUkm_nama());
        tvLink.setText(ukm.getUkm_link());
        tvDescription.setText(ukm.getDescription());

        btnEdit = findViewById(R.id.btnDetailAdminUKMEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailAdminUKM.this,AdminEditUKMActivity.class);
                intent.putExtra(AdminEditUKMActivity.UKM_ID,ukm.ukm_id);
                startActivityForResult(intent,MY_REQUEST_FOR_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_FOR_EDIT){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
                Long ukm_id = data.getLongExtra(UKM_ID,-1);
                UKM ukm = myDatabase.myDao().ambilSatuUKMid(ukm_id);
                this.tvNama.setText(ukm.getUkm_nama());
                this.tvDescription.setText(ukm.getDescription());
                this.tvLink.setText(ukm.getUkm_link());
                this.ivCover.setImageBitmap(ReadFromLocalStorage.readImage(this,ukm.getImage_name()));
            }
            else
            {
                Toast.makeText(this, "ZONK", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        CustomAdapterAdminAdapter.ukms = myDatabase.myDao().ambilSemuaUKM();
        customAdapterAdminAdapter.notifyDataSetChanged();
        super.onBackPressed();
    }
}