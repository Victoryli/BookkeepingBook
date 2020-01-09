package com.xjt.test;


/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-08 15:30
 */
public class ArrayQueue {


    public static void main(String[] args) {

////单向队列
//        SingleQueue singleQueue = new SingleQueue();
//
//        singleQueue.setQueueLength(6);
//        singleQueue.inQueue("1");
//        singleQueue.inQueue("2");
//        singleQueue.inQueue("3");
//        singleQueue.inQueue("4");
//        singleQueue.inQueue("5");
//        singleQueue.inQueue("6");
//
//        singleQueue.outQueue();
//        singleQueue.outQueue();
//


//    //  单向队列优化
//        singleQueueOpt singleQueueOpt = new singleQueueOpt();
//
//        singleQueueOpt.setQueueLength(6);
//
//        singleQueueOpt.inQueue("1");
//        singleQueueOpt.inQueue("2");
//        singleQueueOpt.inQueue("3");
//        singleQueueOpt.inQueue("4");
//        singleQueueOpt.inQueue("5");
//        singleQueueOpt.inQueue("6");
//
//        singleQueueOpt.outQueue();
//        singleQueueOpt.outQueue();
//        singleQueueOpt.outQueue();
//
//        singleQueueOpt.inQueue("7");
//        singleQueueOpt.inQueue("8");
//        singleQueueOpt.inQueue("9");

//      // 双向队列
//        DoubleQueue doubleQueue = new DoubleQueue();
//
//        doubleQueue.setQueueLength(6);
//
//        doubleQueue.inQueue("1");
//        doubleQueue.inQueue("2");
//        doubleQueue.inQueue("3");
//        doubleQueue.inQueue("4");
//        doubleQueue.inQueue("5");
//        doubleQueue.inQueue("6");
//
//
//        doubleQueue.outQueue();
//        doubleQueue.outQueue();
//        doubleQueue.outQueue();
//        doubleQueue.outQueue();
//        doubleQueue.outQueue();
//        doubleQueue.outQueue();


    }


}

class SingleQueue {

    private int head = 0;
    private int tail = 0;
    private int length = 0;
    private String[] data;

    public SingleQueue() {

    }

    public void setQueueLength(int size) {
        this.length = size;
        this.data = new String[size];
    }

    public boolean inQueue(String e) {
        if (this.tail == this.length) {

            System.out.println("Queue is full");
            return false;

        }

        this.data[this.tail] = e;
        System.out.println("in: " + this.data[this.tail]);
        tail++;
        return true;
    }

    public String outQueue() {
        if (this.head == this.tail)
            return "false";
        String out = this.data[head];
        System.out.println("out: " + out);
        head++;
        return out;
    }
}


class singleQueueOpt {
    private int head = 0;
    private int tail = 0;
    private int length = 0;
    private String[] data;

    public singleQueueOpt() {

    }

    public void setQueueLength(int size) {
        this.length = size;
        this.data = new String[size];
    }

    public boolean inQueue(String e) {
        if (this.tail == this.length) {

            if (this.head == 0) {

                System.out.println("Queue is full");
                return false;
            }

            for (int i = 0; i < tail - head; i++) {
                data[i] = data[head];
            }

            tail = tail - head;
            head = 0;

        }

        data[this.tail] = e;
        System.out.println("in: " + data[this.tail]);
        tail++;
        return true;

    }

    public String outQueue() {
        if (this.tail == this.head) {
            System.out.println("Queue is empty");
            return "false";
        }

        String out = this.data[this.head];
        System.out.println("out: " + out);
        head++;
        return out;
    }
}


class DoubleQueue {
    private int head = 0;
    private int tail = 0;
    private int length = 0;
    private String[] data;

    public DoubleQueue() {

    }

    public void setQueueLength(int size) {
        this.length = size;
        this.data = new String[size];
    }

    public boolean inQueue(String e) {
        if ((this.tail + 1) % this.length == this.head) {
            System.out.println("Queue is full");
            return false;

        }

        this.data[this.tail] = e;
        System.out.println("doubleIn: " + this.data[this.tail]);
        tail++;
        return true;
    }

    public String outQueue() {
        if (this.head == this.tail) {
            System.out.println("Queue is empty");
            return "false";
        }
        String out = this.data[this.head];
        System.out.println("doubleOut: " + out);
        head++;
        return out;
    }


}
