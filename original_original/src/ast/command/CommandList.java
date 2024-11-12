package ast.command;

import ast.CodeVisitor;

public class CommandList implements Command
{
    public CommandList commandList;
    public Command command;

    public CommandList(Command command, CommandList commandList) {
        this.commandList = commandList; 
        this.command = command;
    }

    public CommandList (Command command) {
        this(command, null);
    }

    public CommandList getCommandList() {
        return commandList;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
