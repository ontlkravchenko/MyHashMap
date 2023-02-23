import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {
    private MyMap<Integer, Integer> map;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.map = new MyMap<>();
    }

    @org.junit.jupiter.api.Test
    void putAndGetWorks() {
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);

        assertEquals(2, map.get(2));
        assertEquals(3, map.get(3));
        assertEquals(4, map.get(4));
    }

    @Test
    void growWorks() {
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        assertEquals(500, map.get(500));
        assertEquals(5, map.get(5));
        assertEquals(888, map.get(888));
        assertEquals(2, map.get(2));
    }

    @Test
    void containsWorks() {
        map.put(2, 2);

        assertTrue(map.containsKey(2));
    }

    @Test
    void removeWorks() {
        map.put(2,2);
        map.put(3,3);

        map.remove(2);
        assertFalse(map.containsKey(2));
    }
}