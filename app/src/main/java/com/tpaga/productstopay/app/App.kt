package com.tpaga.productstopay.app

import android.app.Application
import com.merqueo.di.koin.AppDI
import com.tpaga.productstopay.cache.AppCache

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDI.init()
        AppCache.init(this)
    }
}
