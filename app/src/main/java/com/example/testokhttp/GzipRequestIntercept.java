package com.example.testokhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/** This interceptor compresses the HTTP request body. Many webservers can't handle this! */
/*利用Interceptor拦截器，对每次的网络请求或回复进行拦截，然后拿到请求url或回复response，并对url（response）进行改造来加入我们需要的公共参数*/
public class GzipRequestIntercept implements Interceptor {
    private static final String TAG = "LoggingInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
                originalRequest.url(), chain.connection(), originalRequest.headers()));

        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest);
        }

        //修改Request
        Request compressedRequest = originalRequest.newBuilder()//重用originalRequest;Share same connection pool, dispatcher, and configuration with the original client
                .header("Content-Encoding", "gzip")
                .method(originalRequest.method(), gzip(originalRequest.body()))
                //String method = request.method(); method 获取方法. get 还是 post.
                .build();

        Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                compressedRequest.url(), (10 - 1) / 1e6d, compressedRequest.headers()));
        return chain.proceed(compressedRequest);

        //修改response
//        Response originalResponse = chain.proceed(chain.request());//获取response
//        return originalResponse.newBuilder()//修改并返回
//                .header("Cache-Control", "max-age=60")
//                .build();
    }

    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override public MediaType contentType() {
                return body.contentType();
            }

            @Override public long contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
