package cs.ecl.karpAndMamidala.tawmylf.Models;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
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

    public void setDate(int utcTime) {
        this.date = new Date(utcTime);
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
