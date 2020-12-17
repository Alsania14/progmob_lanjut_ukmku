package id.alin_gotama.ukmku.MyFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.MyRecyclerView.CustomAdapterAnggota;
import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class Fragment_anggota extends Fragment {
    public static final String UKM_ID = "UKM_ID";

    private UKM ukm;
    private List<Anggota> anggotas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyDatabase myDatabase;
    @SuppressLint("StaticFieldLeak")
    public static CustomAdapterAnggota customAdapterAnggota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anggota,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.myDatabase = MyDatabase.createDatabase(getContext());

        this.ukm = myDatabase.myDao().ambilSatuUKMid(getArguments().getLong(UKM_ID,-1));
        this.anggotas = myDatabase.myDao().ambilSemuaAnggotaUKM(getArguments().getLong(UKM_ID,-1));
        Log.d("anggota :",String.valueOf(anggotas.size()));
        this.recyclerView = view.findViewById(R.id.rvFragmentAnggota);
        this.layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        customAdapterAnggota = new CustomAdapterAnggota(this.anggotas,getContext(),getActivity(),ukm.getUkm_id());

        this.recyclerView.setAdapter(customAdapterAnggota);
    }
}