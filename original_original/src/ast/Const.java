package ast;

public class Const
{
    private Object value;
    private Type type;

    public Const(Object value, Type type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
