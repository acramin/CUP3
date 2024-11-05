package ast.expr;

import ast.CodeVisitor;

public class GTExpr implements BoolExpr {
    public Expr e1;
    public Expr e2;

    public GTExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Boolean accept(CodeVisitor v) {
        return v.visit(this);
    }

}
