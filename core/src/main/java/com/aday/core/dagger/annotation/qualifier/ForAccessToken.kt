package com.aday.core.dagger.annotation.qualifier

import androidx.room.Query
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForAccessToken