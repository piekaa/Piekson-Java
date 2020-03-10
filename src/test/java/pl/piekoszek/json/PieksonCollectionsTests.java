package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PieksonCollectionsTests {

    @Test
    void testArrayOfInts() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, Piekson.fromJson("[1,2,3,4]", int[].class));
    }

    @Test
    void testArrayOfIntegers() {
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, Piekson.fromJson("[1,2,3,4]", Integer[].class));
    }

    @Test
    void testArrayOfRawFloats() {
        assertArrayEquals(new float[]{1.5f, 2.5f, 3.5f, 4.5f}, Piekson.fromJson("[1.5,2.5,3.5,4.5]", float[].class));
    }

    @Test
    void testArrayOfFloats() {
        assertArrayEquals(new Float[]{1.5f, 2.5f, 3.5f, 4.5f}, Piekson.fromJson("[1.5,2.5,3.5,4.5]", Float[].class));
    }

    @Test
    void testArrayOfStrings() {
        //language=JSON
        String json = "[\"abc\", \"def\", \"gh1\"" +
                "]";
        assertArrayEquals(new String[]{"abc", "def", "gh1"}, Piekson.fromJson(json, String[].class));
    }

    @Test
    void testArrayOfObjects() {

        TestClass testClass = new TestClass();
        testClass.a = 10;
        testClass.nested = new TestNested(false);

        TestClass testClass2 = new TestClass();
        testClass2.a = 30;
        testClass2.nested = new TestNested(false);

        TestClass testClass3 = new TestClass();
        testClass3.a = 41;
        testClass3.nested = new TestNested(true);

        //language=JSON
        String json = "[\n" +
                "  {\n" +
                "    \"a\": 10,\n" +
                "    \"nested\": {\n" +
                "      \"ab\": false\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"a\": 30,\n" +
                "    \"nested\": {\n" +
                "      \"ab\": false\n" +
                "    }\n" +
                "  " +
                "},\n" +
                "  {\n" +
                "    \"a\": 41,\n" +
                "    \"nested\": {\n" +
                "      \"ab\": true\n" +
                "    }\n" +
                "  " +
                "}\n" +
                "]";

        assertArrayEquals(new TestClass[]{testClass, testClass2, testClass3}, Piekson.fromJson(json, TestClass[].class));

    }

}
