package com.venkat.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {

    private String name;
    private int age;
    private double salary;
    private boolean active;

    public Employee(String name, int age, double salary, boolean active) {
        this.age = age;
        this.salary = salary;
        this.name = name;
        this.active = active;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public static List<Employee> getAllEmployees(){

        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Venkatram",54, 300000, true));
        list.add(new Employee("Srijan",18, 350000, true));
        list.add(new Employee("Vinny",22, 600000, true));
        list.add(new Employee("Chintu",24, 450000, true));
        list.add(new Employee("Tinku",25, 500000, true));
        list.add(new Employee("Chiru",27, 900000, false));
        return list;
    }

    public static void main(String[] args) {

        List<Employee> employees = getAllEmployees();
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .filter(Employee::isActive)
                .map(Employee::getName)
                .forEach(System.out::println);

    }

}

/**
 * Lambdas are special types:
 * Single Abstract Method
 * Single Method Interfaces
 * Functional Interface
 *
 * public interface Comparator<T>{
 *     int compare(T o1, T o2);
 * }
 *
 * public interface Callable<V>{
 *     V call() throws Exception;
 * }
 *
 * public interface Runnable{
 *     void run()
 * }
 *
 *
 */

/*
public interface Predicate<T> {
    boolean test(T t);
}

public interface Consumer<T> {
    void accept(T t);
}

public interface Supplier<T>{
    T get();
}

public interface Function<T, R> {
    R apply(T t);
}
 */
