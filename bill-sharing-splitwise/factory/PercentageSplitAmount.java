package factory;

public class PercentageSplitAmount extends SplitAmount {
    private double percentage;

    public PercentageSplitAmount(double amount, double percentage) {
        super(amount);
        this.percentage = percentage;
    }

    public PercentageSplitAmount(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
