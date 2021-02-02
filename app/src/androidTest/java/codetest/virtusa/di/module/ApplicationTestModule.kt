package codetest.virtusa.di.module

import android.app.Application
import android.content.Context
import codetest.virtusa.BuildConfig
import codetest.virtusa.JobApplication
import codetest.virtusa.data.remote.NetworkService
import codetest.virtusa.data.remote.Networking
import codetest.virtusa.di.ApplicationContext
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.RxSchedulerProvider
import codetest.virtusa.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationTestModule(private val application:JobApplication){
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL, application.cacheDir,
            10 * 1024 * 1024
        )

    @Provides
    @Singleton
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}