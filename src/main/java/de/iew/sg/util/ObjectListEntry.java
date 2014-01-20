package de.iew.sg.util;

/**
 * List element to hold {@link java.lang.Object} payload in a double linked list.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 22:39
 */
public class ObjectListEntry {

    /**
     * The payload.
     */
    private Object payload;

    /**
     * Pointer to the next list element or NULL if this element is last element in the list.
     */
    private ObjectListEntry next;

    /**
     * Pointer to the previous list element or NULL if this element is the first element in the list.
     */
    private ObjectListEntry prev;

    public ObjectListEntry(Object payload) {
        this.payload = payload;
    }

    public ObjectListEntry getNext() {
        return next;
    }

    public void setNext(ObjectListEntry next) {
        this.next = next;
    }

    public ObjectListEntry getPrev() {
        return prev;
    }

    public void setPrev(ObjectListEntry prev) {
        this.prev = prev;
    }
}
