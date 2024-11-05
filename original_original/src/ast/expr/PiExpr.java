package ast.expr;

import ast.CodeVisitor;

public class PiExpr implements Expr {
    public Double value;

    public PiExpr(Double value) {
        this.value = value;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}
