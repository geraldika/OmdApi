package com.example.omdbapi.api.module;

import com.example.omdbapi.api.OmdbApi;
import com.example.omdbapi.api.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class ServiceModule {

    @Provides
    @Singleton
    public WebService provideService(OmdbApi omdbApi) {
        return new WebService(omdbApi);
    }
}