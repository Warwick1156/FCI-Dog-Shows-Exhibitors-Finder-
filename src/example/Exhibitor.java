package example;

import java.util.ArrayList;
import java.util.List;

public class Exhibitor {
    String firstName;

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getKey() {
        return key;
    }

    public List<Integer> getStartNumbers() {
        return startNumbers;
    }

    public String getSummary() {
        return String.join(" ", key, startNumbers.toString());
    }

    String surname;
    String lastName;
    String country;

    String key;
    List<Integer> startNumbers = new ArrayList<>();

    Exhibitor(String firstName, String surname, String lastName, String country, String startNumber, String key) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.country = country;
        this.key = key;

        addStartNumber(startNumber);
    }

    public void addStartNumber(String startNumber) {
        startNumbers.add(Integer.valueOf(startNumber));
    }
}