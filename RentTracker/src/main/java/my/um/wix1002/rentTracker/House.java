package my.um.wix1002.rentTracker;

/**
 * Subclass representing a rental house unit.
 */
public class House extends Property {
    public House(String unitId, String password, String tenantName, double monthlyRent) {
        super(unitId, password, tenantName, monthlyRent);
    }

    @Override
    public String getUnitSummary() {
        return "Unit " + getUnitId() + " | Tenant: " + getTenantName() + " | Rent: " + getMonthlyRent();
    }
}