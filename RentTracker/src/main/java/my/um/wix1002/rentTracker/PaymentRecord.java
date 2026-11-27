package my.um.wix1002.rentTracker;

/**
 * Stores monthly rental payment details and receipt screenshot file path.
 */
public class PaymentRecord {
    private String unitId;
    private String month;
    private double amount;
    private String status; // "UNPAID", "PENDING", or "VERIFIED"
    private String proofImagePath;

    public PaymentRecord(String unitId, String month, double amount, String status, String proofImagePath) {
        this.unitId = unitId;
        this.month = month;
        this.amount = amount;
        this.status = status;
        this.proofImagePath = proofImagePath;
    }

    public String getUnitId() {
        return unitId;
    }

    public String getMonth() {
        return month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProofImagePath() {
        return proofImagePath;
    }

    public void setProofImagePath(String proofImagePath) {
        this.proofImagePath = proofImagePath;
    }

    public String getPaymentSummary() {
        return "Unit: " + unitId
                + " | Month: " + month
                + " | Amount: " + amount
                + " | Status: " + status
                + " | Receipt: " + (proofImagePath == null || proofImagePath.trim().isEmpty() ? "None" : proofImagePath);
    }
}