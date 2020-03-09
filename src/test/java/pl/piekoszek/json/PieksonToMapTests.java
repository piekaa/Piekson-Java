package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonToMapTests {

    @Test
    void testJson() {
        //language=JSON
        String json = "{\"a\": \"b\"}";
        assertEquals("b", Piekson.fromJson(json).get("a"));
    }

    @Test
    void testJsonInt() {
        //language=JSON
        String json = "{\"a\": 10}";
        assertEquals(10L, Piekson.fromJson(json).get("a"));
    }


    @Test
    void testJsonFloatAndString() {
        //language=JSON
        String json = "{\n" +
                "  \"a\": 10.123,\n" +
                "  \"b\" : \"dupa\"\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);

        assertEquals(10.123, result.get("a"));
        assertEquals("dupa", result.get("b"));
    }

    @Test
    void testJsonFloatAndStringReverse() {
        //language=JSON
        String json = "{\n" +
                "  \"b\" : \"dupa\",\n" +
                "  \"a\": 10.123\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);

        assertEquals(10.123, result.get("a"));
        assertEquals("dupa", result.get("b"));
    }

    @Test
    void testNestedObject() {
        //language=JSON
        String json = "{\n" +
                "  \"a\" : {\n" +
                "    \"b\" : 10,\n" +
                "    \"c\" : \"cycki\"\n" +
                "  }\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);

        Map<String, Object> nested = (Map<String, Object>) result.get("a");
        assertEquals(10L, nested.get("b"));
        assertEquals("cycki", nested.get("c"));
    }

    @Test
    void testArray() {
        //language=JSON
        String json = "{\n" +
                "  \"a\" : [1,2,3,4,5,6,7]\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);
        assertArrayEquals((Long[]) result.get("a"), new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L});
    }

    @Test
    void testEmptyArray() {
        //language=JSON
        String json = "{\n" +
                "  \"a\" : []\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);
        assertArrayEquals((Object[]) result.get("a"), new Object[]{});
    }

    @Test
    void testBoolean() {
        //language=JSON
        String json = "{\n" +
                "  \"a\" : true,\n" +
                "  \"b\": false\n" +
                "}";
        Map<String, Object> result = Piekson.fromJson(json);
        assertEquals(result.get("a"), true);
        assertEquals(result.get("b"), false);
    }
}
