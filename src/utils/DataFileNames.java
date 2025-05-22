package utils;

public enum DataFileNames {
    COACH("coaches.csv"),
    COMP_STATS("compStatsRecord.csv"),
    MEMBERS("members.csv"),
    PAYMENT_PAUSES("paymentPauses.csv"),
    PAYMENTS("payments.csv"),
    TRAINING_STATS("trainingStats.csv");

    public final String label;
    DataFileNames(String label) {
        this.label = label;
    }
}
