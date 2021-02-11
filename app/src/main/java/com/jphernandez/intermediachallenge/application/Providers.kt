package com.jphernandez.intermediachallenge.application

import com.jphernandez.intermediachallenge.characterDetails.CharacterDetailsFragment
import com.jphernandez.intermediachallenge.characterList.CharacterListFragment
import com.jphernandez.intermediachallenge.eventList.EventListFragment
import com.jphernandez.intermediachallenge.repositories.CharacterRepository
import com.jphernandez.intermediachallenge.repositories.CharacterRepositoryImpl
import com.jphernandez.intermediachallenge.repositories.EventRepository
import com.jphernandez.intermediachallenge.repositories.EventRepositoryImpl
import com.jphernandez.intermediachallenge.services.*
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Clase con los providers (Dagger2) para los distintos servicios y repositorios necesarios en la app
 */
@Module
open class Providers {

    @Provides
    @Singleton
    fun providesRetrofit2(): Retrofit {
        val serverUrl = "https://gateway.marvel.com"
        val httpClient = OkHttpClient.Builder()

        return Retrofit.Builder()
            .baseUrl(serverUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun characterServiceProvider(retrofit: Retrofit): CharacterService =
        CharacterServiceImpl(ServiceRetrofit.create(retrofit))

    @Provides
    @Singleton
    fun characterRepositoryProvider(characterService: CharacterService): CharacterRepository =
        CharacterRepositoryImpl(characterService)

    @Provides
    @Singleton
    fun eventServiceProvider(retrofit: Retrofit): EventService =
        EventServiceImpl(ServiceRetrofit.create(retrofit))

    @Provides
    @Singleton
    fun eventRepositoryProvider(eventService: EventService): EventRepository =
        EventRepositoryImpl(eventService)

}

@Singleton
@Component(modules = [Providers::class])
interface AppComponent {
    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterDetailsFragment: CharacterDetailsFragment)
    fun inject(eventListFragment: EventListFragment)
}