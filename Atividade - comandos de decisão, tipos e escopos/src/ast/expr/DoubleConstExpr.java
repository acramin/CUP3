package ast.expr;

import ast.CodeVisitor;

public class DoubleConstExpr implements Expr {
    public Double value;

    public DoubleConstExpr(Double value) {
        this.value = value;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }

}
