package com.devflowteam.data.local.util

import androidx.room.TypeConverter
import com.devflowteam.data.local.room.movie_detail.model.BelongsToCollectionCache
import com.devflowteam.data.local.room.movie_detail.model.GenreCache
import com.devflowteam.data.local.room.movie_detail.model.ProductionCompanyCache
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromBelongsToCollection(belongsToCollectionCache: BelongsToCollectionCache): String =
        Json.encodeToString(belongsToCollectionCache)

    @TypeConverter
    fun toBelongsToCollection(json: String): BelongsToCollectionCache =
        Json.decodeFromString(json)

    @TypeConverter
    fun fromGenres(genres: List<GenreCache>): String =
        Json.encodeToString(genres)

    @TypeConverter
    fun toGenres(json: String): List<GenreCache> =
        Json.decodeFromString(json)

    @TypeConverter
    fun fromProductionCompanies(productionCompanies: List<ProductionCompanyCache>): String =
        Json.encodeToString(productionCompanies)

    @TypeConverter
    fun toProductionCompanies(json: String): List<ProductionCompanyCache> =
        Json.decodeFromString(json)

    @TypeConverter
    fun fromList(list: List<String>): String =
        Json.encodeToString(list)

    @TypeConverter
    fun toList(json: String): List<String> =
        Json.decodeFromString(json)
}