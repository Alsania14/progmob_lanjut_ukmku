package id.alin_gotama.ukmku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.alin_gotama.ukmku.MyFragment.Fragment_anggota;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAnggota;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;

public class Anggota_edit extends AppCompatActivity implements View.OnClickListener{
    public static final String Anggota_id = "Anggota_id";
    public static final String UKM_ID = "UKM_ID";
    public static final int SUKSES_UPDATE = -1;

    private EditText etNama,etNomor,etNim;
    private Button btnSubmit;
    private Anggota anggota;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anggota_edit);
        myDatabase = MyDatabase.createDatabase(this);
        this.anggota = myDatabase.myDao().ambilSatuAnggota(getIntent().getLongExtra(Anggota_id,-1));

        this.btnSubmit = findViewById(R.id.btnEditAnggotaSubmit);
        this.btnSubmit.setOnClickListener(this);

        this.etNama = findViewById(R.id.etEditNamaAnggota);
        this.etNim = findViewById(R.id.etEditNIMAnggota);
        this.etNomor = findViewById(R.id.etEditNomorAnggota);

        this.etNama.setText(anggota.getAnggota_nama());
        this.etNomor.setText(anggota.getAnggota_nomor());
        this.etNim.setText(anggota.getAnggota_nim());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnEditAnggotaSubmit){
            StringBuilder builder = new StringBuilder();
            builder.append("");
            if(this.etNama.getText().toString().matches("")){
                builder.append("Mohon Tambahkan Nama \n");
            }
            if(this.etNim.getText().toString().matches("")){
                builder.append("Mohon Tambahkan NIM \n");
            }
            if(this.etNomor.getText().toString().matches("")){
                builder.append("Mohon Tambahkan Nomor \n");
            }

            if(builder.toString().matches("")){
                this.anggota.setAnggota_nama(etNama.getText().toString());
                this.anggota.setAnggota_nim(etNim.getText().toString());
                this.anggota.setAnggota_nomor(etNomor.getText().toString());
                Toast.makeText(this, String.valueOf(myDatabase.myDao().updateAnggota(anggota)), Toast.LENGTH_SHORT).show();
                CustomAdapterAnggota.anggotas = myDatabase.myDao().ambilSemuaAnggotaUKM(getIntent().getLongExtra(UKM_ID,-1));
                Fragment_anggota.customAdapterAnggota.notifyDataSetChanged();
                finish();
            }else{
                AlertDialog.Builder alert =  new AlertDialog.Builder(this);
                alert.setTitle("ERRORS");
                alert.setMessage(builder.toString());
                alert.setCancelable(true);
                alert.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.create();
                alert.show();
            }

        }
    }
}