package com.wdk.util.thread.pro;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/15 10:18
 * @Since version 1.0.0
 */
public class Player implements Runnable{

    private Movie movie;

    public Player(Movie movie) {
        super();
        this.movie = movie;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            if(i%2==0){
                movie.play("左青龙");
            }else{
                movie.play("右白虎");
            }
        }
    }
}
