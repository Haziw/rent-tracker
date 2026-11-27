package my.um.wix1002.rentTracker;

/**
 * Manages rental properties and monthly payment records using plain Java arrays.
 */
public class RentalManager {
    private final Property[] properties;
    private int propertyCount;

    private final PaymentRecord[] payments;
    private int paymentCount;

    public RentalManager(int maxUnits, int maxPayments) {
        this.properties = new Property[maxUnits];
        this.propertyCount = 0;
        this.payments = new PaymentRecord[maxPayments];
        this.paymentCount = 0;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public Property getPropertyAt(int index) {
        if (index < 0 || index >= propertyCount) {
            return null;
        }
        return properties[index];
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public Property findUnit(String unitId) {
        if (unitId == null) {
            return null;
        }
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].getUnitId().equalsIgnoreCase(unitId.trim())) {
                return properties[i];
            }
        }
        return null;
    }

    public boolean authenticateTenant(String unitId, String password) {
        Property unit = findUnit(unitId);
        return unit != null && unit.checkPassword(password);
    }

    public boolean addHouseUnit(String unitId, String password, String tenantName, double monthlyRent, String startMonth) {
        if (propertyCount >= properties.length || findUnit(unitId) != null) {
            return false;
        }
        properties[propertyCount++] = new House(unitId, password, tenantName, monthlyRent);
        if (startMonth != null && !startMonth.trim().isEmpty() && paymentCount < payments.length) {
            payments[paymentCount++] = new PaymentRecord(unitId, startMonth.trim().toUpperCase(), monthlyRent, "UNPAID", "");
        }
        return true;
    }

    public boolean deleteUnit(String unitId) {
        int foundIndex = -1;
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].getUnitId().equalsIgnoreCase(unitId)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            return false;
        }
        for (int i = foundIndex; i < propertyCount - 1; i++) {
            properties[i] = properties[i + 1];
        }
        properties[--propertyCount] = null;
        return true;
    }

    public PaymentRecord findPayment(String unitId, String month) {
        if (unitId == null || month == null) {
            return null;
        }
        for (int i = 0; i < paymentCount; i++) {
            if (payments[i].getUnitId().equalsIgnoreCase(unitId.trim())
                    && payments[i].getMonth().equalsIgnoreCase(month.trim())) {
                return payments[i];
            }
        }
        return null;
    }

    public boolean submitPaymentProof(String unitId, String month, String proofImagePath) {
        Property unit = findUnit(unitId);
        if (unit == null || month == null || proofImagePath == null || proofImagePath.trim().isEmpty()) {
            return false;
        }

        String normalizedMonth = month.trim().toUpperCase();
        PaymentRecord existing = findPayment(unitId, normalizedMonth);
        if (existing != null) {
            existing.setAmount(unit.getMonthlyRent());
            existing.setProofImagePath(proofImagePath.trim());
            existing.setStatus("PENDING");
            return true;
        }

        if (paymentCount >= payments.length) {
            return false;
        }
        payments[paymentCount++] = new PaymentRecord(unitId, normalizedMonth, unit.getMonthlyRent(), "PENDING", proofImagePath.trim());
        return true;
    }

    public boolean verifyPayment(String unitId, String month) {
        PaymentRecord record = findPayment(unitId, month);
        if (record == null) {
            return false;
        }
        record.setStatus("VERIFIED");
        return true;
    }

    public String getUnitOverallStatus(String unitId) {
        boolean hasPending = false;
        boolean hasUnpaid = false;
        boolean hasRecord = false;

        for (int i = 0; i < paymentCount; i++) {
            if (payments[i].getUnitId().equalsIgnoreCase(unitId)) {
                hasRecord = true;
                String status = payments[i].getStatus();
                if ("PENDING".equalsIgnoreCase(status)) {
                    hasPending = true;
                } else if ("UNPAID".equalsIgnoreCase(status)) {
                    hasUnpaid = true;
                }
            }
        }

        if (!hasRecord || hasUnpaid) {
            return "UNPAID";
        }
        if (hasPending) {
            return "PENDING";
        }
        return "VERIFIED";
    }
}