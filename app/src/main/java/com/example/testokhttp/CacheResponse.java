package com.example.testokhttp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CacheResponse {
    private final OkHttpClient client;

    public CacheResponse(File cacheDirectory) throws Exception {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(cacheDirectory, cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        final String[] response1Body = new String[1];
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                response1Body[0] = response.body().string();
                System.out.println("Response 1 response:          " + response);
                System.out.println("Response 1 cache response:    " + response.cacheResponse());
                System.out.println("Response 1 network response:  " + response.networkResponse());

            }
        });


        final String[] response2Body = new String[1];
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                response2Body[0] = response.body().string();
                System.out.println("Response 2 response:          " + response);
                System.out.println("Response 2 cache response:    " + response.cacheResponse());
                System.out.println("Response 2 network response:  " + response.networkResponse());
            }
        });





    }

}
