package com.wdk.util.data.structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description
 * 用链表实现栈(先进后出)
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/28 9:19
 * @Since version 1.0.0
 */
public class LinkStack<T> implements Iterable<T>{
    private Node<T> first; //第一个元素
    private int n;  //元素个数

    //初始化空栈
    public LinkStack(){
        first = null;
        n = 0;
    }

    //判断当前栈是否为空
    public boolean isEmpty(){
        return first == null;
    }

    //返回当前栈中元素个数
    public int size(){
        return n;
    }

    //入栈
    public void push(T t){
        //需要改变栈顶元素(栈先进去的在下面)
        Node<T> oldFirst = first;
        //栈顶为新push的元素
        first = new Node<T>();
        first.setData(t);
        first.setNext(oldFirst);
        n++;
    }

    //返回并弹出元素
    public T pop(){
        //先判断栈中是否有数据
        if(isEmpty()) throw new NoSuchElementException("当前栈中没有元素");
        T t = first.getData(); //取出需要返回的元素

        //弹出元素 改变first元素
        first = first.getNext();
        n--;
        return t;
    }

    //返回不弹出元素
    public T peek(){
        //先判断栈中是否有数据
        if(isEmpty()) throw new NoSuchElementException("当前栈中没有元素");
        return first.getData();
    }
    public Iterator<T> iterator() {
        return new ReverseLinkIterator();
    }

    public class ReverseLinkIterator implements Iterator<T>{

        private Node<T> current; //当前元素

        public ReverseLinkIterator(){
            this.current = first;//初始化 把当前元素指向栈顶
        }


        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if(hasNext()){
                T t = current.getData();
                current = current.getNext();
                return t;
            }else{
                throw new NoSuchElementException("当前栈中没有元素");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        LinkStack<String> linkStack = new LinkStack<String>();
        linkStack.push("nihao");
        System.out.println(linkStack.isEmpty());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.isEmpty());

        for(int i=0;i<5;i++){
            linkStack.push("hello"+i);
        }

        Iterator<String> iterator = linkStack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
