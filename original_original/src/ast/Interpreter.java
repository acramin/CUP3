package ast;

import ast.command.*;
import ast.expr.*;

public class Interpreter implements CodeVisitor {
    // symbolTable é a tabela de símbolos
    private static SymbolTable symbolTable = new SymbolTable();

    // expressão
    @Override
    public Const visit(SumExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            return new Const((Double)c1.getValue() + (Double)c2.getValue(), Type.NUMBER);
        }
        else if(c1.getType() == Type.STRING || c2.getType() == Type.STRING) {
            return new Const(c1.getValue().toString() + c2.getValue().toString(), Type.STRING);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(SubExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            return new Const((Double)c1.getValue() - (Double)c2.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(MulExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            return new Const((Double)c1.getValue() * (Double)c2.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(DivExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            if((Double)c2.getValue() == 0) {
                System.err.println("Erro: divisão por zero!");
                return new Const(null, null);
                // return null;
            }
            return new Const((Double)c1.getValue() / (Double)c2.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(IdExpr e) {
        IdExpr idExpr = Interpreter.symbolTable.get(e.name);
        if(idExpr == null) {
                System.err.println("Erro: variável \"" + e.name +
                        "\" não inicializada!");
        }
        return idExpr.value;
    }

    @Override
    public Const visit(ConstExpr e) {
        return e.value;
    }

    @Override
    public Const visit(NegatedExpr e) {
        Const value = e.expr.accept(this);
        if(value.getType() == Type.NUMBER) {
            return new Const(-(Double)value.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(ModExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            if((Double)c2.getValue() == 0) {
                System.err.println("Erro: divisão por zero!");
                return new Const(null, null);
                // return null;
            }
            return new Const((Double)c1.getValue() % (Double)c2.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(ExpExpr e) {
        Const c1 = e.e1.accept(this);
        Const c2 = e.e2.accept(this);
        if(c1.getType() == Type.NUMBER && c2.getType() == Type.NUMBER) {
            return new Const(Math.pow((Double)c1.getValue(), (Double)c2.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(SinExpr e) {
        Const value = e.expr.accept(this);
        if(value.getType() == Type.NUMBER) {
            return new Const(Math.sin((Double)value.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(CosExpr e) {
        Const value = e.expr.accept(this);
        if(value.getType() == Type.NUMBER) {
            return new Const(Math.cos((Double)value.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(PiExpr e) {
        return new Const(e.value, Type.NUMBER);
    }


    // operadores relacionais
    @Override
    public Boolean visit(GTExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return (Double)v1.getValue() > (Double)v2.getValue();
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }

    @Override
    public Boolean visit(LTExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return (Double)v1.getValue() < (Double)v2.getValue();
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }

    @Override
    public Boolean visit(GEExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return (Double)v1.getValue() >= (Double)v2.getValue();
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }


    @Override
    public Boolean visit(LEExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return (Double)v1.getValue() <= (Double)v2.getValue();
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }

    @Override
    public Boolean visit(ast.expr.EQExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return ((Double)v1.getValue()).equals((Double)v2.getValue());
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }

    @Override
    public Boolean visit(ast.expr.NEExpr e) {
        Const v1 = e.e1.accept(this);
        Const v2 = e.e2.accept(this);
        if(v1.getType() == Type.NUMBER && v2.getType() == Type.NUMBER) {
            return !((Double)v1.getValue()).equals((Double)v2.getValue());
        }
        else {
            System.err.println("Erro: tipos incompatíveis!");
            return false;
        }
    }


    // comandos
    @Override
    public void visit(PrintCommand c) {
        System.out.println(">>> " + c.expr.accept(this).getValue().toString());
    }

    @Override
    public void visit(AssignmentCommand c) {
        Const value = c.expr.accept(this);
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

    @Override
    public void visit(CommandBlock cb) {
        symbolTable.push();
        cb.getCommandList().accept(this);
        symbolTable.pop();
    }
}
