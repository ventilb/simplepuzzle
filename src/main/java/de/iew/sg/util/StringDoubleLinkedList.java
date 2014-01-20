package de.iew.sg.util;

/**
 * A very simple double linked list to hold {@link java.lang.String} as payload.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @see <a href="http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php">http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php</a>
 * @since 06.01.14 - 22:38
 */
public class StringDoubleLinkedList {

    private StringListEntry last;

    public StringDoubleLinkedList(String s) {
        StringListEntry listEntry = new StringListEntry(s);
        this.last = listEntry;
    }

    /**
     * Appends a new list element to the end of this list with the specified payload.
     *
     * @param s the payload
     */
    public void append(String s) {
        StringListEntry listEntry = new StringListEntry(s);
        this.last.setNext(listEntry);
        listEntry.setPrev(this.last);

        this.last = listEntry;
    }

}
