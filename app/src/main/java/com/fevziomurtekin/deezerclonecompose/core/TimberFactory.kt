package com.fevziomurtekin.deezerclonecompose.core

import timber.log.Timber

object TimberFactory {
    fun setupOnDebug(){
        Timber.plant(Timber.DebugTree())
    }
}