package model;

public class Lease extends LibraryModel{
    private String StartDate;
    private String EndDate;
    private int ClientNo;
    private int ApartmentNo;

    public Lease() {
    }

    public Lease(String startDate, String endDate, int clientNo, int apartmentNo) {
        StartDate = startDate;
        EndDate = endDate;
        ClientNo = clientNo;
        ApartmentNo = apartmentNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public int getClientNo() {
        return ClientNo;
    }

    public void setClientNo(int clientNo) {
        ClientNo = clientNo;
    }

    public int getApartmentNo() {
        return ApartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        ApartmentNo = apartmentNo;
    }
}
