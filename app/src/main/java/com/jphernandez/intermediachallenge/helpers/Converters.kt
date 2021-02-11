package com.jphernandez.intermediachallenge.helpers

import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.data.Comic
import com.jphernandez.intermediachallenge.data.Event
import com.jphernandez.intermediachallenge.data.Thumbnail
import com.jphernandez.intermediachallenge.dto.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * En este archivo se encuentran todas las funciones
 * encargadas de convertir los objetos recibidos
 * de la api en objetos de datos
 *
 */

fun convertCharacters(queryResultDto: QueryResultDto) =
    queryResultDto.data.results.map(::convertCharacter)

fun convertUniqueCharacter(queryResultDto: QueryResultDto) =
    convertCharacter(queryResultDto.data.results[0])

fun convertCharacter(character: ResultDto): Character {
    return Character(
        character.id,
        character.name,
        character.description,
        character.modified,
        character.resourceURI,
        convertThumbnailImage(character.thumbnail),
        convertComics(character.comics.items)
    )
}

fun convertEvents(queryResultDto: QueryResultDto) =
    queryResultDto.data.results.map(::convertEvent)

fun convertEvent(eventDto: ResultDto) =
    Event(
        eventDto.id,
        eventDto.title,
        eventDto.description,
        eventDto.modified,
        eventDto.resourceURI,
        eventDto.thumbnail,
        eventDto.start,
        eventDto.end,
        convertComics(eventDto.comics.items)
    )

fun convertThumbnailImage(thumbnailDto: ThumbnailDto) =
    Thumbnail(thumbnailDto.path,
        thumbnailDto.extension)

fun convertComics(comics: List<ComicDto>?) =
    comics?.map(::convertComic)

fun convertComic(comicDto: ComicDto) =
    Comic(
        comicDto.resourceURI,
        comicDto.name
    )

/**
 * Esta funcion se encarga de convertir un string con la fecha
 * que devulve la api en una string con la fecha en el formato
 * requerido por el diseño
 */
fun dateConverter(stringDate: String?): String {
    if(stringDate == null) return ""
    val cal = Calendar.getInstance()
    val l = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
    cal.time = l.parse(stringDate) ?: Date()
    var month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("ES"))
    month = month?.substring(0,1)?.toUpperCase(Locale.ROOT) + month?.substring(1)?.toLowerCase(Locale.ROOT)
    return "${cal.get(Calendar.DAY_OF_MONTH)} de $month ${cal.get(Calendar.YEAR)}"
}