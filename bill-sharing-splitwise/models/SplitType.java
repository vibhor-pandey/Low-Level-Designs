package models;

public enum SplitType {
    EXACT("EXACT"),
    EQUAL("EQUAL"),
    PERCENTAGE("PERCENTAGE");

    private final String value;

    private SplitType(String value) {
        this.value = value;
    }

    public boolean isEquals(final String value) {
        return this.value.equalsIgnoreCase(value);
    }

}
