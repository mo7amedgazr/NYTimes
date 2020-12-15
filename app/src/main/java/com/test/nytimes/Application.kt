package com.test.nytimes

import android.app.Application
import com.test.nytimes.data.network.connectivity.ConnectivityInterceptor
import com.test.nytimes.data.network.connectivity.ConnectivityInterceptorImpl
import com.test.nytimes.data.network.service.networkModule
import com.test.nytimes.data.repository.AppRepository
import com.test.nytimes.data.repository.AppRepositoryImpl
import com.test.nytimes.ui.main.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { networkModule.apiClientHelper(instance()) }

        // Repository
        bind<AppRepository>() with singleton {
            AppRepositoryImpl(instance())
        }

        // ViewModel Factories
        bind() from provider {
            MainViewModelFactory(
                instance()
            )
        }
    }
}