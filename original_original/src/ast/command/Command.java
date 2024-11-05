package ast.command;

import ast.CodeVisitor;

public interface Command {
    public void accept(CodeVisitor v);
}
