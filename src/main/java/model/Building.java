package model;

public class Building extends LibraryModel {

    private String Street;
    private String City;
    private String State;
    private String Zipcode;
    private String Phone;
    private int ManagerNo;

    public Building() {}
    public Building(String street, String city, String state, String zipcode, String phone, int managerNo) {

        Street = street;
        City = city;
        State = state;
        Zipcode = zipcode;
        Phone = phone;
        ManagerNo = managerNo;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getManagerNo() {
        return ManagerNo;
    }

    public void setManagerNo(int managerNo) {
        ManagerNo = managerNo;
    }
}
