package cs.ecl.karpAndMamidala.tawmylf.Models;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private long id;
    private String name;
    private int gender;
    private String address;
    private String phone;
    private String emerg_name;
    private String emerg_address;
    private String emerg_phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int g) {
        this.gender = g;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        this.address = a;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String p) {
        this.phone = p;
    }

    public String getEmergName() {
        return emerg_name;
    }

    public void setEmergName(String e) {
        this.emerg_name = e;
    }

    public String getEmergAddress() {
        return emerg_address;
    }

    public void setEmergAddress(String e) {
        this.emerg_address = e;
    }

    public String getEmergPhone() {
        return emerg_phone;
    }

    public void setEmergPhone(String e) {
        this.emerg_phone = e;
    }

    @Override
    public String toString() {
        return name;
    }
}
