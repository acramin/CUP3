package ast;

import ast.command.AssignmentCommand;
import ast.command.CommandList;
import ast.command.IfCommand;
import ast.command.PrintCommand;
import ast.command.WhileCommand;
import ast.expr.CosExpr;
import ast.expr.DivExpr;
import ast.expr.IdExpr;
import ast.expr.LEExpr;
import ast.expr.LTExpr;
import ast.expr.ModExpr;
import ast.expr.DoubleConstExpr;
import ast.expr.ExpExpr;
import ast.expr.GEExpr;
import ast.expr.GTExpr;
import ast.expr.MulExpr;
import ast.expr.NegatedExpr;
import ast.expr.PiExpr;
import ast.expr.SinExpr;
import ast.expr.SubExpr;
import ast.expr.SumExpr;
import java.util.*;

public class Interpreter implements CodeVisitor {
    // symbolTable é a tabela de símbolos
    private static Stack<HashMap<String, IdExpr>> scopeStack = new Stack<>();

    public Interpreter() {
        // Inicializa com o escopo global
        System.out.println("qtd escopo " + scopeStack.size());
        System.out.println(scopeStack.push(new HashMap<>()));
        //scopeStack.push(new HashMap<>());
        System.out.println("qtd escopo " +scopeStack.size());
    }

    public void enterScope() {
        scopeStack.push(new HashMap<>());
    }

    public void exitScope() {
        if (!scopeStack.isEmpty()) {
            System.out.println("qtd escopo " +scopeStack.size());
            System.out.println(scopeStack.pop());
            //scopeStack.pop();
            System.out.println("qtd escopo " +scopeStack.size());
        } else {
            System.err.println("Erro: tentativa de sair de um escopo inexistente!");
        }
    }

    private IdExpr findInScope(String name) {
        Iterator<HashMap<String, IdExpr>> iterator = scopeStack.iterator();
        System.out.println("it " + iterator);
        for (int i = scopeStack.size(); i >= 0; i--) {
            HashMap<String, IdExpr> scope = scopeStack.get(i-1);
            System.out.println("currentScope: " + scope);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null; // Variável não encontrada em nenhum escopo
    }

    @Override
    public Double visit(IdExpr e) {
        IdExpr idExpr = findInScope(e.name);
        if (idExpr == null) {
            System.err.println("Erro: variável \"" + e.name + "\" não inicializada!");
        }
        return idExpr.value;
    }

    @Override
    public void visit(AssignmentCommand c) {
        Double value = c.expr.accept(this);
        IdExpr idExpr = new IdExpr(c.id, value);
        // Adiciona ou atualiza a variável no escopo atual
        scopeStack.peek().put(c.id, idExpr);
    }

    @Override
    public void visit(IfCommand ifc) {
        //enterScope();
        if (ifc.boolExpr.accept(this)) {
            ifc.command.accept(this);
        } else {
            if (ifc.elseCommand != null) {
                ifc.elseCommand.accept(this);
            }
        }
        //exitScope();
    }

    @Override
    public void visit(WhileCommand w) {
        //enterScope();
        Boolean b = w.boolExpr.accept(this);
        while (b) {
            w.command.accept(this);
            b = w.boolExpr.accept(this);
        }
        // exitScope();
    }

    @Override
    public void visit(CommandList commandList) {
        CommandList cl = commandList;
        //enterScope();
        do {
            cl.command.accept(this);
            cl = cl.commandList;
        } while (cl != null);
        //exitScope();
    }

    @Override
    public Double visit(SumExpr e) {
        return e.e1.accept(this) + e.e2.accept(this);
    }

    @Override
    public Double visit(SubExpr e) {
        return e.e1.accept(this) - e.e2.accept(this);
    }

    @Override
    public Double visit(MulExpr e) {
        return e.e1.accept(this) * e.e2.accept(this);
    }

    @Override
    public Double visit(DivExpr e) {
        return e.e1.accept(this) / e.e2.accept(this);
    }

    @Override
    public Double visit(DoubleConstExpr e) {
        return e.value;
    }

    @Override
    public Double visit(NegatedExpr e) {
        return -e.expr.accept(this);
    }

    @Override
    public Double visit(ModExpr e) {
        return e.e1.accept(this) % e.e2.accept(this);
    }

    @Override
    public Double visit(ExpExpr e) {
        return Math.pow(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Double visit(SinExpr e) {
        return Math.sin(e.expr.accept(this));
    }

    @Override
    public Double visit(CosExpr e) {
        return Math.cos(e.expr.accept(this));
    }

    @Override
    public Double visit(PiExpr e) {
        return e.value;
    }

    @Override
    public Boolean visit(GTExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return v1 > v2;
    }

    @Override
    public Boolean visit(LTExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return v1 < v2;
    }

    @Override
    public Boolean visit(GEExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return v1 >= v2;
    }


    @Override
    public Boolean visit(LEExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return v1 <= v2;
    }

    @Override
    public Boolean visit(ast.expr.EQExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return v1.equals(v2);
    }

    @Override
    public Boolean visit(ast.expr.NEExpr e) {
        Double v1 = e.e1.accept(this);
        Double v2 = e.e2.accept(this);
        return !v1.equals(v2);
    }

    @Override
    public void visit(PrintCommand c) {
        System.out.println(">>> " + c.expr.accept(this));
    }
}
