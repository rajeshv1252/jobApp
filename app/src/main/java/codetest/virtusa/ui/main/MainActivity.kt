package codetest.virtusa.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import codetest.virtusa.R
import codetest.virtusa.di.component.ActivityComponent
import codetest.virtusa.ui.base.BaseActivity
import codetest.virtusa.ui.main.adapter.JobsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var jobsAdapter: JobsAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setUpView(savedInstanceState: Bundle?) {
        myRecycleView.layoutManager = linearLayoutManager
        myRecycleView.adapter = jobsAdapter
        mySearchView?.setOnQueryTextListener(this)
        mySearchView?.setOnCloseListener {
            jobsAdapter.clearData()
            return@setOnCloseListener true
        }
    }


    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.jobsList.observe(this, Observer {
            jobsAdapter.clearData()
            jobsAdapter.appendData(it)
        })

        viewModel.fetch.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getJobsInfo(query)
        mySearchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return true
    }


}