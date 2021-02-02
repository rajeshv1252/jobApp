package codetest.virtusa.ui.main.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.ui.base.BaseItemViewModel
import codetest.virtusa.utils.log.Logger
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class JobsItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<JobInfo>(schedulerProvider, compositeDisposable, networkHelper) {
    companion object {
        const val TAG = "JobsItemViewModel"
    }

    val company: LiveData<String> = Transformations.map(data) { it.company }
    val location: LiveData<String> = Transformations.map(data) { it.location }
    val title: LiveData<String> = Transformations.map(data) { it.title }
    val date: LiveData<String> = Transformations.map(data) { it.created_at }
    val logo: LiveData<String> = Transformations.map(data) { it.company_logo }
    override fun onCreate() {
        Logger.d(TAG, "onCreateCalled")
    }
}