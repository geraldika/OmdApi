package com.example.omdbapi.api.module;

import com.example.omdbapi.api.OmdbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public OmdbApi provideApi(Retrofit retrofit) {
        return retrofit.create(OmdbApi.class);
    }
}

