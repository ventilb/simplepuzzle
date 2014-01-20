package de.iew.sg.util

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.MatcherAssert.assertThat

/**
 * Implements unit tests to test the {@link GenericDoubleLinkedList} implementation.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 23:35
 */
class GenericDoubleLinkedListTest extends GroovyTestCase {

    void testSetAndGet() {
        // Testfix erstellen
        def s1 = "1"
        def s2 = "2"

        // Test durchführen
        GenericDoubleLinkedList<String> testee = new GenericDoubleLinkedList<String>(2)
        testee.set(s1, 1)
        testee.set(s2, 0)

        def actual0 = testee.get(0)
        def actual1 = testee.get(1)

        // Test auswerten
        assertThat actual0, is(s2)
        assertThat actual1, is(s1)
    }

    public void testAppend() {
        // Testfix erstellen
        def s1 = "1"
        def s2 = "2"

        // Test durchführen
        GenericDoubleLinkedList<String> stringList = new GenericDoubleLinkedList<String>(1)
        stringList.set(s1, 0)
        stringList.append(s2)

        def actual0 = stringList.get(0)
        def actual1 = stringList.get(1)

        // Test auswerten
        assertThat actual0, is(s1)
        assertThat actual1, is(s2)
    }

    void setUp() {
        super.setUp()

    }

    void tearDown() {

    }
}
