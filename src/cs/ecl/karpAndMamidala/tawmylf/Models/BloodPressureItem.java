package cs.ecl.karpAndMamidala.tawmylf.Models;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
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

    public void setDate(int utcTime) {
        this.date = new Date(utcTime);
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
