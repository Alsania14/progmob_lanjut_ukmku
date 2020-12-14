package id.alin_gotama.ukmku.MyRecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.alin_gotama.ukmku.Anggota_edit;
import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.UKMActivity;

public class CustomAdapterAnggota extends RecyclerView.Adapter<CustomAdapterAnggota.viewHolder> {

    public static List<Anggota> anggotas;
    private Context context;
    private Activity activity;
    private Long ukm_id;

    public CustomAdapterAnggota(List<Anggota> anggotas, Context context,Activity activity,Long ukm_id) {
        CustomAdapterAnggota.anggotas = anggotas;
        this.context = context;
        this.activity = activity;
        this.ukm_id = ukm_id;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_anggota_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        holder.tvNama.setText(anggotas.get(position).getAnggota_nama());
        holder.tvNomor.setText(anggotas.get(position).getAnggota_nomor());
        holder.tvNIM.setText(anggotas.get(position).getAnggota_nim());

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, anggotas.get(position).getAnggota_nama(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Anggota_edit.class);
                intent.putExtra(Anggota_edit.Anggota_id,anggotas.get(position).getAnggota_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Anggota_edit.UKM_ID,ukm_id);
                activity.startActivityForResult(intent,UKMActivity.UPDATE);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DELETE", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return anggotas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama,tvNIM,tvNomor;
        public LinearLayout llRow;
        public Button btnEdit,btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.cvAnggotaRowNama);
            this.tvNIM = itemView.findViewById(R.id.cvAnggotaRowNIM);
            this.tvNomor = itemView.findViewById(R.id.cvAnggotaRowNomorHP);
            this.llRow = itemView.findViewById(R.id.llAnggotaRow);
            this.btnEdit = itemView.findViewById(R.id.btnAnggotaEdit);
            this.btnDelete = itemView.findViewById(R.id.btnAnggotaDelete);
        }
    }

}
