package com.wdk.util.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:TODO
 * 测试比较 读锁 写锁  synchronized之间的特性
 * @Author:wang_dk
 * @Date:2019/4/8 0008 22:06
 * @Version: v1.0
 **/

public class ReadWriteLockTest {

    public static void main(String[] args) {
//        CompareReadLockAndSynchronized.testMethod();

        CompareReadLockAndWriteLock.testMethod();
    }

}

/**
 * @Description:
 * 读写互斥  先读完 再 拿到写锁 进行写操作.
 * @Date 2019/4/8 0008 22:53
 * @Param
 * @return
 **/
class CompareReadLockAndWriteLock{
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void readFile(Thread thread){
        lock.readLock().lock();
        boolean readLock = lock.isWriteLocked();
        if (!readLock) {
            System.out.println("当前为读锁！");
        }
        Long timeStrat = System.currentTimeMillis();
        System.out.println("当前时间"+timeStrat);

        try {
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(20);
                    System.out.println(thread.getName()+"正在读取......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Long timeEnd = System.currentTimeMillis();
            System.out.println("读取完成,当前时间"+timeEnd);

            System.out.println("读取耗时:"+(timeEnd-timeStrat));
        }finally {
            System.out.println("释放读锁");
            lock.readLock().unlock();
        }
    }

    public static void writeFile(Thread thread){
        lock.writeLock().lock();

        boolean writeLock = lock.isWriteLocked();
        if (writeLock) {
            System.out.println("当前为写锁！");
        }

        Long timeStrat = System.currentTimeMillis();
        System.out.println("当前时间"+timeStrat);

        try {
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(20);
                    System.out.println(thread.getName()+"正在写入......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Long timeEnd = System.currentTimeMillis();
            System.out.println("写入完成,当前时间"+timeEnd);

            System.out.println("读取耗时:"+(timeEnd-timeStrat));
        }finally {
            System.out.println("释放写锁");
            lock.writeLock().unlock();
        }
    }

    public static void testMethod(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readFile(Thread.currentThread());
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                writeFile(Thread.currentThread());
            }
        });
    }

}



/**
 * @Description:
 * 比较 读锁(共享锁) 和 synchronized(独占重入锁) 读取时候性能关系.
 * 结论: readLock 性能牛逼
 * @Date 2019/4/8 0008 22:22
 * @Param
 * @return
 **/
class CompareReadLockAndSynchronized{
    //测试读锁 的共享锁特性
    //通过这个方法可以看到两个线程同时执行 所以读锁是线程共享锁.
    public static void readFileByReadLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();

        Long timeStrat = System.currentTimeMillis();
        System.out.println("当前时间"+timeStrat);


        for(int i=0;i<5;i++){
            try {
                Thread.currentThread().sleep(20);
                System.out.println(Thread.currentThread().getName()+"正在读取......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long timeEnd = System.currentTimeMillis();
        System.out.println("读取完成,当前时间"+timeEnd);

        System.out.println("读取耗时:"+(timeEnd-timeStrat));

        lock.readLock().unlock();
    }

    //测试synchronized关键字 独占锁效率 和 readLock做一下性能比较
    public static synchronized void readFileBySynchronized(){
        Long timeStrat = System.currentTimeMillis();
        System.out.println("当前时间"+timeStrat);


        for(int i=0;i<5;i++){
            try {
                Thread.currentThread().sleep(20);
                System.out.println(Thread.currentThread().getName()+"正在读取......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long timeEnd = System.currentTimeMillis();
        System.out.println("读取完成,当前时间"+timeEnd);

        System.out.println("读取耗时:"+(timeEnd-timeStrat));
    }

    public static void testMethod(){
       Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                CompareReadLockAndSynchronized.readFileByReadLock();
            }
        });
       t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                CompareReadLockAndSynchronized.readFileByReadLock();
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~华丽分割线~~~~~~~~~~~~~~~~~~~~~~~~");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                CompareReadLockAndSynchronized.readFileBySynchronized();
            }
        });
        t3.start();

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                CompareReadLockAndSynchronized.readFileBySynchronized();
            }
        });
        t4.start();
    }
}
