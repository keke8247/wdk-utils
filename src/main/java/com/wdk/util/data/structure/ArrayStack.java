package com.wdk.util.data.structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description
 * 用数组实现一个栈
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/28 8:46
 * @Since version 1.0.0
 */
public class ArrayStack<T> implements Iterable<T> {

    private T[] a; //素组表示栈
    private int n; //栈中元素个数


    //初始化空栈
    public ArrayStack(){
        a = (T[]) new Object[4];
        n = 0;
    }

    //判断栈是否为空栈
    public boolean isEmpty(){
        return n == 0;
    }

    //返回栈中元素个数
    public int size(){
        return n;
    }

    //改变栈的大小
    private void resize(int capacity){
        assert capacity >=n : "改变后的容量应该大于当前元素个数";

        T [] temp = (T[]) new Object[capacity];

        //把原先的元素转移掉当前栈
        for(int i=0;i<n;i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    //压入元素入栈
    public void push(T element){
        //先判断当前栈的容量是否够用,不够则扩容
        if(n == a.length){
            resize(a.length*2);
        }
        a[n++] = element;
    }

    //弹出并返回元素
    public T pop() throws Exception {
        //判断当前栈是否为空
        if(isEmpty()){
            throw new NoSuchElementException("当前栈为空,没有元素可以返回");
        }

        T t = a[n-1]; //获取最后一个元素
        a[n-1] = null; //把最后一个栈置空
        n--;

        //判断是否需要调整栈的大小
        if(n>0 && n <= a.length/4){
            resize(a.length/2);
        }
        return t;
    }

    //返回但不弹出栈顶元素
    public T peek() throws Exception {
        if(isEmpty()){
            throw new NoSuchElementException("当前栈为空,没有元素可以返回");
        }
        return a[n-1];
    }

    //返回一个迭代器  遍历取栈中数据
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<T>{

        private int i;//计数器

        public ReverseArrayIterator(){
            i = n-1;
        }

        public boolean hasNext() {
            return i>=0;
        }

        public T next() {
            if(hasNext()){
                return a[i--];
            }else{
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayStack stack = new ArrayStack();
        System.out.println(stack.isEmpty());

        for(int i=0;i<10;i++){
            stack.push("hello"+i);
            System.out.println(stack.size());
        }

        System.out.println(stack.isEmpty());

        System.out.println(stack.pop());

        System.out.println(stack.size());

        System.out.println(stack.peek());
        Iterator iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
