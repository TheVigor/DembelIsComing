package com.noble.activity.dembeliscoming

import android.app.Application

val soldierPrefs: SoldierPrefs by lazy {
    App.soldierPrefs!!
}

class App: Application() {
    companion object {
        var soldierPrefs: SoldierPrefs? = null
    }

    override fun onCreate() {
        soldierPrefs = SoldierPrefs(applicationContext)
        super.onCreate()
    }
}