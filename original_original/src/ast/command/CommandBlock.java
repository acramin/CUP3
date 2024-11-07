package ast.command;
import ast.CodeVisitor;
import ast.Interpreter;

public class CommandBlock implements Command {
    private CommandList commands;

    public CommandBlock(CommandList commands) {
        this.commands = commands;
    }

    @Override
    public void accept(CodeVisitor visitor) {
        if (visitor instanceof Interpreter) {
            Interpreter interpreter = (Interpreter) visitor;
            interpreter.enterScope(); // Entrar no escopo do bloco

            // Executar cada comando dentro do bloco
            commands.accept(visitor);

            interpreter.exitScope(); // Sair do escopo do bloco
        }
    }
}
