package ast.expr;

import ast.CodeVisitor;

public class CosExpr implements Expr{
    public Expr expr;

    public CosExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Double accept(CodeVisitor v) {
        return v.visit(this);
    }
}