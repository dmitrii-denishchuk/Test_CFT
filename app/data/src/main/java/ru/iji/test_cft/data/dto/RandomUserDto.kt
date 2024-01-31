package ru.iji.test_cft.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "random_users")
@JsonClass(generateAdapter = true)
data class RandomUserDto(

    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0,

    @ColumnInfo(name = "nat")
    @Json(name = "nat")
    val nat: String?,

    @ColumnInfo(name = "gender")
    @Json(name = "gender")
    val gender: String?,

    @ColumnInfo(name = "phone")
    @Json(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "dob")
    @Json(name = "dob")
    val dob: Dob?,

    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: Name?,

    @ColumnInfo(name = "registered")
    @Json(name = "registered")
    val registered: Registered?,

    @ColumnInfo(name = "location")
    @Json(name = "location")
    val location: Location?,

    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Id?,

    @ColumnInfo(name = "login")
    @Json(name = "login")
    val login: Login?,

    @ColumnInfo(name = "cell")
    @Json(name = "cell")
    val cell: String?,

    @ColumnInfo(name = "email")
    @Json(name = "email")
    val email: String?,

    @ColumnInfo(name = "picture")
    @Json(name = "picture")
    val picture: Picture?
) {

    @JsonClass(generateAdapter = true)
    data class Dob(

        @Json(name = "date")
        val date: String?,

        @Json(name = "age")
        val age: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Name(

        @Json(name = "last")
        val last: String?,

        @Json(name = "title")
        val title: String?,

        @Json(name = "first")
        val first: String?
    )

    @JsonClass(generateAdapter = true)
    data class Registered(

        @Json(name = "date")
        val date: String?,

        @Json(name = "age")
        val age: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Location(

        @Json(name = "country")
        val country: String?,

        @Json(name = "city")
        val city: String?,

        @Json(name = "street")
        val street: Street?,

        @Json(name = "timezone")
        val timezone: Timezone?,

        @Json(name = "postcode")
        val postcode: String?,

        @Json(name = "coordinates")
        val coordinates: Coordinates?,

        @Json(name = "state")
        val state: String?
    ) {

        @JsonClass(generateAdapter = true)
        data class Street(

            @Json(name = "number")
            val number: Int?,

            @Json(name = "name")
            val name: String?
        )

        @JsonClass(generateAdapter = true)
        data class Timezone(

            @Json(name = "offset")
            val offset: String?,

            @Json(name = "description")
            val description: String?
        )

        @JsonClass(generateAdapter = true)
        data class Coordinates(

            @Json(name = "latitude")
            val latitude: String?,

            @Json(name = "longitude")
            val longitude: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Id(

        @Json(name = "name")
        val name: String?,

        @Json(name = "value")
        val value: String?
    )

    @JsonClass(generateAdapter = true)
    data class Login(

        @Json(name = "sha1")
        val sha1: String?,

        @Json(name = "password")
        val password: String?,

        @Json(name = "salt")
        val salt: String?,

        @Json(name = "sha256")
        val sha256: String?,

        @Json(name = "uuid")
        val uuid: String?,

        @Json(name = "username")
        val username: String?,

        @Json(name = "md5")
        val md5: String?
    )

    @JsonClass(generateAdapter = true)
    data class Picture(

        @Json(name = "thumbnail")
        val thumbnail: String?,

        @Json(name = "large")
        val large: String?,

        @Json(name = "medium")
        val medium: String?
    )
}