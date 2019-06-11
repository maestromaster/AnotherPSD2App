package com.i.anotherpsd2app.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.i.apiclient.SecurityHelper
import com.i.apiclient.datasource.MockDatasource
import com.i.apiclient.datasource.OauthDatasource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val MOCK_URL = "https://raw.githubusercontent.com"
const val SANDBOX_URL = "https://api.sandbox.ing.com"

val appModules : Module = module {

    factory { SecurityHelper(assetManager = get()) }

    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }

    single { createOkHttpClient(securityHelper = get()) }

    single { createWebService<MockDatasource>(get(), MOCK_URL) }

    single { createWebService<OauthDatasource>(get(), SANDBOX_URL) }

    single<AssetManager> { androidContext().assets }
}

fun createOkHttpClient(securityHelper: SecurityHelper): OkHttpClient {
    val sslFactory = securityHelper.provideWithSSLContext()
    // TODO: Set client certificate to okHttp

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

