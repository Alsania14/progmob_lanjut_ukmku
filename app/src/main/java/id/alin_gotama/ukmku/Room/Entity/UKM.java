package id.alin_gotama.ukmku.Room.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UKM {

    @PrimaryKey(autoGenerate = true)
    public long ukm_id;
    public String ukm_nama;
    public String Description;
    public String ukm_link;
    public String image_name;

    public String getUkm_link() {
        return ukm_link;
    }

    public void setUkm_link(String ukm_link) {
        this.ukm_link = ukm_link;
    }

    public long getUkm_id() {
        return ukm_id;
    }

    public void setUkm_id(long ukm_id) {
        this.ukm_id = ukm_id;
    }

    public String getUkm_nama() {
        return ukm_nama;
    }

    public void setUkm_nama(String ukm_nama) {
        this.ukm_nama = ukm_nama;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
