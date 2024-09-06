package com.example.hh_clone_test.search.data

import android.content.Context
import com.example.hh_clone_test.core.api.HeadHunterApi
import com.example.hh_clone_test.search.data.entities.ApiResponse
import com.example.hh_clone_test.search.domain.SearchInteractor
import com.example.hh_clone_test.util.Resource
import com.example.hh_clone_test.util.error_handling.DataError
import com.example.hh_clone_test.util.error_handling.NetworkHelper.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class SearchInteractorImpl(
    private val retrofitService: HeadHunterApi,
    private val context: Context
) : SearchInteractor {
    override suspend fun search(): Flow<Resource<ApiResponse, DataError.Network>> =
        flow<Resource<ApiResponse, DataError.Network>> {
            if (!isOnline(context)) {
                emit(Resource.Error(DataError.Network.NO_INTERNET))
            } else {
                var response: ApiResponse? = null
                try {
                    response = retrofitService.search()
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                404 -> emit(Resource.Error(DataError.Network.NOT_FOUND))
                                408 -> emit(Resource.Error(DataError.Network.REQUEST_TIMEOUT))
                                413 -> emit(Resource.Error(DataError.Network.PAYLOAD_TOO_LARGE))
                                else -> emit(Resource.Error(DataError.Network.UNKNOWN))
                            }
                        }
                        is IOException -> {
                            emit(Resource.Error(DataError.Network.UNKNOWN))
                        }
                        else -> emit(Resource.Error(DataError.Network.UNKNOWN))
                    }
                }
                if (response != null)
                    emit(Resource.Success(response))
            }
        }.flowOn(Dispatchers.IO)
}