package com.georgcantor.newsproject.di.component

import android.app.Application
import com.georgcantor.newsproject.di.module.DatabaseModule
import com.georgcantor.newsproject.di.module.RetrofitModule
import com.georgcantor.newsproject.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class, RetrofitModule::class])
@ApplicationScope
interface AppComponent {

    fun plusFragmentComponent(): FragmentComponent

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

}