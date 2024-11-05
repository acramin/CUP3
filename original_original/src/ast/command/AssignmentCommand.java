package ast.command;

import ast.CodeVisitor;
import ast.expr.Expr;


public class AssignmentCommand implements Command {
    public String id;
    public Expr expr;
    
    public AssignmentCommand(String id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }

}
