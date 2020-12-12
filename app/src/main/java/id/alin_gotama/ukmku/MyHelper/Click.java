package id.alin_gotama.ukmku.MyHelper;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.cardview.widget.CardView;

import id.alin_gotama.ukmku.R;

public class Click {
    private static CardView object;
    private static Context context;

    public static void Clicking(CardView paramObject,Context context) {
        object = paramObject;
        context = context;

        Animation aniClick = AnimationUtils.loadAnimation(context, R.anim.click);
        object.setAnimation(aniClick);
        Animation aniUnClick = AnimationUtils.loadAnimation(context,R.anim.zoom_out);
        object.setAnimation(aniUnClick);
    }
}
