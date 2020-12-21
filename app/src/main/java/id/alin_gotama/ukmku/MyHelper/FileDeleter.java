package id.alin_gotama.ukmku.MyHelper;

import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class FileDeleter {

    public static boolean Delete(String pathAndImage) {
        File fdelete = new File(pathAndImage);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.d("delete", "sukses");
                return true;
            } else {
                Log.d("delete", "gagal");
                return false;
            }
        }
        return false;
    }
}
