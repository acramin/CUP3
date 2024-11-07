package ast.expr;

import ast.CodeVisitor;
import ast.TypedValue;

public class IdExpr implements Expr {
    public int name;
    public TypedValue value;

    public IdExpr(String name) {
        this(name, null);
    }

    public IdExpr(String name, TypedValue value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}
