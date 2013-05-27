package no.wc;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Tester {

    public static final int N_THREADS = 1000;

    @Test
    public void testManyConnections() throws Exception {
        final URL url = new URL("http://localhost:8080/chat/notify");

        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

        List<Callable<String>> callables = getCallables(url, N_THREADS);

        executorService.invokeAll(callables);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("done!");
    }

    private List<Callable<String>> getCallables(URL url, int threads) {
        Callable<String> waitForNotification = getCallable(url);

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            tasks.add(waitForNotification);

        }
        return tasks;
    }

    private Callable<String> getCallable(final URL url) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Starting...");
                InputStream inputStream = url.openStream();
                inputStream.close();
                System.out.println("Finished...");
                return "";
            }
        };
    }
}
