package com.example.omdapi.api;

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