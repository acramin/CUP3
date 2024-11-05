package ast.expr;

import ast.CodeVisitor;

public class LEExpr implements BoolExpr {
    public Expr e1;
    public Expr e2;

    public LEExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Boolean accept(CodeVisitor v) {
        return v.visit(this);
    }

}
