package ast.expr;

import ast.CodeVisitor;

public class StringConstExpr implements Expr {
    public String value;

    public StringConstExpr(String value) {
        this.value = value;
    }

    @Override
    public String accept(CodeVisitor v) {
        return v.visit(this);
    }

}
