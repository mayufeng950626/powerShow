package com.mayufeng.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final    int finalI = i;
            exec.submit(new Callable<String>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public String call() throws Exception {
                    return "1";
                }
            });
        }
        exec.shutdown();
        while(true){
            if(exec.isTerminated()){
                System.out.println(list.toString());
                System.out.println("输出完毕");
                break;
            }
            Thread.sleep(1000);
        }
    }
}
