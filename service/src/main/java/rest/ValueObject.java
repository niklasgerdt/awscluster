package rest;

public class ValueObject {
    private final String name;
    private final String value;

    public ValueObject(final String name) {
        this.name = name;
        value = "Hello " + this.name;
    }

    public String getValue() {
        return value;
    }
}
