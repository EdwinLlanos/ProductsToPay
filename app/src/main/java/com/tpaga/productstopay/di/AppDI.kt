package com.merqueo.di.koin

import com.tpaga.productstopay.di.networkModule
import com.tpaga.productstopay.di.repositoryModule
import com.tpaga.productstopay.di.viewModelModule
import org.koin.standalone.StandAloneContext

object AppDI {
    fun init() {
        StandAloneContext.loadKoinModules(
                viewModelModule,
                repositoryModule,
                networkModule

        )
    }
}
