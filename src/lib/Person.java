package lib;

import java.util.Random;
import java.util.UUID;

public class Person {
    public String id;
    public String firstName;
    public String lastName;
    public int age;
    public String phoneNumber;
    public String address;
    public String[] personRecord;

    public Person(String firstName, String lastName, String phoneNumber,String address, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;

        this.id = constructID(firstName, lastName);

        this.personRecord = new String[]{this.id,firstName, lastName, String.valueOf(phoneNumber),address, String.valueOf(age)};

    }
    public String constructID(String firstName, String lastName){
        String subFirst = firstName.substring(0, 2);
        String subLast = lastName.substring(0, 2);

        Random ran = new Random();
        int conInt = ran.nextInt(1000, 9999);
        String convertInt = String.valueOf(conInt);

        return subFirst + subLast + convertInt;
    }

}
