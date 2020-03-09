package pl.piekoszek.json;

import java.lang.reflect.Field;
import java.util.*;

import static pl.piekoszek.json.T.*;

public class Piekson {

    private static final Map<T, T[]> tt;

    static {
        tt = new HashMap<>();
        tt.put(START, new T[]{
                VALUE_BEGIN,
                VALUE_INT_FIRST,
                VALUE_BOOLEAN_FIRST,
                OBJECT_BEGIN,
                ARRAY_BEGIN,
        });
        tt.put(OBJECT_BEGIN, new T[]{
                KEY_BEGIN,
                OBJECT_END,
        });
        tt.put(OBJECT_END, new T[]{
                OBJECT_END,
                VALUES_SEPARATOR,
                ARRAY_END
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
                VALUE_INT_FIRST,
                VALUE_BOOLEAN_FIRST,
                OBJECT_BEGIN,
                ARRAY_BEGIN
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
                ARRAY_END,
                VALUES_SEPARATOR,
        });
        tt.put(VALUE_INT_FIRST, new T[]{
                VALUE_INT,
                VALUE_POINT,
                VALUES_SEPARATOR,
                OBJECT_END,
                ARRAY_END
        });
        tt.put(VALUE_INT, new T[]{
                VALUE_INT,
                VALUE_POINT,
                VALUES_SEPARATOR,
                OBJECT_END,
                ARRAY_END
        });
        tt.put(VALUE_POINT, new T[]{
                VALUE_FLOAT
        });
        tt.put(VALUE_FLOAT, new T[]{
                VALUE_FLOAT,
                VALUES_SEPARATOR,
                OBJECT_END,
                ARRAY_END
        });
        tt.put(VALUES_SEPARATOR, new T[]{
                KEY_BEGIN,
                VALUE_BEGIN,
                VALUE_INT_FIRST,
                VALUE_BOOLEAN_FIRST,
                OBJECT_BEGIN,
                ARRAY_BEGIN
        });
        tt.put(ARRAY_BEGIN, new T[]{
                VALUE_BEGIN,
                VALUE_INT_FIRST,
                VALUE_BOOLEAN_FIRST,
                OBJECT_BEGIN,
                ARRAY_BEGIN,
                ARRAY_END
        });
        tt.put(ARRAY_END, new T[]{
                OBJECT_END,
                ARRAY_END,
                VALUES_SEPARATOR,
        });
        tt.put(VALUE_BOOLEAN_FIRST, new T[]{
                VALUE_BOOLEAN_R,
                VALUE_BOOLEAN_A
        });
        tt.put(VALUE_BOOLEAN_R, new T[]{
                VALUE_BOOLEAN_U,
        });
        tt.put(VALUE_BOOLEAN_U, new T[]{
                VALUE_BOOLEAN_E,
        });
        tt.put(VALUE_BOOLEAN_A, new T[]{
                VALUE_BOOLEAN_L,
        });
        tt.put(VALUE_BOOLEAN_L, new T[]{
                VALUE_BOOLEAN_S,
        });
        tt.put(VALUE_BOOLEAN_S, new T[]{
                VALUE_BOOLEAN_E,
        });
        tt.put(VALUE_BOOLEAN_E, new T[]{
                VALUES_SEPARATOR,
                OBJECT_END,
                ARRAY_END
        });


    }

    public static Map<String, Object> fromJson(String json) {
        return (Map<String, Object>) parseJson(json);
    }

    public static <T> T fromJson(String json, Class<T> type) {

        Object result = parseJson(json);
        if (result instanceof Map) {
            return (T) parseObject(type, (Map<String, Object>) result);
        }

        return null;
    }

    private static Object parseObject(Class objectsClass, Map<String, Object> map) {

        Object instance = null;
        try {
            instance = objectsClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            try {
                Field field = instance.getClass().getField(k);
                if (v instanceof Map) {
                    setValue(instance, field, parseObject(field.getType(), (Map<String, Object>) v));
                } else if (v instanceof Collection) {

                } else {
                    setValue(instance, field, v);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static void setValue(Object object, Field field, Object value) {
        try {
            if (value instanceof Number) {
                if (field.getType() == int.class) {
                    field.set(object, ((Number) value).intValue());
                } else if (field.getType() == long.class) {
                    field.set(object, ((Number) value).longValue());
                } else if (field.getType() == double.class) {
                    field.set(object, ((Number) value).doubleValue());
                } else if (field.getType() == float.class) {
                    field.set(object, ((Number) value).floatValue());
                }
            } else {
                field.set(object, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Object parseJson(String json) {
        T t = START;

        ArrayList<X> stack = new ArrayList<>();
        Stack<Map<String, Object>> mapStack = new Stack<>();

        stack.add(new X(KEY_BEGIN, 'v'));
        mapStack.push(new HashMap<>());

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

        System.out.println(stack);

        return mapStack.peek().get("v");
    }

}
