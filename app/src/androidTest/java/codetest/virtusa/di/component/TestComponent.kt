package codetest.virtusa.di.component

import codetest.virtusa.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
}