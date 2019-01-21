package com.venkat.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MyEx01 {

    public static void min(int[] numbers){
        int min = numbers[0];
        for(int i = 1; i < numbers.length; i++){
            if(min > numbers[i]){
                min = numbers[i];
            }
        }
        System.out.println("Minimum is :" + min);
        //by using streams
        /*OptionalInt min1 = IntStream.of(numbers).min();

        System.out.println("Minimum is :" + min1.getAsInt());*/

        min = IntStream.of(numbers).min().getAsInt();
        System.out.println("Minimum is :" + min);

        IntStream.of(numbers).min()
                .ifPresent(m -> System.out.println(m));

        IntSummaryStatistics stats = IntStream.of(numbers).summaryStatistics();
        System.out.printf("Statistics are as follows. \nMin is %d, Max is %d, Average is %f, Count is %d, Sum is %d", stats.getMin(),
                stats.getMax(), stats.getAverage(), stats.getCount(), stats.getSum());

    }

    public static void firstThreeMin(int[] numbers){
        //clone
        int[] copy = Arrays.copyOf(numbers, numbers.length);

        //sort
        Arrays.sort(copy);
        //pick first 3
        System.out.println("First three minimum are ");
        for (int i = 0; i < 3; i++){
            System.out.println(copy[i]);
        }

        //distinct

        IntStream.of(numbers)                   //create
                .distinct()
                .sorted()                       //process
                .limit(3)
                .forEach(System.out::println);  //Consume


    }

    public static void main(String[] args) {
        int num[] = {4, 1, 13, 90, 16, 2, 0};
        min(num);
        int num1[] = {4, 1, 13, 90, 16, 2, 0, 1};
        firstThreeMin(num1);
    }
}
