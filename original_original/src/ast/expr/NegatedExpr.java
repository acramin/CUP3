package ast.expr;

import ast.CodeVisitor;

public class NegatedExpr implements Expr{
    public Expr expr;

    public NegatedExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}
