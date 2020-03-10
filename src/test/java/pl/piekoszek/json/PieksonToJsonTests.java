package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
