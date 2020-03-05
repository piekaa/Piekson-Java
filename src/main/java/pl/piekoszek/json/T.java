package pl.piekoszek.json;

import java.util.regex.Pattern;

enum T {
    START(Pattern.compile(""), true, new Idle()),
    OBJECT_BEGIN(Pattern.compile("\\{"), true, new CreateObject()),
    OBJECT_END(Pattern.compile("}"), true, new Idle()),
    ARRAY_BEGIN(Pattern.compile("\\["), true, new Idle()),
    ARRAY_END(Pattern.compile("]"), true, new Idle()),
    VALUE_BEGIN(Pattern.compile("\""), false, new Push()),
    VALUE(Pattern.compile("[^\\\\\"]"), false, new Append()),
    VALUE_INT_FIRST(Pattern.compile("-"), false, new PushAndAppend()),
    VALUE_POINT(Pattern.compile("."), false, new Append()),
    VALUE_INT(Pattern.compile("[0-9]"), false, new Append()),
    VALUE_FLOAT(Pattern.compile("[0-9]"), false, new Append()),
    VALUE_END(Pattern.compile("\""), true, new SetValue()), // think about arrays
    KEY_BEGIN(Pattern.compile("\""), false, new Push()),
    KEY(Pattern.compile("[a-zA-Z0-9]"), false, new Append()),
    KEY_END(Pattern.compile("\""), true, new Idle()),
    KEY_VALUE_SEPARATOR(Pattern.compile(":"), true, new Idle()),
    VALUES_SEPARATOR(Pattern.compile(","), true, new Idle());

    Pattern p;
    boolean skipBlank;
    StackFunction stackFunction;

    T(Pattern p, boolean skipBlank, StackFunction stackFunction) {
        this.p = p;
        this.skipBlank = skipBlank;
        this.stackFunction = stackFunction;
    }
}
