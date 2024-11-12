package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public interface Expr {

    public Const accept(CodeVisitor v);
    
}
