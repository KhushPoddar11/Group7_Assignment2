public class QueueADT {

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Queue {
        private Node front, rear;
        private int size;

        public Queue() {
            this.front = this.rear = null;
            this.size = 0;
        }

        public void enqueue(int data) {
            Node newNode = new Node(data);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        public int dequeue() {
            if (front == null) {
                System.out.println("Queue Underflow");
                return -1;
            }
            int value = front.data;
            front = front.next;
            if (front == null)
                rear = null;
            size--;
            return value;
        }

        public int peek() {
            if (front == null) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return front.data;
        }

        public boolean isEmpty() {
            return front == null;
        }

        public int size() {
            return size;
        }

        public void display() {
            if (front == null) {
                System.out.println("Queue is Empty");
                return;
            }
            Node temp = front;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        System.out.println("Enqueue Operations:");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();

        System.out.println("\nDequeue Operation:");
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();

        System.out.println("\nPeek Operation:");
        System.out.println("Front of Queue: " + queue.peek());

        System.out.println("\nQueue Size:");
        System.out.println("Current Size: " + queue.size());
    }
}
