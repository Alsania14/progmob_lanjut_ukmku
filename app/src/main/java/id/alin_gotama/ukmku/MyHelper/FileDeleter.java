package id.alin_gotama.ukmku.MyHelper;

import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class FileDeleter {

    public static boolean Delete(String pathAndImage) {
        File fdelete = new File(pathAndImage);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
