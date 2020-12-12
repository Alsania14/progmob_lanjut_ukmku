package id.alin_gotama.ukmku.Room.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.alin_gotama.ukmku.Room.Entity.Anggota;
import id.alin_gotama.ukmku.Room.Entity.UKM;

@Dao
public interface MyDao {

    @Insert
    Long insertUKM(UKM ukm);

    @Insert
    Long insertAnggota(Anggota anggotas);

    @Query("SELECT * FROM Anggota WHERE anggota_id = 1")
    Anggota ambilanggota();

    @Query("SELECT * FROM UKM")
    List<UKM> ambilSemuaUKM();

    @Query("SELECT * FROM UKM WHERE ukm_id = :idnya")
    UKM ambilSatuUKMid(Long idnya);

    @Update
    void updateSatuUKM(UKM ukm);

    @Query("SELECT * FROM UKM WHERE ukm_nama LIKE :namanya")
    List<UKM> searchEngineUKM(String namanya);

}
