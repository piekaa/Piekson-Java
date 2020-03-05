package pl.piekoszek.json;

import java.util.*;

import static pl.piekoszek.json.T.*;

public class Piekson {

    private static final Map<T, T[]> tt;

    static {
        tt = new HashMap<>();
        tt.put(START, new T[]{
                VALUE_BEGIN,
                VALUE_INT_FIRST,
                OBJECT_BEGIN,
                ARRAY_BEGIN
        });
        tt.put(OBJECT_BEGIN, new T[]{
                KEY_BEGIN,
                OBJECT_END,
        });
        tt.put(KEY_BEGIN, new T[]{
                KEY
        });
        tt.put(KEY, new T[]{
                KEY,
                KEY_END
        });
        tt.put(KEY_END, new T[]{
                KEY_VALUE_SEPARATOR
        });
        tt.put(KEY_VALUE_SEPARATOR, new T[]{
                VALUE_BEGIN,
                VALUE_INT_FIRST
        });
        tt.put(VALUE_BEGIN, new T[]{
                VALUE
        });
        tt.put(VALUE, new T[]{
                VALUE,
                VALUE_END
        });
        tt.put(VALUE_END, new T[]{
                OBJECT_END,
        });
    }

    public static Map<String, Object> fromJson(String json) {

        T t = START;

        ArrayList<X> stack = new ArrayList<>();
        Stack<Map<String, Object>> mapStack = new Stack<>();

        System.out.println(json);

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            System.out.print(c);
            if (t.skipBlank && Character.isWhitespace(c)) {
                continue;
            }
            T nt = Arrays.stream(tt.get(t)).filter(e -> e.p.matcher("" + c).matches())
                    .findFirst()
                    .orElseThrow(PieksonException::new);
            nt.stackFunction.handle(stack, mapStack, nt, c);
            t = nt;
        }
        return mapStack.peek();
    }

}
