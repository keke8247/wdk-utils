package com.wdk.util.thread.pro;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/15 10:21
 * @Since version 1.0.0
 */
public class Watcher implements Runnable{

    private Movie movie;

    public Watcher(Movie movie) {
        super();
        this.movie = movie;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            movie.watch();
        }
    }
}
