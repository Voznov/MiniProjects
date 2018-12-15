import queue.*;

public class Test {
    public static void main(String[] args) {
        queue.Queue q = new LinkedQueue();
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
        System.out.println("#####");
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.push(i);
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.peek() + " " +
                            ArrayQueueModule.remove()
            );
        }
    }
}
