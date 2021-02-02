package codetest.virtusa.di.component

import codetest.virtusa.di.ActivityScope
import codetest.virtusa.di.module.ActivityModule
import codetest.virtusa.ui.main.MainActivity
import dagger.Component


@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

}