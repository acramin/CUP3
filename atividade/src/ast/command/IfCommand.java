package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class IfCommand implements Command {
    public BoolExpr boolExpr;
    public Command command;

    public IfCommand(BoolExpr boolExpr, Command command) {
        this.boolExpr = boolExpr;
        this.command = command;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }

}
