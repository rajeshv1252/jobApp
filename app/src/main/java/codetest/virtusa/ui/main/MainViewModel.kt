package codetest.virtusa.ui.main

import androidx.lifecycle.MutableLiveData
import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.data.repository.JobsRepository
import codetest.virtusa.ui.base.BaseViewModel
import codetest.virtusa.utils.log.Logger
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val jobsRepository: JobsRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val jobsList = MutableLiveData<List<JobInfo>>()
    val fetch: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        const val TAG = "MainViewModel"
    }

    override fun onCreate() {

    }

    fun getJobsInfo(query: String?) {
        Logger.d(TAG, "Fetching data....")
        if (checkInternetConnectionWithMessage()) {
            fetch.postValue(true)
            compositeDisposable.add(
                jobsRepository.fetchJobs(query)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        jobsList.postValue(it)
                        fetch.postValue(false)
                    }, {
                        Logger.e(TAG, it.toString())
                        fetch.postValue(false)
                    })
            )
        }
    }


    fun onDestroy() {
        compositeDisposable.clear()
    }


}