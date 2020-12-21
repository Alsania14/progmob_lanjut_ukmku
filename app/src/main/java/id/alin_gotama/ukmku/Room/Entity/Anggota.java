package id.alin_gotama.ukmku.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey( entity = UKM.class,parentColumns = "ukm_id",childColumns = "ukm_id_fk",
onDelete = ForeignKey.CASCADE))
public class Anggota {

    @PrimaryKey(autoGenerate = true)
    public long anggota_id;

    @ColumnInfo(index = true)
    public long ukm_id_fk;
    public String anggota_nama;
    public String anggota_nim;
    public String anggota_nomor;

    public long getAnggota_id() {
        return anggota_id;
    }

    public void setAnggota_id(long anggota_id) {
        this.anggota_id = anggota_id;
    }

    public long getUkm_id_fk() {
        return ukm_id_fk;
    }

    public void setUkm_id_fk(long ukm_id_fk) {
        this.ukm_id_fk = ukm_id_fk;
    }

    public String getAnggota_nama() {
        return anggota_nama;
    }

    public void setAnggota_nama(String anggota_nama) {
        this.anggota_nama = anggota_nama;
    }

    public String getAnggota_nim() {
        return anggota_nim;
    }

    public void setAnggota_nim(String anggota_nim) {
        this.anggota_nim = anggota_nim;
    }

    public String getAnggota_nomor() {
        return anggota_nomor;
    }

    public void setAnggota_nomor(String anggota_nomor) {
        this.anggota_nomor = anggota_nomor;
    }
}
