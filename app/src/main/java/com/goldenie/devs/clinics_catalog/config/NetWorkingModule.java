package com.goldenie.devs.clinics_catalog.config;

import android.content.Context;

import com.goldenie.devs.clinics_catalog.BuildConfig;
import com.goldenie.devs.clinics_catalog.utils.CachingControlInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Modifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kobec on 24.04.2017.
 */
@Module
public class NetWorkingModule {
    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Context context, HttpLoggingInterceptor httpLoggingInterceptor) {
        long SIZE_OF_CACHE = 10 * 1024 * 1024;
        Cache cache = new Cache(new File(context.getCacheDir(), "http"), SIZE_OF_CACHE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache)
                .addInterceptor(new CachingControlInterceptor())
                .addInterceptor(httpLoggingInterceptor);
        return httpClient.build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit(OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient);
    }
}
