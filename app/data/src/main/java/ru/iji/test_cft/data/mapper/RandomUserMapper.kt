package ru.iji.test_cft.data.mapper

import ru.iji.test_cft.data.dto.RandomUserDto
import ru.iji.test_cft.domain.models.RandomUserModel

object RandomUserMapper {

    fun RandomUserDto.toRandomUserModel(): RandomUserModel {
        return RandomUserModel(
            nat = nat,
            gender = gender,
            phone = phone,
            dob = dob?.toDob(),
            name = name?.toName(),
            registered = registered?.toRegistered(),
            location = location?.toLocation(),
            id = id?.toId(),
            login = login?.toLogin(),
            cell = cell,
            email = email,
            picture = picture?.toPicture()
        )
    }

    private fun RandomUserDto.Dob.toDob(): RandomUserModel.Dob {
        return RandomUserModel.Dob(
            date = date?.substring(0..9),
            age = age
        )
    }

    private fun RandomUserDto.Name.toName(): RandomUserModel.Name {
        return RandomUserModel.Name(
            last = last,
            title = title?.lowercase(),
            first = first
        )
    }

    private fun RandomUserDto.Registered.toRegistered(): RandomUserModel.Registered {
        return RandomUserModel.Registered(
            date = date?.substring(0..9),
            age = age
        )
    }

    private fun RandomUserDto.Location.toLocation(): RandomUserModel.Location {
        return RandomUserModel.Location(
            country = country,
            city = city,
            street = street?.toStreet(),
            timezone = timezone?.toTimezone(),
            postcode = postcode,
            coordinates = coordinates?.toCoordinates(),
            state = state
        )
    }

    private fun RandomUserDto.Location.Street.toStreet(): RandomUserModel.Location.Street {
        return RandomUserModel.Location.Street(
            number = number,
            name = name
        )
    }

    private fun RandomUserDto.Location.Timezone.toTimezone(): RandomUserModel.Location.Timezone {
        return RandomUserModel.Location.Timezone(
            offset = offset,
            description = description
        )
    }

    private fun RandomUserDto.Location.Coordinates.toCoordinates(): RandomUserModel.Location.Coordinates {
        return RandomUserModel.Location.Coordinates(
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun RandomUserDto.Id.toId(): RandomUserModel.Id {
        return RandomUserModel.Id(
            name = name,
            value = value
        )
    }

    private fun RandomUserDto.Login.toLogin(): RandomUserModel.Login {
        return RandomUserModel.Login(
            sha1 = sha1,
            password = password,
            salt = salt,
            sha256 = sha256,
            uuid = uuid,
            username = username,
            md5 = md5
        )
    }

    private fun RandomUserDto.Picture.toPicture(): RandomUserModel.Picture {
        return RandomUserModel.Picture(
            thumbnail = thumbnail,
            large = large,
            medium = medium
        )
    }
}