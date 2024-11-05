package ast.expr;

import ast.CodeVisitor;

public interface Expr {

    public Object accept(CodeVisitor v);
    
}
