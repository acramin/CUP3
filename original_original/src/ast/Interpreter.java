package ast;

import java.util.HashMap;

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
import ast.expr.StringConstExpr;
import ast.expr.SubExpr;
import ast.expr.SumExpr;

public class Interpreter implements CodeVisitor {
    // symbolTable é a tabela de símbolos
    private static HashMap<String, IdExpr> symbolTable = new HashMap<>();

    @Override
    public Double visit(SumExpr e) {
        return (Double) e.e1.accept(this) + (Double) e.e2.accept(this);
    }

    @Override
    public Double visit(SubExpr e) {
        return (Double) e.e1.accept(this) - (Double) e.e2.accept(this);
    }

    @Override
    public Double visit(MulExpr e) {
        return (Double) e.e1.accept(this) * (Double) e.e2.accept(this);
    }

    @Override
    public Double visit(DivExpr e) {
        return (Double) e.e1.accept(this) / (Double) e.e2.accept(this);
    }

    @Override
    public Double visit(IdExpr e) {
        IdExpr idExpr = Interpreter.symbolTable.get(e.name);
        if( idExpr == null ) {
                System.err.println("Erro: variável \"" + e.name +
                        "\" não inicializada!");
        }
        return idExpr.value;
    }

    @Override
    public Double visit(DoubleConstExpr e) {
        return e.value;
    }

    @Override
    public String visit(StringConstExpr e) {
        return e.value;
    }

    @Override
    public Double visit(NegatedExpr e) {
        return -(Double) e.expr.accept(this);
    }

    @Override
    public Double visit(ModExpr e) {
        return (Double) e.e1.accept(this) % (Double) e.e2.accept(this);
    }

    @Override
    public Double visit(ExpExpr e) {
        return Math.pow((Double)e.e1.accept(this), (Double)e.e2.accept(this));
    }

    @Override
    public Double visit(SinExpr e) {
        return Math.sin((Double)e.expr.accept(this));
    }

    @Override
    public Double visit(CosExpr e) {
        return Math.cos((Double)e.expr.accept(this));
    }

    @Override
    public Double visit(PiExpr e) {
        return e.value;
    }

    @Override
    public Boolean visit(GTExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return v1 > v2;
    }

    @Override
    public Boolean visit(LTExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return v1 < v2;
    }

    @Override
    public Boolean visit(GEExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return v1 >= v2;
    }


    @Override
    public Boolean visit(LEExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return v1 <= v2;
    }

    @Override
    public Boolean visit(ast.expr.EQExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return v1.equals(v2);
    }

    @Override
    public Boolean visit(ast.expr.NEExpr e) {
        Double v1 = (Double) e.e1.accept(this);
        Double v2 = (Double) e.e2.accept(this);
        return !v1.equals(v2);
    }

    @Override
    public void visit(PrintCommand c) {
        System.out.println(">>> " + c.expr.accept(this));
    }

    @Override
    public void visit(AssignmentCommand c) {
        Double value = (Double) c.expr.accept(this);
        IdExpr idExpr = new IdExpr(c.id, value);
        Interpreter.symbolTable.put(c.id, idExpr);
    }

    @Override
    public void visit(IfCommand ifc) {
        if( ifc.boolExpr.accept(this) ){
            ifc.command.accept(this);
        } else {
            if( ifc.elseCommand != null ) {
                ifc.elseCommand.accept(this);
            }
        }  
    }

    @Override
    public void visit(WhileCommand w) {
        Boolean b = w.boolExpr.accept(this);
        while(b) {
            w.command.accept(this);
            b = w.boolExpr.accept(this);
        }
    }

    @Override
    public void visit(CommandList commandList) {
        CommandList cl = commandList;
        do {
            cl.command.accept(this);
            cl = cl.commandList;
        } while (cl != null);
    }
}
