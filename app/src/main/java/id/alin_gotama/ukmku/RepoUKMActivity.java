package id.alin_gotama.ukmku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAdminAdapter;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterRepoUKM;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class RepoUKMActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterRepoUKM customAdapterRepoUKM;
    private EditText etSearchEngine;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_u_k_m);
        myDatabase =MyDatabase.createDatabase(this);

        List<UKM> ukms = myDatabase.myDao().ambilSemuaUKM();

        customAdapterRepoUKM = new CustomAdapterRepoUKM(ukms,this);

        this.recyclerView = findViewById(R.id.rvRepoUKM);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(customAdapterRepoUKM);

        this.etSearchEngine = findViewById(R.id.etRepoUKMSearchEngine);
        this.etSearchEngine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                StringBuilder search = new StringBuilder();
                search.append("%");
                search.append(s.toString());
                search.append("%");
                CustomAdapterRepoUKM.Arukms = myDatabase.myDao().searchEngineUKM(search.toString());
                customAdapterRepoUKM.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        overridePendingTransition(R.anim.activityfadein, R.anim.activityfadeout);
    }
}