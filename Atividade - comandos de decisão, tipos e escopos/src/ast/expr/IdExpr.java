package ast.expr;

import ast.CodeVisitor;

public class IdExpr implements Expr {
    public String name;
    public Double value;

    public IdExpr(String name) {
        this(name, 0.0);
    }

    public IdExpr(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}
