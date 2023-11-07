package io.coderangers.podcastlibrary.model

import com.google.gson.annotations.SerializedName


data class BestPodcastResponse(
    @SerializedName("has_next")
    val hasNext: Boolean,
    @SerializedName("has_previous")
    val hasPrevious: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("listennotes_url")
    val listennotesUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("next_page_number")
    val nextPageNumber: Int,
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("parent_id")
    val parentId: Int,
    @SerializedName("podcasts")
    val podcasts: List<Podcast>,
    @SerializedName("previous_page_number")
    val previousPageNumber: Int,
    @SerializedName("total")
    val total: Int
)


data class Podcast(
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("earliest_pub_date_ms")
    val earliestPubDateMs: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
    @SerializedName("extra")
    val extra: Extra,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_claimed")
    val isClaimed: Boolean,
    @SerializedName("itunes_id")
    val itunesId: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("latest_episode_id")
    val latestEpisodeId: String,
    @SerializedName("latest_pub_date_ms")
    val latestPubDateMs: Long,
    @SerializedName("listen_score")
    val listenScore: Int,
    @SerializedName("listen_score_global_rank")
    val listenScoreGlobalRank: String,
    @SerializedName("listennotes_url")
    val listennotesUrl: String,
    @SerializedName("looking_for")
    val lookingFor: LookingFor,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("rss")
    val rss: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_episodes")
    val totalEpisodes: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("update_frequency_hours")
    val updateFrequencyHours: Int,
    @SerializedName("website")
    val website: String
)







data class Extra(
    @SerializedName("amazon_music_url")
    val amazonMusicUrl: String,
    @SerializedName("facebook_handle")
    val facebookHandle: String,
    @SerializedName("google_url")
    val googleUrl: String,
    @SerializedName("instagram_handle")
    val instagramHandle: String,
    @SerializedName("linkedin_url")
    val linkedinUrl: String,
    @SerializedName("patreon_handle")
    val patreonHandle: String,
    @SerializedName("spotify_url")
    val spotifyUrl: String,
    @SerializedName("twitter_handle")
    val twitterHandle: String,
    @SerializedName("url1")
    val url1: String,
    @SerializedName("url2")
    val url2: String,
    @SerializedName("url3")
    val url3: String,
    @SerializedName("wechat_handle")
    val wechatHandle: String,
    @SerializedName("youtube_url")
    val youtubeUrl: String
)


data class LookingFor(
    @SerializedName("cohosts")
    val cohosts: Boolean,
    @SerializedName("cross_promotion")
    val crossPromotion: Boolean,
    @SerializedName("guests")
    val guests: Boolean,
    @SerializedName("sponsors")
    val sponsors: Boolean
)

data class CharactersApiResponse(
    val code: String?,
    val status: String?,
    val attributionText: String?,
    val data: CharactersData?
)

data class CharactersData(
    val total: Int?,
    val results: List<CharacterResult>?
)

data class CharacterResult(
    val id: Int?,
    val name: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<CharacterResultUrl>?,
    val thumbnail: CharacterThumbnail?,
    val comics: CharacterComics?
)

data class CharacterResultUrl(
    val type: String?,
    val url: String?
)

data class CharacterThumbnail(
    val path: String?,
    val extension: String?
)

data class CharacterComics(
    val items: List<CharacterComicsItems>?
)

data class CharacterComicsItems(
    val resourceURI: String?,
    val name: String?
)
