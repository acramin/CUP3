package ast;

public class TypedValue {
    private Object value;
    private Class<?> type;

    public TypedValue(Double value) {
        this.value = value;
        this.type = Double.class;
    }

    public TypedValue(String value) {
        this.value = value;
        this.type = String.class;
    }

    public Double asDouble() {
        if (type == Double.class) {
            return (Double) value;
        } else {
            throw new RuntimeException("O valor não é do tipo Double.");
        }
    }

    public String asString() {
        if (type == String.class) {
            return (String) value;
        } else {
            throw new RuntimeException("O valor não é do tipo String.");
        }
    }

    public boolean isDouble() {
        return type == Double.class;
    }

    public boolean isString() {
        return type == String.class;
    }

    public Object getValue() {
        return value;
    }
}
