package ast.command;

import ast.CodeVisitor;
import ast.expr.Expr;

public class PrintCommand implements Command {
    public Expr expr;

    public PrintCommand(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }

}
