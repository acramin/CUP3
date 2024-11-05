package ast.expr;

import ast.CodeVisitor;

public class SinExpr implements Expr{
    public Expr expr;

    public SinExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}