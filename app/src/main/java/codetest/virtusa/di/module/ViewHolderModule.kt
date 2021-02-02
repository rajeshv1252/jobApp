package codetest.virtusa.di.module

import androidx.lifecycle.LifecycleRegistry
import codetest.virtusa.di.ViewModelScope
import codetest.virtusa.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}