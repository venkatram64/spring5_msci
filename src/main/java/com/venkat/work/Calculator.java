package com.venkat.work;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public double add(double a, double b){
        return a + b;
    }
}
