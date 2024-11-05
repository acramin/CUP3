package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class WhileCommand implements Command {
    public BoolExpr boolExpr;
    public Command command;

    public WhileCommand(BoolExpr e, Command c) {
        this.boolExpr = e;
        this.command = c;
    }


    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }

}
