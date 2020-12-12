package id.alin_gotama.ukmku;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAdminAdapter;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;
public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnTambah;
    public static CustomAdapterAdminAdapter CustomAdapterAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView  = findViewById(R.id.rvAdminContent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.btnTambah = findViewById(R.id.btnAdminTambah);
        this.btnTambah.setOnClickListener(this);

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        btnTambah.setAlpha((float) 0.75);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        btnTambah.setAlpha((float) 0.25);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        btnTambah.setAlpha((float) 0.75);
                        break;

                }

            }
        });

        MyDatabase myDatabase = MyDatabase.createDatabase(this);
        List<UKM> UKMs = myDatabase.myDao().ambilSemuaUKM();

        CustomAdapterAdminAdapter = new CustomAdapterAdminAdapter(UKMs,this);
        recyclerView.setAdapter(CustomAdapterAdminAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdminTambah){
            Intent TambahUKM = new Intent(AdminActivity.this,AdminTambahActivity.class);
            startActivity(TambahUKM);
        }
    }
}