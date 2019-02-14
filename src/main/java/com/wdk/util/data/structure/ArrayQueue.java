package com.wdk.util.data.structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description
 * 用数组实现队列
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/28 10:55
 * @Since version 1.0.0
 */
public class ArrayQueue<T> implements Iterable<T>{
    private T [] t;
    private int n; //元素个数
    private int first;//队头
    private int last;//队尾

    //初始化一个空的队列
    public ArrayQueue(){
        t = (T[]) new Object[5];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    //修改队列大小
    private void resize(int max){
        assert max>n :"扩容后的数量必须大于当前元素个数";

        T[] temp = (T[]) new Object[max];
        for(int i=0;i<n;i++){
            temp[i] = t[(first + i) % t.length];
        }
        t = temp;
        first = 0; //重置队头
        last = n;   //重置队尾
    }

    //入队
    public void enqueue(T element){
        //判断当前对列容量是否够用
        if(n == t.length){
            //扩容
            resize(t.length*2);
        }
        t[last++] = element;
        if(last == t.length){ //如果last超出数据下标,把last置为0 重复利用数组
            last = 0;
        }
        n++;
    }

    //出队
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("当前队列没有元素");
        }

        T element = t[first];
        t[first] = null;
        first++;

        if(first == t.length){// 循环利用数组，下一个队头在下标为0的地方
            first = 0;
        }
        n--;
        if (n > 0 && n == t.length/4) resize(t.length/2);

        return element;
    }

    //返回不出队
    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException("当前队列没有元素");
        }
        return t[first];
    }

    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<T>{
        private int num; //总元素个数

        public ReverseArrayIterator() {
            this.num = 0;
        }

        public boolean hasNext() {
            return num < n;
        }

        public T next() {
            if(hasNext()){
                T element = t[(num + first) % t.length];
                num ++;
                return element;
            }else{
                throw new NoSuchElementException("队列中没有元素");
            }
        }

        public void remove() {

        }
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<String>();
        queue.enqueue("nihao");

        System.out.println(queue.peek());

        for (int i=0;i<10;i++){
            queue.enqueue(i+"");
        }

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
