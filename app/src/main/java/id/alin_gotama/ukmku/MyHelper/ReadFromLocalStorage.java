package id.alin_gotama.ukmku.MyHelper;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;

import id.alin_gotama.ukmku.R;

public class ReadFromLocalStorage {
    private String path;
    private String image;
    private Context context;

    public static Bitmap readImage(Context context,String pathandImage) {

        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        try{
            File f = new File(pathandImage);
            fileInputStream = new FileInputStream(f);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
        } catch(Exception e) {
            Log.d("error",e.getMessage());
        }

        return bitmap;
    }

}
