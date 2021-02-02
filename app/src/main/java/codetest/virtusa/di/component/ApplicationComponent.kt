package codetest.virtusa.di.component

import android.app.Application
import android.content.Context
import codetest.virtusa.JobApplication
import codetest.virtusa.data.remote.NetworkService
import codetest.virtusa.data.repository.JobsRepository
import codetest.virtusa.di.ApplicationContext
import codetest.virtusa.di.module.ApplicationModule
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: JobApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetWorkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getJobRepository(): JobsRepository
}