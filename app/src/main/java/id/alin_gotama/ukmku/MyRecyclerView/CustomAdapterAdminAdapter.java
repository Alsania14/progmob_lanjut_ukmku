package id.alin_gotama.ukmku.MyRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.alin_gotama.ukmku.DetailAdminUKM;
import id.alin_gotama.ukmku.MyHelper.ReadFromLocalStorage;
import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class CustomAdapterAdminAdapter extends RecyclerView.Adapter<CustomAdapterAdminAdapter.ViewHolder> {
    public static List<UKM> ukms;
    private Context context;


    public CustomAdapterAdminAdapter(List ukms,Context context) {
        CustomAdapterAdminAdapter.ukms = ukms;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ukmrow,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Bitmap bitmap = ReadFromLocalStorage.readImage(context,ukms.get(position).getImage_name());
        holder.ivCover.setImageBitmap(bitmap);
        holder.tv1.setText(ukms.get(position).getUkm_nama());
        holder.tv2.setText(ukms.get(position).getDescription());
        holder.cvUKMrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAdminUKM.class);
                intent.putExtra(DetailAdminUKM.MODEL,ukms.get(position).ukm_id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                DetailAdminUKM.customAdapterAdminAdapter = CustomAdapterAdminAdapter.this;
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ukms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1,tv2;
        public ImageView ivCover;
        public CardView cvUKMrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv1 = itemView.findViewById(R.id.tvUKM1);
            this.tv2 = itemView.findViewById(R.id.tvUKM2);
            this.ivCover = itemView.findViewById(R.id.ivUKMrow);
            this.cvUKMrow = itemView.findViewById(R.id.cvukmrow);
        }
    }
}
