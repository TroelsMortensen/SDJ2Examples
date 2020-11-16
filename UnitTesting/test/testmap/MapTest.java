package testmap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    MyMap<String, String> map;

    @BeforeEach
    public void setup() {
        // arrange
        map  = new ArrayMap<>();
    }

    @Test
    public void insertionOfNullKeyThrowsException() {
        // act assert
        assertThrows(IllegalArgumentException.class,() -> map.put(null, "hello"));
    }

    @Test
    public void insertionOfNullValueThrowsException() {
        // act assert
        assertThrows(IllegalArgumentException.class,() -> map.put("hello", null));
    }

    @Test
    public void insertOneValue() {
        // act
        map.put("hello", "world");
        // assert
        assertEquals("world", map.get("hello"));
    }

    @Test
    public void insertMultiplePairs() {
        map.put("hello", "world");
        map.put("something", "else");
        map.put("last", "value");

        // assert
        assertEquals("world", map.get("hello"));
        assertEquals("else", map.get("something"));
        assertEquals("value", map.get("last"));
    }

    @Test
    public void arrayExpandsOnceFull() {
        map.put("1", "world");
        map.put("2", "world");
        map.put("3", "world");
        map.put("4", "world");
        map.put("5", "world");
        map.put("6", "world");
        map.put("7", "world");
        assertEquals(7, map.size());
        map.put("8", "does it work");

        assertEquals(8, map.size());
    }

}