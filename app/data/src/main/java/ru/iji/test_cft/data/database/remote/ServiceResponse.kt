package ru.iji.test_cft.data.database.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.iji.test_cft.data.dto.RandomUserDto

@JsonClass(generateAdapter = true)
data class ServiceResponse(

    @Json(name = "results")
    val randomUsers: List<RandomUserDto>
)