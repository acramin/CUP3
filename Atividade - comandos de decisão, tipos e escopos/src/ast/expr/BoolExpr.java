package ast.expr;

import ast.CodeVisitor;

public interface BoolExpr {

    public Boolean accept(CodeVisitor v);

}
