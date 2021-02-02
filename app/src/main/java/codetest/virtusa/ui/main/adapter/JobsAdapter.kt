package codetest.virtusa.ui.main.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.ui.base.BaseAdapter

class JobsAdapter(
    parentLifecycle: Lifecycle,
    private val jobs: ArrayList<JobInfo>
) : BaseAdapter<JobInfo, JobsItemViewHolder>(parentLifecycle, jobs) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobsItemViewHolder(parent)

}