package ast.command;

import ast.CodeVisitor;

public class CommandBlock implements Command
{
    private CommandList commandList;

    public CommandBlock(CommandList commandList) {
        this.commandList = commandList; 
    }

    public CommandList getCommandList() {
        return commandList;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
