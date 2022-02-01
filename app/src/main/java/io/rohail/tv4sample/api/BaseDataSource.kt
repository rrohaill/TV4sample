package io.rohail.tv4sample.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>) = try {
        handleResponse(call())
    } catch (e: Throwable) {
        handleError<T>(e)
    }

    private fun <T> handleError(e: Throwable): Result2.Error<T> {
        return Result2.Error(
            message = e.message ?: "",
            error = ApiError(
                message = e.message ?: "",
                type = e.javaClass.name
            )
        )
    }

    private fun <T> handleResponse(response: Response<T>): Result2<T> {
        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) Result2.Success(body)
            else Result2.Error("Unknown error", ApiError(message = "Unknown Error"))
        }
        return buildApiError(response)
    }

    private fun <T> buildApiError(error: Response<T>): Result2<T> {
        val msg = "Network call has failed for a following reason: ${error.message()}"
        val err = error.errorBody()?.let {
            val converter: Converter<ResponseBody, ApiError> = RetrofitFactory.retrofit!!
                .responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))
            converter.convert(it)
        } ?: ApiError(
            message = "Error body is null or failed to parse it"
        )
        return Result2.Error(msg, err)
    }
}

