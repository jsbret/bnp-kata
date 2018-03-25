package com.kata;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class App {

    public static final int NB_REQUESTS = 50;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Integer> responses = IntStream.rangeClosed(0, NB_REQUESTS).boxed()
                .map(value -> executeQuery(value))
                .map(response -> Integer.valueOf(response.split(",")[0].split(":")[1]))
                .collect(Collectors.toList());


        int sum = IntStream.rangeClosed(0, NB_REQUESTS)
                .boxed()
                .map(value -> value + responses.get(value))
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum = " + sum);
        System.out.println("Execution time = " + (System.currentTimeMillis() - start) + "ms");

    }


    private static String executeQuery(int value) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://localhost:8081/request?value=" + value);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return br.lines().collect(Collectors.joining());
                }
            } else {
                return String.valueOf(responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(connection))
                connection.disconnect();
        }
    }


}
