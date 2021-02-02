package codetest.virtusa.data.remote

import codetest.virtusa.data.remote.response.JobResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.search)
    fun doGetJobData(
        @Query("search") search: String?
    ): Single<List<JobResponse>>


}