package cs.ecl.karpAndMamidala.liveforever.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private long id;
    private String name;
    private int gender;
    private String address;
    private String phone;
    private String emerg_name;
    private String emerg_address;
    private String emerg_phone;

    public User() {
        // empty ctor
    }

    private User(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.gender = in.readInt();
        this.address = in.readString();
        this.phone = in.readString();
        this.emerg_name = in.readString();
        this.emerg_address = in.readString();
        this.emerg_phone = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.gender);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.emerg_name);
        dest.writeString(this.emerg_address);
        dest.writeString(this.emerg_phone);
    }
}
