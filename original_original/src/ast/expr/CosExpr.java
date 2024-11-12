package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class CosExpr implements Expr
{
    public Expr expr;

    public CosExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}