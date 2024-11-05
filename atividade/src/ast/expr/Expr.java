package ast.expr;

import ast.CodeVisitor;

public interface Expr {

    public Double accept(CodeVisitor v);
    
}
