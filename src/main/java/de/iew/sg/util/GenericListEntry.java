package de.iew.sg.util;

/**
 * List element to hold the generic type <code>T</code> payload in a double linked list.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 22:39
 */
public class GenericListEntry<T> {

    /**
     * The payload.
     */
    private T payload;

    /**
     * Pointer to the next list element or NULL if this element is last element in the list.
     */
    private GenericListEntry next;

    /**
     * Pointer to the previous list element or NULL if this element is the first element in the list.
     */
    private GenericListEntry prev;

    public GenericListEntry(T payload) {
        this.payload = payload;
    }

    public GenericListEntry getNext() {
        return next;
    }

    public void setNext(GenericListEntry next) {
        this.next = next;
    }

    public GenericListEntry getPrev() {
        return prev;
    }

    public void setPrev(GenericListEntry prev) {
        this.prev = prev;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
