package com.example.omdapi.api;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Юлия on 18.08.2017.
 */

@Module(includes = {ApiModule.class})
public class ServiceModule {

    @Provides
    @Singleton
    public WebService provideService(OmdbApi omdbApi) {
        return new WebService(omdbApi);
    }
}