package my.um.wix1002.rentTracker;

/**
 * Abstract parent class representing a rental house unit.
 */
public abstract class Property {
    private String unitId;
    private String password;
    private String tenantName;
    private double monthlyRent;

    public Property(String unitId, String password, String tenantName, double monthlyRent) {
        this.unitId = unitId;
        this.password = password;
        this.tenantName = tenantName;
        this.monthlyRent = monthlyRent;
    }

    public String getUnitId() {
        return unitId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password != null && this.password.equals(inputPassword);
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public abstract String getUnitSummary();
}