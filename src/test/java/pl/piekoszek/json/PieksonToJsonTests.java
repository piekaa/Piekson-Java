package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonToJsonTests {

    @Test
    void testJson() {

        TestClass expected = new TestClass();
        expected.a = 10;
        expected.b = "dupa kościotrupa";
        expected.c = 123.50f;
        expected.g = new long[]{10, 20, 40, 50, 50};
        expected.gg = new Long[]{10L, 20L, 40L, 50L, 50L};
        expected.h = new float[]{10.5f, 20.5f, 40.5f, 50.5f, 50.5f};
        expected.hh = new Float[]{10.5f, 20.5f, 40.5f, 50.5f, 50.5f};
        expected.nested = new TestNested(true);
        expected.nesteds = new TestNested[]{new TestNested(true), new TestNested(false), new TestNested(true), new TestNested(false)};
        List<TestNested> list = new ArrayList<>();
        list.add(new TestNested(true));
        list.add(new TestNested(false));
        list.add(new TestNested(true));
        list.add(new TestNested(false));
        expected.nestedList = list;
        Set<TestNested> set = new HashSet<>();
        set.add(new TestNested(true));
        set.add(new TestNested(false));
        expected.nestedSet = set;

        String json = Piekson.toJson(expected);

        System.out.println(json);

        TestClass result = Piekson.fromJson(json, TestClass.class);

        assertEquals(expected, result);
    }

    @Test
    void testStringToJson() {
        String s = "dupa kościotrupa";

        assertEquals("\"" + s + "\"", Piekson.toJson(s));
    }

    @Test
    void testEscapedStringToJson() {
        String s = "dupa ko \"ś\" ciotrupa";

        //language=JSON
        String expectedJson = "\"dupa ko \\\"ś\\\" ciotrupa\"";

        assertEquals(expectedJson, Piekson.toJson(s));
    }

    @Test
    void testListToJson() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("edf");
        //language=JSON
        String expectedJson = "[\"abc\",\"edf\"]";
        assertEquals(expectedJson, Piekson.toJson(list));
    }

    @Test
    void testArrayToJson() {
        String[] array = new String[]{"abc", "edf"};
        //language=JSON
        String expectedJson = "[\"abc\",\"edf\"]";
        assertEquals(expectedJson, Piekson.toJson(array));
    }

    @Test
    void testMapToJson() {

        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> inner = new LinkedHashMap<>();
        map.put("text", "dupa kosciotrupa");
        map.put("number", 123);
        inner.put("dbl", 123.12);

        map.put("obj", inner);

        //language=JSON
        String expectedJson = "{\"text\":\"dupa kosciotrupa\",\"number\":123,\"obj\":{\"dbl\":123.12}}";
        assertEquals(expectedJson, Piekson.toJson(map));
    }
}
