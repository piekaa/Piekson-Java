package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonRawTypesToJsonTests {

    @Test
    void testString() {
        assertEquals("dupa kościotrupa", Piekson.fromJson("\"dupa kościotrupa\"", String.class));
    }

    @Test
    void testStringEscaped() {
        assertEquals("dupa ko \"ś\" ciotrupa", Piekson.fromJson("\"dupa ko \\\"ś\\\" ciotrupa\"", String.class));
    }

    @Test
    void testInt() {
        assertEquals(123, Piekson.fromJson("123", Integer.class));
    }

    @Test
    void testLong() {
        assertEquals(123L, Piekson.fromJson("123", Long.class));
    }

    @Test
    void testFloat() {
        assertEquals(123.5f, Piekson.fromJson("123.5", Float.class));
    }

    @Test
    void testDouble() {
        assertEquals(123.5, Piekson.fromJson("123.5", Double.class));
    }

    @Test
    void testBoolean() {
        assertEquals(true, Piekson.fromJson("true", Boolean.class));
    }
}
