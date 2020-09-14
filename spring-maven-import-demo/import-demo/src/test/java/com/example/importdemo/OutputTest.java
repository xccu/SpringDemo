package com.example.importdemo;

import com.example.maven.Output;
import org.junit.jupiter.api.Test;

public class OutputTest {
    @Test
    void testHello() {
        Output output = new Output();
        output.print("Hello World");
    }
}
