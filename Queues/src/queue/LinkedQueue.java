package queue;

//inv: (container = [head; tail] by next)
public class LinkedQueue extends AbstractQueue {
    private class Node {
        Node prev;
        Node next;
        Object value;

        Node(Object value) {
            this.value = value;
        }

        void setPrev(Node prev) {
            this.prev = prev;
            prev.next = this;
        }

        void setNext(Node next) {
            this.next = next;
            next.prev = this;
        }
    }

    private Node head;
    private Node tail;

    @Override
    void _enqueue(Object element) {
        Node temp = new Node(element);
        if (size == 0) {
            head = temp;
            tail = temp;
        } else {
            temp.setPrev(tail);
            tail = temp;
        }
    }

    @Override
    Object _element() {
        return head.value;
    }

    @Override
    void _dequeue() {
        if (size > 1) {
            head = head.next;
        }
    }

    @Override
    void _clear() {
    }

    @Override
    void _push(Object element) {
        Node temp = new Node(element);
        if (size == 0) {
            head = temp;
            tail = temp;
        } else {
            temp.setNext(head);
            head = temp;
        }
    }

    @Override
    Object _peek() {
        return tail.value;
    }

    @Override
    void _remove() {
        if (size > 1) {
            tail = tail.prev;
        }
    }
}
