package com.flipkart.krystal.vajram.greeting;

import static com.flipkart.krystal.vajram.exec.VajramGraph.loadFromClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.flipkart.krystal.vajram.exec.KrystexVajramExecutor;
import com.flipkart.krystal.vajram.exec.VajramGraph;
import com.flipkart.krystal.vajram.samples.greeting.GreetingVajram;
import com.flipkart.krystal.vajram.samples.greeting.GreetingVajramRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Test;

class KrystexVajramExecutorTest {

  @Test
  void requestExecution_vajramWithNoDependencies_success()
      throws ExecutionException, InterruptedException, TimeoutException {
    VajramGraph vajramGraph =
        loadFromClasspath("com.flipkart.krystal.vajram.samples.greeting");
    try (KrystexVajramExecutor krystexVajramExecutor =
        new KrystexVajramExecutor(vajramGraph, "qwerty")) {
      CompletableFuture<String> result =
          krystexVajramExecutor.requestExecution(GreetingVajram.ID, GreetingVajramRequest.builder().userId("Suma").build());
      assertEquals("Hello! Suma", result.get(5, TimeUnit.HOURS));
    }
  }
}
