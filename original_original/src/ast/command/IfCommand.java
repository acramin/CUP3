package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class IfCommand implements Command {
    public BoolExpr boolExpr;
    public Command command, elseCommand;

    public IfCommand(BoolExpr boolExpr, Command command) {
        this.boolExpr = boolExpr;
        this.command = command;
        this.elseCommand = null;
    }

    public IfCommand(BoolExpr boolExpr, Command command, Command elseCommand) {
        this.boolExpr = boolExpr;
        this.command = command;
        this.elseCommand = elseCommand;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
