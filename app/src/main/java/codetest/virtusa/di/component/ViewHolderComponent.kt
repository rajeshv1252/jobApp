package codetest.virtusa.di.component

import codetest.virtusa.di.ViewModelScope
import codetest.virtusa.di.module.ViewHolderModule
import codetest.virtusa.ui.main.adapter.JobsItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: JobsItemViewHolder)

}