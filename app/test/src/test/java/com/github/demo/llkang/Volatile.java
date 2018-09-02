package com.github.demo.llkang;


public class Volatile {

    public volatile int inc = 0;
    
    public void increase(){
        inc++;
    }
    
    public static void main(String[] args) {
        final Volatile v = new Volatile();
        
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++){
                        v.increase();
                    }
                }
            }.start();
        }
        while(Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(v.inc);
    }
    
}
