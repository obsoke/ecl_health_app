package cs.ecl.karpAndMamidala.tawmylf.Models;

import android.widget.Toast;

import java.util.Date;

public class WeightItem {
    private long id;
    private Date date;
    private float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(long utcTime) {
        this.date = new Date(utcTime * 1000);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float w) {
        this.weight = w;
    }

    @Override
    public String toString() {
        return "Weight Entry for " + date.toString();
    }
}
