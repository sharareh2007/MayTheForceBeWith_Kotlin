package com.may.force.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseWrapper(

    @SerializedName("peopleInfo") val peopleInfo:  List<PeopleInfoNetwork>,
    @SerializedName("peopleInfoDetails") val peopleInfoDetails: PeopleInfoDetailsNetwork
)