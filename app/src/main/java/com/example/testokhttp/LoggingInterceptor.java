package com.example.testokhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class LoggingInterceptor implements Interceptor {
    private static final String TAG = "LoggingInterceptor";
//    chain的作用
//    获取request
//    处理request，返回response

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request

        Request request = chain.request();

        long startTime = System.nanoTime();
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        //处理request，得到response

//        Request request2 = new Request.Builder()
//                .url( "https://publicobject.com/helloworld.txt")
//                .build();

        //chain.proceed（）方法返回的响应结果是重定向的响应结果。
        Response response =  chain.proceed(request);//******


        long endTime = System.nanoTime();
        Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (endTime - startTime) / 1e6d, response.headers()));
        //返回response
        return response;
    }
}
