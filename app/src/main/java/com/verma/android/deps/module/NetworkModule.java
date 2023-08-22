package com.verma.android.deps.module;


import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.verma.android.deps.service.NetworkService;
import com.verma.android.deps.service.SharedPreferencesService;
import com.verma.android.template.App;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    public static String API_PATH;

    static {
        API_PATH = App.getInstance().API_PATH;
    }

    public String mBaseUrl;
    private boolean isSSL = false;


    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
        if (mBaseUrl.contains("https")) {
            isSSL = true;
        }
        if (!mBaseUrl.contains("http")) {
            mBaseUrl = "http://" + mBaseUrl;
        }

    }

    @Provides
    @Singleton
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        //gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


    @Provides
    @Singleton
    public Interceptor provideInterceptor(SharedPreferencesService sharedPreferencesService) {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();

                String method = original.method();
                // Customize the request
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("charset", "utf-8")
                        .header("SOURCE", "Android")
                        .header("MANUFACTURER", Build.MANUFACTURER)
                        .header("MODEL", Build.MODEL)
                        .header("BRAND", Build.BRAND)
                        .header("PRODUCT", Build.PRODUCT)
                        .header("WHO", App.getInstance().getPackageName())
                        .removeHeader("Pragma")
                        // .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                        .build();
                if (method.equalsIgnoreCase("GET")) {
                    request = original.newBuilder()
                            .header("Content-Type", "application/vnd.android.package-archive")
                            .header("Content-Type", "application/application/octet-stream")
                            .build();
                }

                okhttp3.Response response = chain.proceed(request);
                response.cacheResponse();
                return response;
            }
        };

    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Interceptor pInterceptor, Cache cache, SSLSocketFactory sslSocketFactory) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(pInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .hostnameVerifier((hostname, session) -> {
                    if (App.getInstance().BASEURL.contains(hostname)) {
                        return true;
                    } else {
                        HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                        return hv.verify(hostname, session);
                    }
                });
        if (isSSL) {
            builder.sslSocketFactory(sslSocketFactory);
        }
        return builder.build();
    }

    @Provides
    @Singleton
    public SSLSocketFactory provideSSLSocketFactory() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            //Do Nothing
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            //Do Nothing
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager

            return sslContext.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }

    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

}
