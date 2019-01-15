package com.tpaga.productstopay.app

import android.app.Application
import com.merqueo.di.koin.AppDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDI.init()
    }
}
