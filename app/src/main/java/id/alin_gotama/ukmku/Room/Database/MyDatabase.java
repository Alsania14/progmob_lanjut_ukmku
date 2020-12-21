package id.alin_gotama.ukmku.Room.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.alin_gotama.ukmku.Room.Dao.MyDao;
import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

@Database(entities = {UKM.class, Anggota.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao myDao();

    private static MyDatabase INSTANCE;

    public static MyDatabase createDatabase(Context context){
        synchronized(MyDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,MyDatabase.class,"db_class")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}
