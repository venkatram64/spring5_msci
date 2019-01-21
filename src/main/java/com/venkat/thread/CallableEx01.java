package com.venkat.thread;

import java.util.Random;
import java.util.concurrent.*;

public class CallableEx01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //submit task and accept the placeholder object for return value
        Future<Integer> future = executorService.submit(new Task());
        try{
            Integer result = future.get(); //blocking
            System.out.println("Result from the task is " + result);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call(){
            return new Random().nextInt();
        }
    }
}


