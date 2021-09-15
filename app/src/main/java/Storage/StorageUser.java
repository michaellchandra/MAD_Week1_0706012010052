package Storage;

import android.os.Parcel;
import android.os.Parcelable;

public class StorageUser implements Parcelable {

    private String userName;
    private int userAge;
    private String userAddress;

    public StorageUser(String userName, int userAge, String userAddress) {
        this.userName = userName;
        this.userAge = userAge;
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public static Creator<StorageUser> getCREATOR() {
        return CREATOR;
    }

    protected StorageUser(Parcel in) {
        userName = in.readString();
        userAge = in.readInt();
        userAddress = in.readString();
    }

    public static final Creator<StorageUser> CREATOR = new Creator<StorageUser>() {
        @Override
        public StorageUser createFromParcel(Parcel in) {
            return new StorageUser(in);
        }

        @Override
        public StorageUser[] newArray(int size) {
            return new StorageUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(userAge);
        dest.writeString(userAddress);
    }
}
