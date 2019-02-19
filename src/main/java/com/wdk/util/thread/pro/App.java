package com.wdk.util.thread.pro;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/15 10:22
 * @Since version 1.0.0
 */
public class App {
    public static void main(String[] args) {
        Movie movie = new Movie();

        Player player = new Player(movie);
        Watcher watcher = new Watcher(movie);

        new Thread(player).start();
        new Thread(watcher).start();
    }
}
