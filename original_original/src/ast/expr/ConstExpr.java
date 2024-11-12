package ast.expr;

import ast.CodeVisitor;

public class ConstExpr implements Expr {
    public Double value;

    public ConstExpr(Double value) {
        this.value = value;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }

}
