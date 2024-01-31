package ru.iji.test_cft.data.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import ru.iji.test_cft.data.dto.RandomUserDto.Dob
import ru.iji.test_cft.data.dto.RandomUserDto.Id
import ru.iji.test_cft.data.dto.RandomUserDto.Location
import ru.iji.test_cft.data.dto.RandomUserDto.Login
import ru.iji.test_cft.data.dto.RandomUserDto.Name
import ru.iji.test_cft.data.dto.RandomUserDto.Picture
import ru.iji.test_cft.data.dto.RandomUserDto.Registered
import javax.inject.Inject

@ProvidedTypeConverter
class Converter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun fromDobJson(jsonString: String): Dob? {
        return moshi.adapter(Dob::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toDobString(obj: Dob): String {
        return moshi.adapter(Dob::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromNameJson(jsonString: String): Name? {
        return moshi.adapter(Name::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toNameString(obj: Name): String {
        return moshi.adapter(Name::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromRegisteredJson(jsonString: String): Registered? {
        return moshi.adapter(Registered::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toRegisteredString(obj: Registered): String {
        return moshi.adapter(Registered::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromLocationJson(jsonString: String): Location? {
        return moshi.adapter(Location::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toLocationString(obj: Location): String {
        return moshi.adapter(Location::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromIdJson(jsonString: String): Id? {
        return moshi.adapter(Id::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toIdString(obj: Id): String {
        return moshi.adapter(Id::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromLoginJson(jsonString: String): Login? {
        return moshi.adapter(Login::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toLoginString(obj: Login): String {
        return moshi.adapter(Login::class.java).toJson(obj)
    }

    @TypeConverter
    fun fromPictureJson(jsonString: String): Picture? {
        return moshi.adapter(Picture::class.java).fromJson(jsonString)
    }

    @TypeConverter
    fun toPictureString(obj: Picture): String {
        return moshi.adapter(Picture::class.java).toJson(obj)
    }
}