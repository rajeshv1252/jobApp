package codetest.virtusa.data.repository

import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.data.remote.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchJobs(text: String?): Single<List<JobInfo>> =
        networkService.doGetJobData(text).flatMap { list ->
            Observable.fromIterable(list)
                .map { item ->
                    JobInfo(
                        item.id, item.type, item.created_at, item.company, item.location
                        , item.title, item.description, item.company_logo
                    )
                }.toList()
        }
}