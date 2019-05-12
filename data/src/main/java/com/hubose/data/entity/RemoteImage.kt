package com.hubose.data.entity


import com.squareup.moshi.Json

data class RemoteImage(
    @Json(name = "color")
    val color: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<CurrentUserCollection>,
    @Json(name = "description")
    val description: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User?,
    @Json(name = "width")
    val width: Int
) {
    data class User(
        @Json(name = "bio")
        val bio: String,
        @Json(name = "id")
        val id: String,
        @Json(name = "instagram_username")
        val instagramUsername: String,
        @Json(name = "links")
        val links: Links,
        @Json(name = "location")
        val location: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "portfolio_url")
        val portfolioUrl: String,
        @Json(name = "profile_image")
        val profileImage: ProfileImage,
        @Json(name = "total_collections")
        val totalCollections: Int,
        @Json(name = "total_likes")
        val totalLikes: Int,
        @Json(name = "total_photos")
        val totalPhotos: Int,
        @Json(name = "twitter_username")
        val twitterUsername: String,
        @Json(name = "username")
        val username: String
    ) {
        data class Links(
            @Json(name = "html")
            val html: String,
            @Json(name = "likes")
            val likes: String,
            @Json(name = "photos")
            val photos: String,
            @Json(name = "portfolio")
            val portfolio: String,
            @Json(name = "self")
            val self: String
        )

        data class ProfileImage(
            @Json(name = "large")
            val large: String,
            @Json(name = "medium")
            val medium: String,
            @Json(name = "small")
            val small: String
        )
    }

    data class Urls(
        @Json(name = "full")
        val full: String,
        @Json(name = "raw")
        val raw: String,
        @Json(name = "regular")
        val regular: String,
        @Json(name = "small")
        val small: String,
        @Json(name = "thumb")
        val thumb: String
    )

    data class Links(
        @Json(name = "download")
        val download: String,
        @Json(name = "download_location")
        val downloadLocation: String,
        @Json(name = "html")
        val html: String,
        @Json(name = "self")
        val self: String
    )

    data class CurrentUserCollection(
        @Json(name = "cover_photo")
        val coverPhoto: Any?,
        @Json(name = "curated")
        val curated: Boolean,
        @Json(name = "id")
        val id: Int,
        @Json(name = "published_at")
        val publishedAt: String,
        @Json(name = "title")
        val title: String,
        @Json(name = "updated_at")
        val updatedAt: String,
        @Json(name = "user")
        val user: Any?
    )
}