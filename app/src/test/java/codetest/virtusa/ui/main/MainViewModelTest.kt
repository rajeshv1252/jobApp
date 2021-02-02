package codetest.virtusa.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.data.repository.JobsRepository
import codetest.virtusa.utils.network.NetworkHelper
import codetest.virtusa.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var jobsRepository: JobsRepository

    @Mock
    private lateinit var fetchObserver: Observer<Boolean>

    @Mock
    private lateinit var jobsListObserver: Observer<List<JobInfo>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()

        testScheduler = TestScheduler()

        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        mainViewModel = MainViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            jobsRepository
        )

        mainViewModel.jobsList.observeForever(jobsListObserver)

        mainViewModel.fetch.observeForever(fetchObserver)
    }

    @Test
    fun givenResponse200_whenFetchJobs_shoouldReturnJobsList() {
        val query = "android"
        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(arrayListOf<JobInfo>()))
            .`when`(jobsRepository)
            .fetchJobs(query)
        mainViewModel.getJobsInfo(query)
        testScheduler.triggerActions()
        assert(mainViewModel.fetch.value == false)
        assert(mainViewModel.jobsList.value == arrayListOf<JobInfo>())
        verify(fetchObserver).onChanged(true)
        verify(fetchObserver).onChanged(false)
        verify(jobsListObserver).onChanged(arrayListOf())

    }

    @After
    fun tearDown() {
        mainViewModel.fetch.removeObserver(fetchObserver)
        mainViewModel.jobsList.removeObserver(jobsListObserver)
    }


}