package ast;

import ast.expr.SumExpr;
import ast.expr.MulExpr;
import ast.expr.NEExpr;
import ast.expr.SubExpr;
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
import ast.expr.DoubleConstExpr;
import ast.expr.EQExpr;
import ast.expr.ExpExpr;
import ast.expr.GEExpr;
import ast.expr.GTExpr;
import ast.expr.NegatedExpr;
import ast.expr.PiExpr;
import ast.expr.ModExpr;
import ast.expr.SinExpr;

public interface CodeVisitor {
    public Double visit(SumExpr e);

    public Double visit(SubExpr e);

    public Double visit(MulExpr e);

    public Double visit(DivExpr e);

    public TypedValue visit(IdExpr e);

    public Double visit(DoubleConstExpr e);

    public Double visit(NegatedExpr e);

    public Double visit(ModExpr e);

    public Double visit(ExpExpr e);

    public Double visit(SinExpr e);

    public Double visit(CosExpr e);

    public Double visit(PiExpr e);

    public Boolean visit(GTExpr e);

    public Boolean visit(LTExpr e);

    public Boolean visit(GEExpr e);

    public Boolean visit(LEExpr e);

    public Boolean visit(EQExpr e);

    public Boolean visit(NEExpr e);

    public void visit(PrintCommand c);

    public void visit(AssignmentCommand c);

    public void visit(IfCommand ifc);

    public void visit(WhileCommand w);

    public void visit(CommandList e);
}
