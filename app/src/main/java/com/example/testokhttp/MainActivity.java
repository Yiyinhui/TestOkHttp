package com.example.testokhttp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.TlsVersion;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;

public class MainActivity extends AppCompatActivity {
    private OkHttpClient mHttpClient;
    Request request;
    private TextView mClick;
    private ImageView mImageView;


    private static final String TAG = "MainActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //某些权限属于Protected Permission
        //例如：读写手机存储权限，仅仅在AndroidManifest.xml中申明是无法真正获取到权限的
        //代码动态的获取此权限
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

        initView();
    }

    private void initView() {

        mClick = findViewById(R.id.click);
        mImageView = findViewById(R.id.image);
        mClick.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View view) {
//                try {
//                    postRequest();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                //getRequestAsychro();
                //getRequestSychro();
                // authenticate();

//                try {
//                    header();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//                try {
//                    ParseJson();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//                File cachedirectory = new File(Environment.getExternalStorageDirectory(), "baidu.png");
//                CacheResponse c = null;
//                try {
//                    c = new CacheResponse(cachedirectory);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                try {
//                    c.run();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                try {
//                    run()
//                    ;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


//                try {
//                    ConfigureTimeouts configureTimeouts = new ConfigureTimeouts();
//                    configureTimeouts.run();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                try {
                    run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //File file2 = new File(Environment.getExternalStorageDirectory(),"baidu.png");
//        Glide.with(MainActivity.this)
//                .load(file2)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(mImageView);
    }

    /*
    interceptor
     */

    public void run() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //如果没有开发者自定义的Interceptor时，首先调用的RetryAndFollowUpInterceptor，如果有开发者自己定义的interceptor则调用开发者interceptor.
                //.addInterceptor(new LoggingInterceptor())//!!应用程序拦截器
//                                                                  不需要担心比如重定向和重试的中间响应。
//                                                                  总是被调用一次，即使HTTP响应结果是从缓存中获取的。
//                                                                  监控应用程序的原始意图。不关心例如OkHttp注入的头部字段If-None-Match。
//                                                                  允许短路，不调用Chain.proceed（）。
//                                                                  允许重试并多次调用Chain.proceed（）。

                //.addNetworkInterceptor(new GzipRequestIntercept())//网络拦截器
//                                                                      能够对中间的响应进行操作比如重定向和重试。
//                                                                      当发生网络短路时，不调用缓存的响应结果。
//                                                                      监控数据，就像数据再网络上传输一样。
//                                                                      访问承载请求的连接Connection。

                //.addInterceptor(new RetryAndFollowUpInterceptor(mHttpClient,true))
//        RetryAndFollowUpInterceptor，负责请求的重定向、重试等操作.
//        BridgeInterceptor，用户应用层和网络从桥梁。主要包含：1. 将用户的request，转变为网络层的request，比如添加各种请求头，UA ,Cookie , Content-Type等。
//                                                             2. 将网络层的response转变为用户层的response，比如解压缩，除去各种请求头等。
//        CacheInterceptor，封装的okhttp的缓存策略。
//        ConnectInterceptor，负责与服务器之间建立连接。
//        CallServerInterceptor，看名字也差不多了，负责请求服务器。


                //.connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT))//https方案
//                .connectionSpecs(Arrays.asList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)//自定义
//                        .tlsVersions(TlsVersion.TLS_1_2)
//                        .cipherSuites(
//                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                        .build()))
//        RESTRICTED_TLS is a secure configuration, intended to meet stricter compliance requirements.
//        MODERN_TLS is a secure configuration that connects to modern HTTPS servers.默认
//        COMPATIBLE_TLS is a secure configuration that connects to secure–but not current–HTTPS servers.
//        CLEARTEXT is an insecure configuration that is used for http:// URLs.

//                .certificatePinner(new CertificatePinner.Builder()//约定什么证书是可信的
//                        .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
//                        .build())
                //.sslSocketFactory()
                .eventListener(new EventListener() {
                    long callStartNanos;

                    private void printEvent(String name) {
                        long nowNanos = System.nanoTime();
                        if (name.equals("callStart")) {
                            callStartNanos = nowNanos;
                        }
                        long elapsedNanos = nowNanos - callStartNanos;
                        System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d, name);
                    }


                    @Override
                    public void callStart(Call call) {
                        printEvent("callStart");
                    }


                    @Override
                    public void dnsStart(Call call, String domainName) {
                        printEvent("dnsStart");
                    }


                    @Override
                    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
                        printEvent("dnsEnd");
                    }


                    @Override
                    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
                        printEvent("connectStart");
                    }


                    @Override
                    public void secureConnectStart(Call call) {
                        printEvent("secureConnectStart");
                    }


                    @Override
                    public void secureConnectEnd(Call call, Handshake handshake) {
                        printEvent("secureConnectEnd");
                    }


                    @Override
                    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
                        printEvent("connectEnd");
                    }


                    @Override
                    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
                        printEvent("connectFailed");
                    }


                    @Override
                    public void connectionAcquired(Call call, Connection connection) {
                        printEvent("connectionAcquired");
                    }


                    @Override
                    public void connectionReleased(Call call, Connection connection) {
                        printEvent("connectionReleased");
                    }


                    @Override
                    public void requestHeadersStart(Call call) {
                        printEvent("requestHeadersStart");
                    }


                    @Override
                    public void requestHeadersEnd(Call call, Request request) {
                        printEvent("requestHeadersEnd");
                    }


                    @Override
                    public void requestBodyStart(Call call) {
                        printEvent("requestBodyStart");
                    }


                    @Override
                    public void requestBodyEnd(Call call, long byteCount) {
                        printEvent("requestBodyEnd");
                    }


                    @Override
                    public void responseHeadersStart(Call call) {
                        printEvent("responseHeadersStart");
                    }


                    @Override
                    public void responseHeadersEnd(Call call, Response response) {
                        printEvent("responseHeadersEnd");
                    }


                    @Override
                    public void responseBodyStart(Call call) {
                        printEvent("responseBodyStart");
                    }


                    @Override
                    public void responseBodyEnd(Call call, long byteCount) {
                        printEvent("responseBodyEnd");
                    }


                    @Override
                    public void callEnd(Call call) {
                        printEvent("callEnd");
                    }


                    @Override
                    public void callFailed(Call call, IOException ioe) {
                        printEvent("callFailed");
                    }
                })
                .eventListener(new EventListener() {
                    @Override
                    public void callStart(Call call) {
                        super.callStart(call);
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .addHeader("User-Agent2", "123456")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    Log.d(TAG, "onResponse: " + response.body().string());
                    body.close();
                }
            }
        });
    }




    /*
    共用client
     */
//    private final OkHttpClient client = new OkHttpClient();//这句是重点，被公用的client.
//
//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("http://httpbin.org/delay/1") // This URL is served with a 1 second delay.
//                .build();
//
//        // Copy to customize OkHttp for this request.
//        OkHttpClient client1 = client.newBuilder()//克隆 最外面的client 共享连接池和线程池
//                .readTimeout(500, TimeUnit.MILLISECONDS)
//                .build();
//        client1.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("Response 1 succeeded: " + response);
//            }
//        });
//
//        // Copy to customize OkHttp for this request.
//        OkHttpClient client2 = client.newBuilder()//克隆 最外面的client 共享连接池和线程池
//                .readTimeout(3000, TimeUnit.MILLISECONDS)
//                .build();
//        client2.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("Response 2 succeeded: " + response);
//            }
//        });
//
//    }

    /*
    连接超时
     */

//    .retryOnConnectionFailure(true)
//    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //连接超时
//    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //读取超时
//    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //写超时


    //Use Call.cancel() to stop an ongoing call immediately. If a thread is currently writing a request or reading a response, it will receive an IOException.
//    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//    private final OkHttpClient client = new OkHttpClient();
//
//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
//                .build();
//
//        final long startNanos = System.nanoTime();
//        final Call call = client.newCall(request);
//
//        // Schedule a job to cancel the call in 1 second.
//        executor.schedule(new Runnable() {
//            @Override public void run() {
//                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
//                call.cancel();
//                System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
//            }
//        }, 1, TimeUnit.SECONDS);
//
//        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
//                        (System.nanoTime() - startNanos) / 1e9f, response);
//            }
//        });
//
//    }




    /*
    用Moshi解析json
     */
//    private final OkHttpClient client = new OkHttpClient();
//    private final Moshi moshi = new Moshi.Builder().build();
//    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void ParseJson() throws Exception {
//        Request request = new Request.Builder()
//                .url("https://api.github.com/gists/c2a7c39532239ff261be")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                Gist gist = gistJsonAdapter.fromJson(response.body().source());
//
//                for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
//                    System.out.println("Key:"+entry.getKey());
//                    System.out.println("Content:"+entry.getValue().content);
//                }
//            }
//        });
//
//    }
//
//    static class Gist {
//        Map<String, GistFile> files;
//    }
//
//    static class GistFile {
//        String content;
//    }


    /*
    Header
    */
    //When writing request headers,
    // use header(name, value) to set the only occurrence of name to value.
    // If there are existing values, they will be removed before the new value is added.
    // Use addHeader(name, value) to add a header without removing the headers already present.

    //When reading response a header,
    // use header(name) to return the last occurrence of the named value. Usually this is also the only occurrence!
    // If no value is present, header(name) will return null.
    // To read all of a field’s values as a list, use headers(name).

//    private final OkHttpClient client = new OkHttpClient();
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void header() throws Exception {
//        Request request = new Request.Builder()
//                .url("https://api.github.com/repos/square/okhttp/issues")
//                .header("User-Agent", "OkHttp Headers.java")
//                .addHeader("User-Agent", "OkHttp Headers.java2")
//                .addHeader("Accept", "application/json; q=0.5")
//                .addHeader("Accept", "application/vnd.github.v3+json")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                System.out.println("Server: " + response.header("Server"));
//                System.out.println("Date: " + response.header("Date"));
//                System.out.println("Vary: " + response.header("Vary"));
//
//                for (int i = 0, size = response.headers().size(); i < size; i++) {
//                    System.out.println(response.headers().name(i) + ": " + response.headers().value(i));
//                }
//            }
//        });
//
//
//    }


//    response.headers()
//    Server: nginx/1.10.0 (Ubuntu)
//    Date: Wed, 26 Jun 2019 06:36:56 GMT
//    Content-Type: text/plain
//    Content-Length: 1759
//    Last-Modified: Tue, 27 May 2014 02:35:47 GMT
//    Connection: keep-alive
//    ETag: "5383fa03-6df"
//    Accept-Ranges: bytes
//    ......body......


    public void getRequestAsychro() {

        Log.d("okHttp", "Click!!!");
        String url = "https://www.baidu.com/img/bd_logo1.png";
        //第一步
        mHttpClient = new OkHttpClient();
        //第二步
        Request request = new Request.Builder()
                .get()
                //Builder默认构造方法,初始化请求部分所需的请求方式method(get)和默认的请求头headers，

                .url(url)
                //Builder的url()方法设置请求的链接地址

                .build();
        //调用build()方法返回Request对象;在Builder的build()方法中，调用了Request的构造参数，将method,headers等成员设置给Request对象。

        /* okhttp3.Response response = null;*/
        /*response = mHttpClient.newCall(request).execute();*///同步请求
        //第三步
        //通过OkhttpClient的newCall()方法,构建一个RealCall对象,且其持有第一，二步创建的OkhttpClient和Request对象的引用
        //同时RealCall对象还会创建一个拦截器RetryAndFollowUpInterceptor
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okHttp", "Failure!!!" + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = response.body().byteStream();

//                /*
//                下载文件
//                 */
//                //拿到字节流
//                int len = 0;
//                //设置下载图片存储路径和名称
//                File file = new File(Environment.getExternalStorageDirectory(),"baidu.png");
//                FileOutputStream fos = new FileOutputStream(file);
//                byte[] buf = new byte[128];
//                while((len = is.read(buf))!= -1){
//                    fos.write(buf,0,len);
//                    Log.e("Download!!!", "onResponse: "+len +":"+Environment.getExternalStorageDirectory()+"baidu.png");
//                }
//                fos.flush();
//                fos.close();


                /*
                下载图片到指定ImageView,不可与下载图片至本地同时使用
                 */
                //使用 BitmapFactory 的 decodeStream 将图片的输入流直接转换为 Bitmap
                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                //在主线程中操作UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //然后将Bitmap设置到 ImageView 中
                        mImageView.setImageBitmap(bitmap);
                    }
                });


                is.close();


//                Log.d("okHttp", "Receive!!!");
//                String json = response.body().string();
//                Log.d("okHttp", json);
            }

        });

    }


    public void getRequestSychro() {

        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://www.baidu.com").method("GET", null).build();
        //3.创建一个call对象,参数就是Request请求对象
        final Call call = okHttpClient.newCall(request);
        //4.同步调用会阻塞主线程,这边在子线程进行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步调用,返回Response,会抛出IO异常
                    Response response = call.execute();
                    Log.d("okHttp", "Receive!!!" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //Post请求。
    private void postRequest() throws IOException {
        String url = "http://write.blog.csdn.net/postlist/0/0/enabled/1";
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "123";

        mHttpClient = new OkHttpClient();
        /*
        一般
         */
        //RequestBody body = RequestBody.create(mediaType, requestBody);
        /*
        post表单
         */
        //法一
//      RequestBody  body= new FormBody.Builder().add("name","zhangqilu").add("age","25").build();//.POST请求提交键值对
        //法二
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("username", "叶应是叶")
//                .addFormDataPart("password", "叶应是叶")
//                .build();
        /*
        另一种方法
         */
//        RequestBody body = new RequestBody() {
//            @Nullable
//            @Override
//            public MediaType contentType() {
//                return MediaType.parse("text/x-markdown; charset=utf-8");//"类型,字节码"
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                //sink.writeUtf8("I am Jdqm.");//提交流
//                File file = new File("123.txt");//提交文件
//            }
//        };


        /*
        异步post请求上传multipart
         */
        //上传的图片
        File file = new File(Environment.getExternalStorageDirectory(), "baidu.png");
        //2.通过new MultipartBody build() 创建requestBody对象，
        RequestBody body = new MultipartBody.Builder()
                //设置类型是表单
                .setType(MultipartBody.FORM)
                //添加数据
                .addFormDataPart("username", "zhangqilu")
                .addFormDataPart("age", "25")
                .addFormDataPart("image", "baidu.png", RequestBody.create(MediaType.parse("image/png"), file))
                .build();

        /*
        上传流
         */
        //法一
//        File fileup = new File("README.md");
//        final FileInputStream fileInputStream1=new FileInputStream(file);
//        RequestBody body1 = new RequestBody() {
//            @Nullable
//            @Override
//            public MediaType contentType() {
//                return MediaType.parse("text/x-markdown; charset=utf-8");//"类型,字节码"
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                OutputStream outputStream=sink.outputStream();
//                int length;
//                byte[] buffer = new byte[1024];
//                while ((length = fileInputStream1.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, length);
//                }
//            }
//        };
        //法二
//        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
//        RequestBody requestBody2=new RequestBody() {
//            @Nullable
//            @Override
//            public MediaType contentType() {
//                return MEDIA_TYPE_MARKDOWN;
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                int length;
//                byte[] buffer = new byte[1024];
//                while ((length = fileInputStream1.read(buffer)) != -1) {
//                    sink.write(buffer, 0, length);
//                }
//            }
//        };


        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okHttp", "Fail!!!" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("okHttp", response.body().string());
            }

        });
    }

    //需要用户登录后才能访问接口http://publicobject.com/secrets/hellosecret.txt
    public void authenticate() {


        mHttpClient = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {//认证
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        if (response.request().header("Authorization") != null) {
                            return null; // Give up, we've already attempted to authenticate.
                        }
                        Log.d("okHttp", "authenticator内");
                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();


        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okHttp", "Fail!!!" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("okHttp", response.body().string());
            }
        });
    }


//    new Thread(new Runnable() {
//        @Override
//        public void run() {
//            Response response = null;
//            try {
//                response = client.newCall(request).execute();
//                if (response.isSuccessful()) {
//                    Log.i("WY","打印POST响应的数据：" + response.body().string());
//                } else {
//                    throw new IOException("Unexpected code " + response);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }).start();


}
