package queue;

/*inv: (array.length >= 5) && (0 <= left < array.length) && (0 < right <= array.length)
    && (container = (left < right ? array[left; right) : array[left; array.length) + array[0; right))
*/
public class ArrayQueue extends AbstractQueue {
    private static final int INITIAL_ARRAY_LENGTH = 5;
    private int left = 0, right = INITIAL_ARRAY_LENGTH;
    private Object[] array = new Object[INITIAL_ARRAY_LENGTH];

    private void fillArray(Object[] result) {
        if (size == 0) {
            return;
        }
        if (left < right) {
            System.arraycopy(array, left, result, left - left, right - left);
        } else {
            System.arraycopy(array, left, result, left - left, array.length - left);
            System.arraycopy(array, 0, result, 0 + array.length - left, right);
        }
    }


    private void checkSize() {
        if (array.length > size) {
            return;
        }
        Object[] newArray = new Object[2 * size + 1];
        fillArray(newArray);
        array = newArray;
        left = 0;
        right = size;
    }

    @Override
    void _enqueue(Object element) {
        checkSize();
        if (array.length == right) {
            right = 0;
        }
        array[right++] = element;
    }

    @Override
    Object _element() {
        return array[left];
    }

    @Override
    void _dequeue() {
        ++left;
        if (left == array.length) {
            left = 0;
        }
    }

    @Override
    void _clear() {
        left = 0;
        right = INITIAL_ARRAY_LENGTH;
    }

    @Override
    void _push(Object element) {
        checkSize();
        if (left == 0) {
            left = array.length;
        }
        array[--left] = element;
    }

    @Override
    Object _peek() {
        return array[right - 1];
    }

    @Override
    void _remove() {
        --right;
        if (right == 0) {
            right = array.length;
        }
    }
}
