package com.albert.presentation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T : Parcelable?> ParcelableNavType() =
    object : NavType<T>(isNullableAllowed = false) {

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value as Parcelable)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, T::class.java)
            } else {
                bundle.getParcelable(key) as T?
            }
        }

        override fun parseValue(value: String): T {
            return Gson().fromJson(Uri.decode(value), T::class.java)
        }
    }

inline fun <reified T : Parcelable> ParcelableListNavType() =
    object : NavType<ArrayList<T>>(isNullableAllowed = true) {

        override fun put(bundle: Bundle, key: String, value: ArrayList<T>) {
            bundle.putParcelableArrayList(key, value)
        }

        override fun get(bundle: Bundle, key: String): ArrayList<T>? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList(key, T::class.java)
            } else {
                bundle.getParcelableArrayList(key)
            }
        }

        override fun parseValue(value: String): ArrayList<T> {
            val listType = object : TypeToken<ArrayList<T>>() {}.type
            return Gson().fromJson(value, listType)
        }
    }


inline fun <reified T : Parcelable>  Bundle?.getParcelableArrayListNavType(key: String): ArrayList<T>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelableArrayList<T>(key, T::class.java)
    } else {
        this?.getParcelableArrayList<T>(key)
    }
}

object GsonSingleton {
    val gson: Gson by lazy {
        Gson()
    }
}

fun Parcelable.toJson(): String {
    return Uri.encode(GsonSingleton.gson.toJson(this))
}

fun <T : Parcelable> List<T>.toJson(): String {
    return Uri.encode(GsonSingleton.gson.toJson(this))
}