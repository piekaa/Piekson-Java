package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonToObjectTests {

    @Test
    void testJson() {
        //language=JSON
        String json = "{\n" +
                "  \"a\": 10,\n" +
                "  \"b\": \"dupa kościotrupa\",\n" +
                "  \"c\": 123.50,\n" +
                "  \"nested\" : {\n" +
                "    \"ab\" : true\n" +
                "  }\n" +
                "}";
        TestClass expected = new TestClass();
        expected.a = 10;
        expected.b = "dupa kościotrupa";
        expected.c = 123.50f;
        expected.nested = new TestNested(true);
        assertEquals(expected, Piekson.fromJson(json, TestClass.class));
    }

}

class TestClass {

    public int a;
    public String b;
    public float c;
    public TestNested nested;
    public TestNested[] nesteds;
    public List<TestNested> nestedList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return a == testClass.a &&
                Float.compare(testClass.c, c) == 0 &&
                Objects.equals(b, testClass.b) &&
                Objects.equals(nested, testClass.nested) &&
                Arrays.equals(nesteds, testClass.nesteds) &&
                Objects.equals(nestedList, testClass.nestedList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a, b, c, nested, nestedList);
        result = 31 * result + Arrays.hashCode(nesteds);
        return result;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                ", nested=" + nested +
                ", nesteds=" + Arrays.toString(nesteds) +
                ", nestedList=" + nestedList +
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