package id.alin_gotama.ukmku.MyFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;

public class Fragment_tambah extends Fragment implements View.OnClickListener {
    public static final String UKM_ID = "UKM_ID";
    private EditText etNama,etNomor,etNim;
    private Button btnTambah;
    private MyDatabase myDatabase;
    private Long ukm_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tambah_anggota,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDatabase = MyDatabase.createDatabase(getContext());
        this.ukm_id = getArguments().getLong(UKM_ID);
        this.etNama = view.findViewById(R.id.etTambahAnggotaNama);
        this.etNim = view.findViewById(R.id.etTambahAnggotaNim);
        this.etNomor = view.findViewById(R.id.etTambahAnggotaNomor);
        this.btnTambah = view.findViewById(R.id.btnAnggotaTambah);

        this.btnTambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAnggotaTambah){
            StringBuilder builder = new StringBuilder();
            builder.append("");
            if(etNama.getText().toString().matches("")){
                builder.append("Mohon tambahkan Nama");
            }
            if (etNim.getText().toString().matches("")) {
                builder.append("Mohon Tambahkan Nim");
            }
            if(etNomor.getText().toString().matches("")){
                builder.append("Mohon Tambahkan Nomor");
            }
            if(builder.toString().matches("")){
                Anggota anggota = new Anggota();
                anggota.setAnggota_nama(etNama.getText().toString());
                anggota.setAnggota_nomor(etNomor.getText().toString());
                anggota.setAnggota_nim(etNim.getText().toString());
                anggota.setUkm_id_fk(this.ukm_id);
                myDatabase.myDao().insertAnggota(anggota);

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Anggota telah ditambahkan ! \n Nama : "+etNama.getText().toString());
                alertDialog.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setCancelable(true);
                alertDialog.create();
                alertDialog.show();

                this.etNim.setText("");
                this.etNama.setText("");
                this.etNomor.setText("");
            }
        }
    }
}
