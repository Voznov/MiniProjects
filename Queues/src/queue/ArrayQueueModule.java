package queue;

/*inv: (array.length >= 5) && (size >= 0) && (0 <= left < array.length) && (0 < right <= array.length)
    && (container = (left < right ? array[left; right) : array[left; array.length) + array[0; right))
    && (sizeOf(Container) == size) && (Ɐ element in container, element != null)
*/
public class ArrayQueueModule {
    private static final int INITIAL_ARRAY_LENGTH = 5;
    private static int size = 0;
    private static int left = 0, right = INITIAL_ARRAY_LENGTH;
    private static Object[] array = new Object[INITIAL_ARRAY_LENGTH];

    private static void fillArray(Object[] arrayForFilling) {
        if (size == 0) {
            return;
        }
        if (left < right) {
            for (int i = left; i < right; ++i) {
                arrayForFilling[i - left] = array[i];
            }
        } else {
            for (int i = left; i < array.length; ++i) {
                arrayForFilling[i - left] = array[i];
            }
            for (int i = 0; i < right; ++i) {
                arrayForFilling[i + array.length - left] = array[i];
            }
        }
    }

    //Immutable
    public static Object[] toArray() {
        Object[] result = new Object[size];
        fillArray(result);
        return result;
    }

    private static void checkSize() {
        if (array.length > size) {
            return;
        }
        Object[] newArray = new Object[2 * size + 1];
        fillArray(newArray);
        array = newArray;
        left = 0;
        right = size;
    }

    //pre: element != null
    //post: size == size' + 1
    public static void enqueue(Object element) {
        assert element != null;
        checkSize();
        if (array.length == right) {
            right = 0;
        }
        array[right++] = element;
        ++size;
    }

    //pre: size > 0
    //Immutable
    public static Object element() {
        assert size > 0;
        return array[left];
    }

    //pre: size > 0
    //post: size == size' - 1
    public static Object dequeue() {
        assert size > 0;
        Object result = element();
        ++left;
        if (left == array.length) {
            left = 0;
        }
        --size;

        return result;
    }

    //Immutable
    public static int size() {
        return size;
    }

    //Immutable
    public static boolean isEmpty() {
        return (size == 0);
    }

    //post: size == 0
    public static void clear() {
        size = 0;
        left = 0;
        right = INITIAL_ARRAY_LENGTH;
    }

    //pre: element != null
    //post: size == size' + 1
    public static void push(Object element) {
        assert element != null;
        checkSize();
        if (left == 0) {
            left = array.length;
        }
        array[--left] = element;
        ++size;
    }

    //pre: size > 0
    //Immutable
    public static Object peek() {
        assert size > 0;
        return array[right - 1];
    }

    //pre: size > 0
    //post: size == size' - 1
    public static Object remove() {
        assert size > 0;
        Object result = peek();

        --right;
        if (right == 0) {
            right = array.length;
        }
        --size;

        return result;
    }
}
