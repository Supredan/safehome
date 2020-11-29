package model;

public class Maintenance extends LibraryModel{
    private int ApartmentNo;
    private String StartDate;
    private String ProblemDescription;
    private String Type;
    private String Resolution;
    private String ResolutionDate;
    private String BuildingExpense;
    private String TenantExpense;

    public Maintenance() {
    }

    public Maintenance(int apartmentNo, String startDate, String problemDescription, String type, String resolution, String resolutionDate, String buildingExpense, String tenantExpense) {
        ApartmentNo = apartmentNo;
        StartDate = startDate;
        ProblemDescription = problemDescription;
        Type = type;
        Resolution = resolution;
        ResolutionDate = resolutionDate;
        BuildingExpense = buildingExpense;
        TenantExpense = tenantExpense;
    }

    public int getApartmentNo() {
        return ApartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        ApartmentNo = apartmentNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getProblemDescription() {
        return ProblemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        ProblemDescription = problemDescription;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getResolution() {
        return Resolution;
    }

    public void setResolution(String resolution) {
        Resolution = resolution;
    }

    public String getResolutionDate() {
        return ResolutionDate;
    }

    public void setResolutionDate(String resolutionDate) {
        ResolutionDate = resolutionDate;
    }

    public String getBuildingExpense() {
        return BuildingExpense;
    }

    public void setBuildingExpense(String buildingExpense) {
        BuildingExpense = buildingExpense;
    }

    public String getTenantExpense() {
        return TenantExpense;
    }

    public void setTenantExpense(String tenantExpense) {
        TenantExpense = tenantExpense;
    }
}
