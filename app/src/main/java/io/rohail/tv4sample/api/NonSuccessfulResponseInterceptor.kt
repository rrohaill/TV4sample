package io.rohail.tv4sample.api

import okhttp3.Interceptor
import okhttp3.Response

class NonSuccessfulResponseInterceptor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request())
}
