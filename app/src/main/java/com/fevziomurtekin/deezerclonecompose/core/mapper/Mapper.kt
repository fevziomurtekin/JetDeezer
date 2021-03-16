package com.fevziomurtekin.deezerclonecompose.core.mapper

import com.fevziomurtekin.deezerclonecompose.data.entities.AlbumEntity
import com.fevziomurtekin.deezerclonecompose.data.entities.GenreEntity
import com.fevziomurtekin.deezerclonecompose.data.response.AlbumData
import com.fevziomurtekin.deezerclonecompose.data.response.Genre
import timber.log.Timber

fun AlbumEntity?.mapper(): AlbumData? = this?.let { e->
    AlbumData(
            disk_number= e.disk_number,
            duration= e.duration,
            explicit_content_cover = 0,
            explicit_lyrics = e.explicit_lyrics,
            id = e.albumId,
            isrc = e.albumIsrc,
            explicit_content_lyrics = 0,
            readable = true,
            track_position = 0,
            type = e.type,
    )
}

fun AlbumData?.mapper(): AlbumEntity? = this?.let { d->
    AlbumEntity(
            disk_number= d.disk_number,
            duration= d.duration,
            explicit_lyrics = d.explicit_lyrics,
            albumId = d.id,
            albumIsrc= d.isrc,
            link = d.link,
            md5_image = d.md5_image,
            title = d.title,
            type = d.type,
            fav_time = d.fav_time,
    )
}

fun GenreEntity?.mapper(): Genre = this?.let { e->
    Genre(
            id = e.genreId,
            name= e.name.orEmpty(),
            picture= e.picture.orEmpty(),
            picture_medium= e.picture_medium.orEmpty(),
            type= "genre"
    )
} ?: run {
    Genre(
            "", "", "", "", ""
    )
}

fun Genre?.mapper(): GenreEntity = this?.let { d->
    GenreEntity(
            genreId = d.id,
            name= d.name,
            picture= d.picture,
            picture_big = "",
            picture_medium= d.picture_medium,
            picture_small= "",
            picture_xl= "",
            type= ""
    )
} ?: run {
    GenreEntity(
            genreId = "",
            name= "",
            picture= "",
            picture_big= "",
            picture_medium= "",
            picture_small= "",
            picture_xl= "",
            type= ""
    )
}


@JvmName("genreMapper")
fun List<GenreEntity?>?.mapper() = this?.let { list ->
    Timber.d("genreMapper : $list")
    val returnList = mutableListOf<Genre>()
    list.forEach {
        it.mapper().let { d->
            returnList.add(d)
        }
    }
    return returnList
} ?:run {
    Timber.d("genreMapper : is null")
    mutableListOf<Genre>()
}

@JvmName("dataMapper")
fun List<Genre>?.mapper() = this?.let { list ->
    val returnList = mutableListOf<GenreEntity>()
    list.forEach {
        it.mapper().let { e->
            returnList.add(e)
        }
    }
    return returnList
} ?: run {
    mutableListOf<GenreEntity>()
}

@JvmName("albumEntityToAlbumData")
fun List<AlbumEntity>.mapper(): List<AlbumData> {
    val returnList = mutableListOf<AlbumData>()
    this.forEach {
        it.mapper()?.let { a->
            returnList.add(a)
        }
    }
    return returnList
}
