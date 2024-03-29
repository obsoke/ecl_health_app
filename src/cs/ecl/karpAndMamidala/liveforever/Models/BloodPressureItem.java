package cs.ecl.karpAndMamidala.liveforever.Models;

import java.util.Date;

public class BloodPressureItem {
    private long id;
    private Date date;
    private float systolic_pressure;
    private float diastolic_pressure;
    private float heartrate;

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

    public float getSystolicPressure() {
        return systolic_pressure;
    }

    public void setSystolicPressure(float p) {
        this.systolic_pressure = p;
    }

    public float getDiastolicPressure() {
        return diastolic_pressure;
    }

    public void setDiastolicPressure(float p) {
        this.diastolic_pressure = p;
    }

    public float getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(float hr) {
        this.heartrate = hr;
    }

    @Override
    public String toString() {
        return "Blood Pressure Entry for " + date.toString();
    }
}
