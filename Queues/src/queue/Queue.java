package queue;

//inv: (size >= 0) && (sizeOf(container) == size) && (Ɐelement in container, element != null)
public interface Queue {
    //pre: element != null
    //post: (size == size' + 1) && (container == container' + element)
    void enqueue(Object element);

    //Immutable
    Object[] toArray();

    //pre: size > 0
    //Immutable
    Object element();

    //pre: size > 0
    //post: (size == size' + 1) && (container == --container')
    Object dequeue();

    //Immutable
    int size();

    //Immutable
    boolean isEmpty();

    //post: size == 0
    void clear();

    //pre: element != null
    //post: (size == size' + 1) && (container == element + container')
    void push(Object element);

    //pre: size > 0
    //Immutable
    Object peek();

    //pre: size > 0
    //post: (size == size' - 1) && (container == container'--)
    Object remove();
}
