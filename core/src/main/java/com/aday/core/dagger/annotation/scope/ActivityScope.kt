package com.aday.core.dagger.annotation.scope

import javax.inject.Scope

//Scope: 해당 클래스의 단일 인스턴스가 존재하는 범위
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope