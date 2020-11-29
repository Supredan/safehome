package model;

public class Apartment extends LibraryModel{
    private String MonthlyRate;
    private int BuildingNo;

    public Apartment() {

    }

    public Apartment(String monthlyRate, int buildingNo) {
        MonthlyRate = monthlyRate;
        BuildingNo = buildingNo;
    }

    public String getMonthlyRate() {
        return MonthlyRate;
    }

    public void setMonthlyRate(String monthlyRate) {
        MonthlyRate = monthlyRate;
    }

    public int getBuildingNo() {
        return BuildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        BuildingNo = buildingNo;
    }
}
