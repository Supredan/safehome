package model;

public class Client extends LibraryModel {
    private String ClientName;
    private String Phone;
    private String StartDate, EndDate,PaymentDate, AmountPaid,Late;
    private int ApartmentNo;

    public Client() {
    }

    public Client(String clientName, String phone, String startDate, String endDate, String paymentDate, String amountPaid, String late, int apartmentNo) {
        ClientName = clientName;
        Phone = phone;
        StartDate = startDate;
        EndDate = endDate;
        PaymentDate = paymentDate;
        AmountPaid = amountPaid;
        Late = late;
        ApartmentNo = apartmentNo;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        AmountPaid = amountPaid;
    }

    public String getLate() {
        return Late;
    }

    public void setLate(String late) {
        Late = late;
    }

    public int getApartmentNo() {
        return ApartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        ApartmentNo = apartmentNo;
    }
}
