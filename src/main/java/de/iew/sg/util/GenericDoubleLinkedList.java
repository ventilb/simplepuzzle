package de.iew.sg.util;

/**
 * A very simple double linked list to hold objects of generic type <code>T</code> as payload.
 *
 * @author @author <a href="mailto:manuel_schulze@i-entwicklung.de">Manuel Schulze</a>
 * @see <a href="http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php">http://www.javabeginners.de/Sammlungen_und_Listen/Doppelt_verkettete_Liste.php</a>
 * @since 06.01.14 - 22:38
 */
public class GenericDoubleLinkedList<T> {

    private GenericListEntry<T> last;

    private int length = 0;

    public GenericDoubleLinkedList(int length) {
        this.last = new GenericListEntry(null);
        this.length = 1;

        for (int i = 1; i < length; i++) {
            append(null);
        }
    }

    public void set(T o, int index) {
        GenericListEntry<T> entry = getEntryAt(index);
        entry.setPayload(o);
    }

    public T get(int index) {
        GenericListEntry<T> entry = getEntryAt(index);
        return entry.getPayload();
    }

    public int getLength() {
        return this.length;
    }

    protected GenericListEntry<T> getEntryAt(int index) {
        final int length = getLength();

        GenericListEntry<T> entry = this.last;
        for (int i = index; i < length - 1; i++) {
            entry = entry.getPrev();
        }
        return entry;
    }

    public void append(T o) {
        GenericListEntry<T> listEntry = new GenericListEntry(o);
        this.last.setNext(listEntry);
        listEntry.setPrev(this.last);

        this.last = listEntry;
        this.length++;
    }

}
