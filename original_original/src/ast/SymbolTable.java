package ast;

import java.util.HashMap;
import java.util.Stack;
import ast.expr.IdExpr;

public class SymbolTable
{
    private Stack<HashMap<String, IdExpr>> stack;

    public SymbolTable() {
        stack = new Stack<HashMap<String, IdExpr>>();
        stack.push(new HashMap<String, IdExpr>());
    }

    public void push() {
        stack.push(new HashMap<String, IdExpr>());
    }

    public void pop() {
        if (stack.size() > 1) {
            stack.pop();
        }
    }

    public void put(String key, IdExpr e) {
        for (HashMap<String, IdExpr> map : stack) {
            if (map.containsKey(key)) {
                map.put(key, e);
                return;
            }
        }
        stack.peek().put(key, e);
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
        for (HashMap<String, IdExpr> map : stack) {
            System.out.println(map);
        }
    }
}
