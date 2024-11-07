package ast.command;
import ast.CodeVisitor;
//import ast.Interpreter;
import ast.Interpreter;

public class CommandBlock implements Command {
    private CommandList commands;

    public CommandBlock(CommandList commands) {
        this.commands = commands;
    }

    @Override
    public void accept(CodeVisitor v) {
        //v.visit(this);
        if (v instanceof Interpreter) {
            Interpreter interpreter = (Interpreter) v;
            interpreter.enterScope(); // Entrar no escopo do bloco
            commands.accept(v); // Executar cada comando dentro do bloco
            interpreter.exitScope(); // Sair do escopo do bloco
        }
    }
}
