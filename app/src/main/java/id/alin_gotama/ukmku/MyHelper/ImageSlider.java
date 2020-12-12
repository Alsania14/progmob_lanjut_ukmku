package id.alin_gotama.ukmku.MyHelper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import id.alin_gotama.ukmku.R;

public class ImageSlider {
    private ImageView iv;
    private Context context;
    private int[] imagesNya;
    private int angka = 0;
    private int delaynya;

    public ImageSlider(final Context context, ImageView imageView, final int delay, final int... images){
        this.context = context;
        this.iv = imageView;
        this.imagesNya = images;
        this.delaynya = delay;
        final int panjang = images.length;



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    if(angka != panjang){
                        Log.d("nilai",String.valueOf(angka));
                        Animation aniZoom = AnimationUtils.loadAnimation(context,R.anim.zoom_in);
                        iv.setAnimation(aniZoom);
                        iv.setImageDrawable(context.getResources().getDrawable(imagesNya[angka]));
                        angka+=1;
                    }else{
                        angka=0;
                    }

                    new Handler().postDelayed(this,delaynya);
            }
        },delaynya);
    }

}
