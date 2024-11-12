package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class ConstExpr implements Expr
{
    public Const value;

    public ConstExpr(Const value) {
        this.value = value;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
