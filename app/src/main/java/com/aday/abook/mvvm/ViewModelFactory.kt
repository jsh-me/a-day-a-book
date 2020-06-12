package com.aday.abook.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
    }

//@IntoMap 어노테이션은 dagger로 하여금 이 리턴 타입을 map의 value에 넣으라고 알려준다
//그럼 이제 map에 value는 들어갔으니 키로 사용될값이 필요한데
//바로 @MapKey라는 어노테이션이 그 역할을 한다.
//ViewModelKey라는 어노테이션을 만들었다 그리고 여기에 @MapKey 어노테이션을 붙였다
//우리 생성한 ViewModelKey 라는 어노테이션이 이후에 Key 로 사용할 값을 알려주는 역할을 한다

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)

@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
