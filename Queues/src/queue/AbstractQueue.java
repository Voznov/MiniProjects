package queue;

public abstract class AbstractQueue implements Queue {
    int size = 0;

    //Immutable
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < result.length; ++i) {
            result[i] = dequeue();
        }
        for (Object object : result) {
            enqueue(object);
        }
        return result;
    }

    @Override
    public void enqueue(Object element) {
        assert element != null;
        _enqueue(element);
        ++size;
    }

    abstract void _enqueue(Object element);

    @Override
    public Object element() {
        assert size > 0;
        return _element();
    }

    abstract Object _element();

    @Override
    public Object dequeue() {
        Object result = element();
        _dequeue();
        --size;
        return result;
    }

    abstract void _dequeue();

    @Override
    public void clear() {
        size = 0;
        _clear();
    }

    abstract void _clear();

    @Override
    public void push(Object element) {
        assert element != null;
        _push(element);
        ++size;
    }

    abstract void _push(Object element);

    @Override
    public Object peek() {
        assert size > 0;
        return _peek();
    }

    abstract Object _peek();

    public Object remove() {
        Object result = peek();
        _remove();
        --size;
        return result;
    }

    abstract void _remove();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}
