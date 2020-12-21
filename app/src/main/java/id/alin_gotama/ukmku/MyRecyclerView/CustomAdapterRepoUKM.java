package id.alin_gotama.ukmku.MyRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Entity.UKM;
import id.alin_gotama.ukmku.UKMActivity;

public class CustomAdapterRepoUKM extends RecyclerView.Adapter<CustomAdapterRepoUKM.CustomViewHolder> {
    public static List<UKM> Arukms;
    private Context context;
    private UKM ukm;

    public CustomAdapterRepoUKM(List<UKM> arukms, Context context) {
        Arukms = arukms;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ukmrow,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        ukm = Arukms.get(position);
        holder.iv.setImageBitmap(ReadFromLocalStorage.readImage(context,Arukms.get(position).getImage_name()));
        holder.tvnama.setText(Arukms.get(position).getUkm_nama());
        holder.tvDescription.setText(Arukms.get(position).getDescription());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UKMActivity.class);
                intent.putExtra(UKMActivity.UKM_ID,Arukms.get(position).getUkm_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.Arukms.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView tvnama,tvDescription;
        private ImageView iv;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cv = itemView.findViewById(R.id.cvukmrow);
            this.iv = itemView.findViewById(R.id.ivUKMrow);
            this.tvnama = itemView.findViewById(R.id.tvUKM1);
            this.tvDescription = itemView.findViewById(R.id.tvUKM2);
        }
    }
}
