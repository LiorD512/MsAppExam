package com.dahanlior.msappexam.api;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseWebServices implements MsApi {

    private Retrofit retrofit;
    private final String baseUrl;


    private final Map<Apis, Object> apiServices = new HashMap<>();

    BaseWebServices(@NonNull String baseUrl) {
        this.baseUrl = fixTrailingSlash(baseUrl);
    }

    private String fixTrailingSlash(String baseUrl) {

        if(!TextUtils.isEmpty(baseUrl)) {
            baseUrl += baseUrl.endsWith("/") ? "" : "/";
            return baseUrl;
        }

        return baseUrl;
    }

    private synchronized <T> T get(final Apis api) {
        if(apiServices.containsKey(api)) {
            //noinspection unchecked
            return (T) apiServices.get(api);
        }

        createRetrofitIfNecessary();

        return createApi(api);
    }

    private void createRetrofitIfNecessary() {
        if(retrofit == null) {
            synchronized (Retrofit.class) {
                if(retrofit == null) {
                    retrofit = createRetrofit(baseUrl);
                }
            }
        }
    }

    private Retrofit createRetrofit(@NonNull String baseUrl) {
        final String baseUrlFixed = fixTrailingSlash(baseUrl);
        return new Retrofit.Builder()
                .baseUrl(baseUrlFixed)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private <T> T createApi(Apis api) {
        //noinspection unchecked
        T apiInstance = (T) retrofit.create(api.getApiClass());
        apiServices.put(api, apiInstance);

        return apiInstance;
    }



    @Override
    public MoviesApi movieApi() {
        return get(Apis.Movie);
    }
}
