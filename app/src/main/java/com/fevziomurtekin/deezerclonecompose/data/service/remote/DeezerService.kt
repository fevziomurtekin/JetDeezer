package com.fevziomurtekin.deezerclonecompose.data.service.remote

import com.fevziomurtekin.deezerclonecompose.data.DeezerResponse
import com.fevziomurtekin.deezerclonecompose.data.response.*
import com.fevziomurtekin.deezerclonecompose.data.response.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerService{

    @GET(Endpoints.GENRE)
    suspend fun fetchGenreList()
            : Response<DeezerResponse<GenreResponse>>

    @GET(Endpoints.ARTISTS)
    suspend fun fetchArtistList(@Path("genreId") genreId:String)
            : Response<DeezerResponse<ArtistsResponse>>

    @GET(Endpoints.ARTIST)
    suspend fun fetchArtistDetails(@Path("artistId") artistID:String)
            : Response<DeezerResponse<ArtistDetailResponse>>

    @GET(Endpoints.ALBUMS)
    suspend fun fetchArtistAlbums(@Path("artistId") artistID: String)
            : Response<DeezerResponse<ArtistAlbumResponse>>

    @GET(Endpoints.RELATED)
    suspend fun fetchArtistRelated(@Path("artistId") artistID: String)
            : Response<DeezerResponse<ArtistRelatedResponse>>

    @GET(Endpoints.ALBUM)
    suspend fun fetchAlbumDetails(@Path("albumId") albumId:String)
            : Response<DeezerResponse<AlbumDetailsResponse>>

    @GET(Endpoints.SEARCH)
    suspend fun fetchSearchAlbum(@Query("q") q:String)
            : Response<DeezerResponse<SearchResponse>>
}