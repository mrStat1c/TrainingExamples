package model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Client {

    String firstName;
    String lastName;
    byte age;
    Gender gender;
    Phone phone;
    List<Address> addresses;
    @SerializedName("good_man")
    @JsonAdapter(value = BooleanAdapter.class)
    boolean goodMan;

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone=" + phone +
                ", addresses=" + addresses +
                ", goodMan=" + goodMan +
                '}';
    }
}
