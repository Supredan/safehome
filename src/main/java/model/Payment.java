package model;

public class Payment {
    private int LeaseNo;
    private String PaymentDate;
    private String AmountPaid;
    private String Late;

    public Payment() {
    }

    public Payment(int leaseNo, String paymentDate, String amountPaid, String late) {
        LeaseNo = leaseNo;
        PaymentDate = paymentDate;
        AmountPaid = amountPaid;
        Late = late;
    }

    public int getLeaseNo() {
        return LeaseNo;
    }

    public void setLeaseNo(int leaseNo) {
        LeaseNo = leaseNo;
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
}
