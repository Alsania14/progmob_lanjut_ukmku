package id.alin_gotama.ukmku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import id.alin_gotama.ukmku.MyFragment.DetailUKM;
import id.alin_gotama.ukmku.MyFragment.Fragment_berita;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class UKMActivity extends AppCompatActivity {
    public static final String UKM_ID = "UKM_ID";
    private BottomNavigationView bottomNavigationView;
    private ArrayList<Anggota> ArAnggotas;
    private Long ukm_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_k_m);
        this.ukm_id = getIntent().getLongExtra(UKM_ID,-1);
        Detail();

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
                }
                else if(item.getItemId() == R.id.itemMenuDaftar){
                    item.setChecked(true);
                }
                else if(item.getItemId() == R.id.itemMenuWeb){
                    item.setChecked(true);
                    Berita();
                }
                return false;
            }
        });
    }

    private void Detail(){
        DetailUKM detailUKM = new DetailUKM();
        Bundle bundle = new Bundle();
        bundle.putLong(DetailUKM.UKM_ID,ukm_id);
        detailUKM.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,detailUKM);
        ft.commit();
    }

    private void Berita(){
        Fragment_berita fragment_berita = new Fragment_berita();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.flUKM1,fragment_berita);
        ft.commit();
    }
}