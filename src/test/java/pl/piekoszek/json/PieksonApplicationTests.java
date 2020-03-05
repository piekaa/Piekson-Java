package pl.piekoszek.json;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PieksonApplicationTests {

	@Test
	void testFromJson() {
		//language=JSON
		String json = "{\"a\": \"b\"}";
		assertEquals("b", Piekson.fromJson(json).get("a"));
	}
}
