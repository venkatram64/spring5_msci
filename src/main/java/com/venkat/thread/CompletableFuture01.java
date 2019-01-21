package com.venkat.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFuture01 {

    public static void completableFuture01(){
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() ->{
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch(InterruptedException e){
                throw new IllegalStateException(e);
            }
            return "Venkatram";
        }).thenApply(name ->{
            return "Hello, " + name;
        }).thenApply(greeting ->{
            return greeting + ", Welcome to the Java Space.";
        });

        try {
            System.out.println(welcomeText.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        completableFuture01();
    }
}
