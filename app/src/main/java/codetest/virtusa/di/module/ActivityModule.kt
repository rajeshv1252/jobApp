package codetest.virtusa.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import codetest.virtusa.data.repository.JobsRepository
import codetest.virtusa.ui.base.BaseActivity
import codetest.virtusa.ui.main.MainViewModel
import codetest.virtusa.ui.main.adapter.JobsAdapter
import codetest.virtusa.utils.ViewModelProviderFactory
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        jobsRepository: JobsRepository
    ): MainViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, jobsRepository)
        }).get(MainViewModel::class.java)


    @Provides
    fun provideJobsAdapter() = JobsAdapter(activity.lifecycle, ArrayList())


}