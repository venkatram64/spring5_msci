package com.venkat.work;


import com.venkat.test.SpringTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringTestSupport
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@DisplayName("Calculator Unit Tests")
public class CalculatorTests {

    @Autowired
    private Calculator calculator;

    @Test
    @DisplayName("+")
    void add(){
        assertEquals(5, calculator.add(2,3), () -> "2 + 3 = " + (2 + 3));
    }
}
