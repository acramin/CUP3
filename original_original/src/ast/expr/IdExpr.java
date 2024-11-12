package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class IdExpr implements Expr {
    public String name;
    public Const value;

    public IdExpr(String name) {
        this(name, null);
    }

    public IdExpr(String name, Const value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
