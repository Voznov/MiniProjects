package queue;

/*inv: (array.length >= 5) && (size >= 0) && (0 <= left < array.length) && (0 < right <= array.length)
    && (container = (left < right ? array[left; right) : array[left; array.length) + array[0; right))
    && (sizeOf(Container) == size) && (Ɐ element in container, element != null)
*/
public class ArrayQueueADT {
    private static final int INITIAL_ARRAY_LENGTH = 5;
    private int size = 0;
    private int left = 0, right = INITIAL_ARRAY_LENGTH;
    private Object[] array = new Object[INITIAL_ARRAY_LENGTH];

    private static void fillArray(ArrayQueueADT arrayQueueADT, Object[] arrayForFilling) {
        if (arrayQueueADT.size == 0) {
            return;
        }
        if (arrayQueueADT.left < arrayQueueADT.right) {
            for (int i = arrayQueueADT.left; i < arrayQueueADT.right; ++i) {
                arrayForFilling[i - arrayQueueADT.left] = arrayQueueADT.array[i];
            }
        } else {
            for (int i = arrayQueueADT.left; i < arrayQueueADT.array.length; ++i) {
                arrayForFilling[i - arrayQueueADT.left] = arrayQueueADT.array[i];
            }
            for (int i = 0; i < arrayQueueADT.right; ++i) {
                arrayForFilling[i + arrayQueueADT.array.length - arrayQueueADT.left] = arrayQueueADT.array[i];
            }
        }
    }

    //Immutable
    public static Object[] toArray(ArrayQueueADT arrayQueueADT) {
        Object[] result = new Object[arrayQueueADT.size];
        fillArray(arrayQueueADT, result);
        return result;
    }

    private static void checkSize(ArrayQueueADT arrayQueueADT) {
        if (arrayQueueADT.array.length > arrayQueueADT.size) {
            return;
        }
        Object[] newArray = new Object[2 * arrayQueueADT.size + 1];
        fillArray(arrayQueueADT, newArray);
        arrayQueueADT.array = newArray;
        arrayQueueADT.left = 0;
        arrayQueueADT.right = arrayQueueADT.size;
    }

    //pre: element != null
    //post: size == size' + 1
    public static void enqueue(ArrayQueueADT arrayQueueADT, Object element) {
        assert element != null;
        checkSize(arrayQueueADT);
        if (arrayQueueADT.array.length == arrayQueueADT.right) {
            arrayQueueADT.right = 0;
        }
        arrayQueueADT.array[arrayQueueADT.right++] = element;
        ++arrayQueueADT.size;
    }

    //pre: size > 0
    //Immutable
    public static Object element(ArrayQueueADT arrayQueueADT) {
        assert arrayQueueADT.size > 0;
        return arrayQueueADT.array[arrayQueueADT.left];
    }

    //pre: size > 0
    //post: size == size' - 1
    public static Object dequeue(ArrayQueueADT arrayQueueADT) {
        assert arrayQueueADT.size > 0;
        Object result = element(arrayQueueADT);
        ++arrayQueueADT.left;
        if (arrayQueueADT.left == arrayQueueADT.array.length) {
            arrayQueueADT.left = 0;
        }
        --arrayQueueADT.size;

        return result;
    }

    //Immutable
    public static int size(ArrayQueueADT arrayQueueADT) {
        return arrayQueueADT.size;
    }

    //Immutable
    public static boolean isEmpty(ArrayQueueADT arrayQueueADT) {
        return (arrayQueueADT.size == 0);
    }

    //post: size == 0
    public static void clear(ArrayQueueADT arrayQueueADT) {
        arrayQueueADT.size = 0;
        arrayQueueADT.left = 0;
        arrayQueueADT.right = INITIAL_ARRAY_LENGTH;
    }

    //pre: element != null
    //post: size == size' + 1
    public static void push(ArrayQueueADT arrayQueueADT, Object element) {
        assert element != null;
        checkSize(arrayQueueADT);
        if (arrayQueueADT.left == 0) {
            arrayQueueADT.left = arrayQueueADT.array.length;
        }
        arrayQueueADT.array[--arrayQueueADT.left] = element;
        ++arrayQueueADT.size;
    }

    //pre: size > 0
    //Immutable
    public static Object peek(ArrayQueueADT arrayQueueADT) {
        assert arrayQueueADT.size > 0;
        return arrayQueueADT.array[arrayQueueADT.right - 1];
    }

    //pre: size > 0
    //post: size == size' - 1
    public static Object remove(ArrayQueueADT arrayQueueADT) {
        assert arrayQueueADT.size > 0;
        Object result = peek(arrayQueueADT);

        --arrayQueueADT.right;
        if (arrayQueueADT.right == 0) {
            arrayQueueADT.right = arrayQueueADT.array.length;
        }
        --arrayQueueADT.size;

        return result;
    }
}
