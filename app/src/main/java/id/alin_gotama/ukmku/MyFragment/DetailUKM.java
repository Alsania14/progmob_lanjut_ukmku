package id.alin_gotama.ukmku.MyFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class DetailUKM extends Fragment {
    public static final String UKM_ID = "UKM_ID";
    private Button btnWebView;
    private ImageView ivCover;
    private TextView tvNama,tvDescription;
    private UKM ukm;
    private MyDatabase myDatabase;

    public DetailUKM() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_ukm,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDatabase = MyDatabase.createDatabase(getContext());
        Bundle bundle = getArguments();
        Long id = bundle.getLong(UKM_ID);
        ukm = myDatabase.myDao().ambilSatuUKMid(id);
        Log.d("UKM",String.valueOf(ukm.getUkm_id()));
        Log.d("UKM",ukm.getImage_name());
        Log.d("UKM",ukm.getUkm_nama());
        Log.d("UKM",ukm.getUkm_nama());
        this.ivCover = view.findViewById(R.id.ivUKMDetail);
        this.tvNama = view.findViewById(R.id.tvUKMDetailNama);
        this.tvDescription = view.findViewById(R.id.tvUKMDetailDescriptiion);

        ivCover.setImageBitmap(ReadFromLocalStorage.readImage(getContext(),ukm.getImage_name()));
        tvNama.setText(ukm.getUkm_nama());
        tvDescription.setText(ukm.getDescription());
    }
}
