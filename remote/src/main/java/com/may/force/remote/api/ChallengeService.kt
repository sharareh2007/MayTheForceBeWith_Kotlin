package com.may.force.remote.api

import com.may.force.remote.helper.AppConstants
import com.may.force.remote.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface ChallengeService {

   // @GET(AppConstants.PREFIX_URL)
    //fun getPeople(@Query("page") page: Int): Call<ResultsPeopleModel?>?

   // @POST(AppConstants.PREFIX_URL_FAVORITE)
   // fun saveFavorite(@Body people: PeopleModel?): Call<Any?>?

    @GET(AppConstants.PREFIX_URL)
    fun getPeopleInfo():
            Observable<ResponseWrapper>

    @GET(AppConstants.PREFIX_URL)
    fun getPeopleInfoDetails(@Query("page") page: Int):
            Observable<ResponseWrapper>
}
