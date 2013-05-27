package no.wc;

import org.junit.Test;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Tester {

    public static final int N_THREADS = 5;

    @Test
    public void testManyConnectionsAsync() throws Exception {
        batchRequests("http://localhost:8080/chat/notify");
        System.out.println("Async done!");
    }

    @Test
    public void testManyConnectionsSync() throws Exception {
        batchRequests("http://localhost:8080/chat/sync");
        System.out.println("Sync done!");
    }

    private void batchRequests(String spec) throws MalformedURLException, InterruptedException {
        final URL url = new URL(spec);

        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

        List<Callable<String>> callables = getCallables(url, N_THREADS);

        executorService.invokeAll(callables);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }


    private List<Callable<String>> getCallables(URL url, int threads) {

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            tasks.add(getCallable(url, i));

        }
        return tasks;
    }

    private Callable<String> getCallable(final URL url, final int i) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Starting..." + i);
                InputStream inputStream = url.openStream();
                inputStream.close();
                System.out.println("Finished..." + i);
                return "";
            }
        };
    }
}
