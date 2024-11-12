package ast;

import ast.expr.*;
import ast.command.*;

public interface CodeVisitor {

    // expressões
    public Double visit(SumExpr e);

    public Double visit(SubExpr e);

    public Double visit(MulExpr e);

    public Double visit(DivExpr e);

    public Double visit(IdExpr e);

    public Double visit(ConstExpr e);

    public Double visit(NegatedExpr e);

    public Double visit(ModExpr e);

    public Double visit(ExpExpr e);

    public Double visit(SinExpr e);

    public Double visit(CosExpr e);

    public Double visit(PiExpr e);


    // operadores relacionais
    public Boolean visit(GTExpr e);

    public Boolean visit(LTExpr e);

    public Boolean visit(GEExpr e);

    public Boolean visit(LEExpr e);

    public Boolean visit(EQExpr e);

    public Boolean visit(NEExpr e);


    // comandos
    public void visit(PrintCommand c);

    public void visit(AssignmentCommand c);

    public void visit(IfCommand ifc);

    public void visit(WhileCommand w);

    public void visit(CommandList e);

    
}
