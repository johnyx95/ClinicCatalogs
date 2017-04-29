package com.goldenie.devs.clinics_catalog.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kobec on 24.04.2017.
 */

public class CachingControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        if (request.method().equals("GET")) {

            request = request.newBuilder()
                    .header("Cache-Control", "public, max-stale=2419200")
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .header("Cache-Control", "max-age=600")
                .build();
    }
}
