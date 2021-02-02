package codetest.virtusa

import android.app.Application
import codetest.virtusa.di.component.ApplicationComponent
import codetest.virtusa.di.component.DaggerApplicationComponent
import codetest.virtusa.di.module.ApplicationModule

class JobApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)
    }

    //Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}