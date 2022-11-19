package com.rafemo.ultimatedex.di

import com.rafemo.ultimatedex.BuildConfig
import com.rafemo.ultimatedex.data.remote.PokeAPI
import com.rafemo.ultimatedex.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeAPI
    ) = PokemonRepositoryImpl(api)

    @Singleton
    @Provides
    fun providePokeApi(): PokeAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE)
            .build()
            .create(PokeAPI::class.java)
    }

}