package codetest.virtusa.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import codetest.virtusa.R
import codetest.virtusa.data.model.JobInfo
import codetest.virtusa.di.component.ViewHolderComponent
import codetest.virtusa.ui.base.BaseItemViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_job.view.*

class JobsItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<JobInfo, JobsItemViewModel>(R.layout.item_job, parent) {
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) =
        viewHolderComponent.inject(this)

    override fun setupView(view: View) {
        view.setOnClickListener {
            //configure listener
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.company.observe(this, Observer {
            itemView.tv_item_company.text = it
        })

        viewModel.date.observe(this, Observer {
            //itemView.tv_item_date.text = it
        })

        viewModel.location.observe(this, Observer {
            itemView.tv_item_location.text = it
        })

        viewModel.logo.observe(this, Observer {
            Glide.with(itemView.context).load(it).into(itemView.tv_item_icon)
        })

        viewModel.title.observe(this, Observer {
            itemView.tv_item_title.text = it
        })
    }

}