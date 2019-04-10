package ru.blizzed.timetablespbu.data.utils

import android.content.Context
import android.content.res.AssetManager.ACCESS_STREAMING
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class AssetsUtil @Inject constructor(private val context: Context) {

    fun <T> getModel(folderName: String = "", fileName: String): T = openAsset(folderName, fileName)
        .let {
            Gson().fromJson(InputStreamReader(it), object : TypeToken<T>() {}.type)
        }

    fun <T> getListModel(folderName: String = "", fileName: String, genericClass: Class<T>): List<T> =
        openAsset(folderName, fileName)
            .let {
                Gson().fromJson(InputStreamReader(it), TypeToken.getParameterized(List::class.java, genericClass).type)
            }

    private fun openAsset(folderName: String, fileName: String): InputStream = context
        .assets
        .open(getFullPath(folderName, fileName), ACCESS_STREAMING)

    private fun getFullPath(folderName: String, fileName: String) = Uri.parse(folderName)
        .buildUpon()
        .appendPath(fileName)
        .toString()

}