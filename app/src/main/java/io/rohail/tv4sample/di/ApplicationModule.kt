package io.rohail.tv4sample.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rohail.tv4sample.api.ApiFactory
import io.rohail.tv4sample.api.TvPlayApi
import io.rohail.tv4sample.media_play.data.MediaDataSource
import io.rohail.tv4sample.media_play.data.MediaDataSourceImpl
import io.rohail.tv4sample.media_play.data.MediaRepository
import io.rohail.tv4sample.media_play.data.MediaRepositoryImpl
import io.rohail.tv4sample.media_play.domain.usecase.FetchMediaUseCase
import io.rohail.tv4sample.media_play.domain.usecase.FetchMediaUseCaseImpl
import io.rohail.tv4sample.media_play.domain.usecase.GetMediaUiUseCase
import io.rohail.tv4sample.media_play.domain.usecase.GetMediaUiUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideMediaRepository(@ApplicationContext appContext: Context): MediaRepository =
        MediaRepositoryImpl(
            provideMediaDataSource(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideMediaDataSource(@ApplicationContext appContext: Context): MediaDataSource =
        MediaDataSourceImpl(
            service = provideApi(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext appContext: Context): TvPlayApi =
        ApiFactory.getApi(context = appContext)

}

//Usecases
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindFetchMediaUseCase(impl: FetchMediaUseCaseImpl): FetchMediaUseCase

    @Binds
    abstract fun bindGetMediaUiUseCase(impl: GetMediaUiUseCaseImpl): GetMediaUiUseCase
}
