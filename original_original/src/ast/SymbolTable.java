package ast;

import java.util.HashMap;
import java.util.Stack;
import ast.expr.IdExpr;

public class SymbolTable
{
    private Stack<HashMap<String, IdExpr>> stack;

    public SymbolTable() {
        stack = new Stack<>();
        stack.push(new HashMap<>());
        //System.err.println("SymbolTable add: " + stack.size());
    }

    public void push() {
        stack.push(new HashMap<>());
        //System.err.println("SymbolTable add: " + stack.size());
    }

    public void pop() {
        if (!stack.isEmpty() ){
            stack.pop();
        }
        //System.err.println("SymbolTable remove: " + stack.size());
    }

    public void put(String key, IdExpr e) {
        for (HashMap<String, IdExpr> map : stack) {
            if (map.containsKey(key)) {
                map.put(key, e);
                return;
            }
        }
        stack.peek().put(key, e);
        //printStack();
    }

    public IdExpr get(String key) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).containsKey(key)) {
                return stack.get(i).get(key);
            }
        }
        return null;
    }

    public void printStack() {
        System.out.println("Estado da pilha de escopos:");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("Escopo " + i + ": " + stack.get(i));
        }
    }
}
