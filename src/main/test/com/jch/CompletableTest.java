package com.jch;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CompletableTest {
    @Test
    void shouldCatchAnException() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> {throw new CompletionException (new Exception("Failed"));});


        String combined = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
    }

}