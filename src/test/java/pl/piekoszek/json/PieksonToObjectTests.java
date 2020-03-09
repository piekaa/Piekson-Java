package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonToObjectTests {

    @Test
    void testJson() {
        //language=JSON
        String json = "{\n" +
                "  \"a\": 10,\n" +
                "  \"b\": \"dupa kościotrupa\",\n" +
                "  \"c\": 123.50,\n" +
                "  \"nested\": {\n" +
                "    \"ab\": true\n" +
                "  },\n" +
                "  \"g\": [\n" +
                "    10,\n" +
                "    20,\n" +
                "    40,\n" +
                "    50,\n" +
                "    50\n" +
                "  ],\n" +
                "  \"gg\": [\n" +
                "    10,\n" +
                "    20,\n" +
                "    40,\n" +
                "    50,\n" +
                "    50\n" +
                "  ],\n" +
                "  \"h\": [\n" +
                "    10.5,\n" +
                "    20.5,\n" +
                "    40.5,\n" +
                "    50.5,\n" +
                "    50.5\n" +
                "  ],\n" +
                "  \"hh\": [\n" +
                "    10.5,\n" +
                "    20.5,\n" +
                "    40.5,\n" +
                "    50.5,\n" +
                "    50.5\n" +
                "  ],\n" +
                "  \"nesteds\": [\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    }\n" +
                "  ],\n" +
                "  \"nestedList\": [\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    }\n" +
                "  ],\n" +
                "  \"nestedSet\": [\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"ab\": false\n" +
                "    }\n" +
                "  ]\n" +
                "}";
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
        assertEquals(expected, Piekson.fromJson(json, TestClass.class));
    }

}

class TestClass {

    public int a;
    public String b;
    public float c;
    public long[] g;
    public Long[] gg;
    public float[] h;
    public Float[] hh;
    public TestNested nested;
    public TestNested[] nesteds;
    public List<TestNested> nestedList;
    public Set<TestNested> nestedSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return a == testClass.a &&
                Float.compare(testClass.c, c) == 0 &&
                Objects.equals(b, testClass.b) &&
                Arrays.equals(g, testClass.g) &&
                Arrays.equals(gg, testClass.gg) &&
                Arrays.equals(h, testClass.h) &&
                Arrays.equals(hh, testClass.hh) &&
                Objects.equals(nested, testClass.nested) &&
                Arrays.equals(nesteds, testClass.nesteds) &&
                Objects.equals(nestedList, testClass.nestedList) &&
                Objects.equals(nestedSet, testClass.nestedSet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a, b, c, nested, nestedList, nestedSet);
        result = 31 * result + Arrays.hashCode(g);
        result = 31 * result + Arrays.hashCode(gg);
        result = 31 * result + Arrays.hashCode(h);
        result = 31 * result + Arrays.hashCode(hh);
        result = 31 * result + Arrays.hashCode(nesteds);
        return result;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                ", g=" + Arrays.toString(g) +
                ", gg=" + Arrays.toString(gg) +
                ", h=" + Arrays.toString(h) +
                ", hh=" + Arrays.toString(hh) +
                ", nested=" + nested +
                ", nesteds=" + Arrays.toString(nesteds) +
                ", nestedList=" + nestedList +
                ", nestedSet=" + nestedSet +
                '}';
    }
}

class TestNested {

    public boolean ab;

    public TestNested(boolean ab) {
        this.ab = ab;
    }

    public TestNested() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestNested that = (TestNested) o;
        return ab == that.ab;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ab);
    }

    @Override
    public String toString() {
        return "TestNested{" +
                "ab=" + ab +
                '}';
    }
}