package pl.piekoszek.json;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieksonBsonToMapTests {

    @Test
    void bsonToMap() {
        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("dupa", "kosciotrupa");
        expected.put("cycki", -123123123);
        byte[] bson = new byte[]{
                38, 0, 0, 0, // size
                0x02, 100, 117, 112, 97, 0, // dupa
                12, 0, 0, 0, 107, 111, 115, 99, 105, 111, 116, 114, 117, 112, 97, 0, // kosciotrupa
                0x10, 99, 121, 99, 107, 105, 0, // cycki
                0x4D, 0x4A, (byte) 0xA9, (byte) 0xF8, // 4
                0, // end of object
        };
        assertEquals(expected, Piekson.fromBson(bson));
    }

    @Test
    void bsonToMapWithArray() {
        Map<String, Object> expected = new LinkedHashMap<>();
        Object[] array = new Object[]{"awesome", 5.05, 1986};
        expected.put("BSON", array);
        byte[] bson = new byte[]{
                0x31, 0, 0, 0, // size
                0x04, 66, 83, 79, 78, 0x00, // BSON
                0x26, 0, 0, 0, // size
                0x02, 0x30, 0x00, 0x08, 0x00, 0x00, 0x00, 97, 119, 101, 115, 111, 109, 101, 0x00, // awesome
                0x01, 0x31, 0x00, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33, 0x14, 0x40, // 5.05
                0x10, 0x32, 0x00, (byte)0xC2, 0x07, 0x00, 0x00, // 1986
                0, // end of array
                0, // end of object
        };
        assertArrayEquals(array, (Object[]) Piekson.fromBson(bson).get("BSON"));
    }

    @Test
    void bsonToEmptyObjectMap() {
        Map<String, Object> expected = new LinkedHashMap<>();
        byte[] bson = new byte[]{
                5, 0, 0, 0, // size
                0, // end of object
        };
        assertEquals(expected, Piekson.fromBson(bson));
    }
}
