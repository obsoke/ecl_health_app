package cs.ecl.karpAndMamidala.tawmylf.Models;

import java.util.Date;

public class ExerciseItem {
    private long id;
    private Date date;
    private String type;
    private int duration;
    private int intensity;

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

    public String getType() {
        return type;
    }

    public void setType(String t) {
        this.type = t;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int d) {
        this.duration = d;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int i) {
        this.intensity = i;
    }

    @Override
    public String toString() {
        return "Physical Exercise Entry for " + date.toString();
    }
}
