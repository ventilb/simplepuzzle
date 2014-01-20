package de.iew.sg.util;

/**
 * A very simple double linked list to hold {@link java.lang.Object} as payload.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @see <a href="http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php">http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php</a>
 * @since 06.01.14 - 22:38
 */
public class ObjectDoubleLinkedList {

    private ObjectListEntry last;

    public ObjectDoubleLinkedList(Object o) {
        ObjectListEntry listEntry = new ObjectListEntry(o);
        this.last = listEntry;
    }

    public void append(Object o) {
        ObjectListEntry listEntry = new ObjectListEntry(o);
        this.last.setNext(listEntry);
        listEntry.setPrev(this.last);

        this.last = listEntry;
    }

}
