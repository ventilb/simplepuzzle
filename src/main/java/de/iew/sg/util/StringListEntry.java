package de.iew.sg.util;

/**
 * List element to hold {@link java.lang.String} payload in a double linked list.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @since 06.01.14 - 22:39
 */
public class StringListEntry {

    /**
     * The payload.
     */
    private String payload;

    /**
     * Pointer to the next list element or NULL if this element is last element in the list.
     */
    private StringListEntry next;

    /**
     * Pointer to the previous list element or NULL if this element is the first element in the list.
     */
    private StringListEntry prev;

    public StringListEntry(String payload) {
        this.payload = payload;
    }

    public StringListEntry getNext() {
        return next;
    }

    public void setNext(StringListEntry next) {
        this.next = next;
    }

    public StringListEntry getPrev() {
        return prev;
    }

    public void setPrev(StringListEntry prev) {
        this.prev = prev;
    }
}
