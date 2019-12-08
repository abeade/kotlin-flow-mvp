package com.gfabrego.moviesapp.popular.data.network.retrofitadapter

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {

        @JvmStatic
        fun create() = FlowCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }
        require(returnType is ParameterizedType) { "Flow return type must be parameterized Flow<Type>" }
        val responseType = getParameterUpperBound(0, returnType)
        val rawFlowType = getRawType(responseType)
        return if (rawFlowType == Response::class.java) {
            require(responseType is ParameterizedType) { "Response must be parameterized Response<Type>" }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }
}
