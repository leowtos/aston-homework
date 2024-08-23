package dev.leowtos.aston.homework.core.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class CollectionsUtils {

    public static <T> void assertCollectionEquals(Collection<T> first, Collection<T> second) {
        assertEquals(first.size(), second.size());

        assertTrue(first.containsAll(second));
        assertTrue(second.containsAll(first));
    }
}
