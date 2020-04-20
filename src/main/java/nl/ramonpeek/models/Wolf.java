package nl.ramonpeek.models;

import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Wolf {

    @NotNull
    private int id;
    @NotNull
    private WolfType type;
    @NotNull
    private String firstName;

    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private Gender gender;
    @NotNull
    private Date birthDate;

    private Location location;

    public Wolf() {}

    public Wolf(int id, WolfType type, String firstName, String middleName, String lastName, Gender gender, Date birthDate, Location location) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.location = location;
    }

    public Wolf(int id, WolfType type, String firstName, String middleName, String lastName, Gender gender, Date birthDate) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Wolf(int id, WolfType type, String firstName, String lastName, Gender gender, Date birthDate, Location location) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.location = location;
    }

    public Wolf(int id, WolfType type, String firstName, String lastName, Gender gender, Date birthDate) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WolfType getType() {
        return type;
    }

    public void setType(WolfType type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
