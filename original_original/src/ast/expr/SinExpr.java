package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class SinExpr implements Expr{
    public Expr expr;

    public SinExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}