package id.alin_gotama.ukmku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import id.alin_gotama.ukmku.MyFragment.DetailUKM;
import id.alin_gotama.ukmku.MyFragment.Fragment_anggota;
import id.alin_gotama.ukmku.MyFragment.Fragment_berita;
import id.alin_gotama.ukmku.MyFragment.Fragment_tambah;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class UKMActivity extends AppCompatActivity {
    public static final String UKM_ID = "UKM_ID";
    public static final int UPDATE = 1121;

    private BottomNavigationView bottomNavigationView;
    private ArrayList<Anggota> ArAnggotas;
    private Long ukm_id;
    private TextView tvTitle;
    private UKM ukm;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_k_m);
        myDatabase = MyDatabase.createDatabase(this);

        this.ukm_id = getIntent().getLongExtra(UKM_ID,-1);
        this.ukm = myDatabase.myDao().ambilSatuUKMid(this.ukm_id);
        this.tvTitle = findViewById(R.id.tvUKMActivityTitle);
        this.tvTitle.setText(ukm.ukm_nama);


        this.bottomNavigationView = findViewById(R.id.bnUKM);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.iteMenuDetail){
                    item.setChecked(true);
                    Detail();
                }
                else if(item.getItemId() == R.id.itemMenuAnggota){
                    item.setChecked(true);
                    Anggota();
                }
                else if(item.getItemId() == R.id.itemMenuDaftar){
                    item.setChecked(true);
                    TambahAnggota();
                }
                else if(item.getItemId() == R.id.itemMenuWeb){
                    item.setChecked(true);
                    Berita();
                }
                return false;
            }
        });
        Detail();
    }

    private void Detail(){
        DetailUKM detailUKM = new DetailUKM();
        Bundle bundle = new Bundle();
        bundle.putLong(DetailUKM.UKM_ID,ukm_id);
        detailUKM.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,detailUKM);
        ft.commit();
    }

    private void Anggota(){
        Fragment_anggota fragment_anggota = new Fragment_anggota();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,fragment_anggota);
        Bundle bundle = new Bundle();
        bundle.putLong(Fragment_anggota.UKM_ID,this.ukm_id);
        fragment_anggota.setArguments(bundle);
        ft.commit();
    }

    private void TambahAnggota(){
        Fragment_tambah fragment_tambah = new Fragment_tambah();
        Bundle bundle = new Bundle();
        bundle.putLong(Fragment_tambah.UKM_ID,this.ukm_id);
        fragment_tambah.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,fragment_tambah);
        ft.commit();
    }

    private void Berita(){
        Fragment_berita fragment_berita = new Fragment_berita();
        Bundle bundle = new Bundle();
        bundle.putLong(Fragment_berita.UKM_ID,this.ukm_id);
        fragment_berita.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,fragment_berita);
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT",String.valueOf(resultCode));
        Log.d("REQUEST",String.valueOf(requestCode));
    }
}