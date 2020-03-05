package pl.piekoszek.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

interface StackFunction {
    void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c);
}

class CreateObject implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {
        stack.add(new X(t));
        mapStack.push(new HashMap<>());
    }
}

class Push implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {
        stack.add(new X(t));
    }
}

class Append implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {
        stack.get(stack.size() - 1).value += c;
    }
}

class PushAndAppend implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {
        stack.add(new X(t, c));
    }
}

class SetValue implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {
        String key = stack.get(stack.size() - 2).value;
        String value = stack.get(stack.size() - 1).value;
        mapStack.peek().put(key, value);
        stack.remove(stack.size() - 1);
        stack.remove(stack.size() - 1);
    }
}

class Idle implements StackFunction {
    @Override
    public void handle(ArrayList<X> stack, Stack<Map<String, Object>> mapStack, T t, char c) {

    }
}