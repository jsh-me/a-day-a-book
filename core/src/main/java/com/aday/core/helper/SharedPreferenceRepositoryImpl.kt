package com.aday.core.helper

import android.content.Context
import io.reactivex.Observable

class SharedPreferenceRepositoryImpl(context: Context): SharedPreferenceRepository {
    private val instance = SharedPreferenceHelper.getInstance(context)


    override fun writePrefs(key: String?, value: Int?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: Long?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: String?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: Boolean) {
        instance.writePrefs(key, value)
    }

    override fun getPrefsBooleanValue(key: String?): Boolean {
        return instance.getPrefsBooleanValue(key)
    }

    override fun getPrefsBooleanValue(key: String?, defaultValue: Boolean): Boolean {
        return instance.getPrefsBooleanValue(key, defaultValue)
    }

    override fun resetPrefsIntValue(key: String?) {
        instance.resetPrefsIntValue(key)
    }

    override fun getPrefsIntValue(key: String?): Int {
        return instance.getPrefsIntValue(key)
    }

    override fun getPrefsIntValue(key: String?, defaultValue: Int?): Int {
        return instance.getPrefsIntValue(key, defaultValue)
    }

    override fun getPrefsLongValue(key: String?): Long {
        return instance.getPrefsLongValue(key)
    }

    override fun getPrefsStringValue(key: String?): String? {
        return instance.getPrefsStringValue(key)
    }

    override fun removePrefsValue(key: String?) {
        instance.removePrefsValue(key)
    }

    override fun getInteger(key: String?): Observable<Int?>? {
        return instance.getInteger(key)
    }
}