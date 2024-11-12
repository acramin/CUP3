package ast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import ast.expr.IdExpr;

public class SymbolTable
{
    private Stack<HashMap<String, IdExpr>> stack= new Stack<>();

    public SymbolTable() {
        stack.push(new HashMap<>());
        //System.err.println("SymbolTable add: " + stack.size());
    }

    public void enterScope() {
        //System.out.println("qtd escopo " + stack.size());
        //System.out.println(symbolStack.push(new HashMap<String, Object>()));
        stack.push(new HashMap<String, IdExpr>());
        //System.out.println("qtd escopo " +stack.size());
    }

    public void exitScope(){
        if (!stack.isEmpty()) {
            //System.out.println("qtd escopo " + stack.size());
            //System.out.println(stack.pop());
            stack.pop();
            //System.out.println("qtd escopo " + stack.size());
        } else {
            System.err.println("Erro: tentativa de sair de um escopo inexistente!");
        }
    }

    public void assignSymbol(String id, IdExpr value) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            HashMap<String, IdExpr> currentScope = stack.get(i);
            if (currentScope.containsKey(id)) {
                currentScope.put(id, value);
                return;
            }
        }
        //System.out.println("pico "+ stack.peek().put(id, value));
        stack.peek().put(id, value);
    }

    public IdExpr lookupSymbol(String id) {
        //Iterator<HashMap<String, IdExpr>> iterator = stack.iterator();
        //System.out.println("it " + iterator);
        for( int i  = stack.size(); i > 0; i--) {
            HashMap<String, IdExpr> currentScope = stack.get(i-1);
            //System.out.println("currentScope: " + currentScope);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id);
            }
        }
        
        System.err.println("Erro: variável \"" + id + "\" não encontrada!");
        return null;
    }

    public void printStack() {
        System.out.println("Estado da pilha de escopos:");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("Escopo " + i + ": " + stack.get(i));
        }
    }
}