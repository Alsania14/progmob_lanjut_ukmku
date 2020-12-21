package id.alin_gotama.ukmku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import id.alin_gotama.ukmku.MyHelper.Click;
import id.alin_gotama.ukmku.MyHelper.ImageSlider;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv1,iv2;
    private int angka = 1;
    private CardView cvRepoUKM,cvDaftarAnggota;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.iv1 = findViewById(R.id.ivHome1);
        this.iv2 = findViewById(R.id.ivHome2);
        this.cvRepoUKM = findViewById(R.id.cvHome1);
        this.cvDaftarAnggota = findViewById(R.id.cvHome2);

        int[] image = {R.drawable.pencak_silat2, R.drawable.pencak_silat,R.drawable.logo_ti};
        ImageSlider imageSlider = new ImageSlider(this,iv1,2000,image);
        ImageSlider imageSlider2 = new ImageSlider(this,iv2,5000,image);

        this.cvRepoUKM.setOnClickListener(this);
        this.cvDaftarAnggota.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cvHome1){
            Click.Clicking(this.cvRepoUKM,this);
            Intent intent = new Intent(HomeActivity.this,RepoUKMActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.cvHome2){
            Click.Clicking(this.cvDaftarAnggota,this);
            Intent intent = new Intent(HomeActivity.this,AdminActivity.class);
            startActivity(intent);
        }
    }
}
