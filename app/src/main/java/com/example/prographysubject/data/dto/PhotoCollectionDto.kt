import com.example.prographysubject.domain.model.ImageSize
import com.example.prographysubject.domain.model.PhotoCollection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
data class PhotoCollectionDto(
    val id: String,
    val slug: String,

    @SerialName("alternative_slugs")
    val alternativeSlugs: AlternativeSlugs,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("promoted_at")
    val promotedAt: JsonElement? = null,

    val width: Long,
    val height: Long,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val description: JsonElement? = null,

    @SerialName("alt_description")
    val altDescription: String? = null,

    val breadcrumbs: JsonArray,
    val urls: Urls,
    val links: WelcomeLinks,
    val likes: Long,

    @SerialName("liked_by_user")
    val likedByUser: Boolean,

    @SerialName("current_user_collections")
    val currentUserCollections: JsonArray,

    val sponsorship: JsonElement? = null,


    @SerialName("asset_type")
    val assetType: AssetType,

    val user: User,

    val tags: List<Tag> = emptyList(),

    @SerialName("related_collections")
    val relatedCollections: RelatedCollections? = null
)

@Serializable
data class AlternativeSlugs(
    val en: String,
    val es: String,
    val ja: String,
    val fr: String,
    val it: String,
    val ko: String,
    val de: String,
    val pt: String
)

@Serializable
enum class AssetType(val value: String) {
    @SerialName("photo")
    Photo("photo");
}

@Serializable
data class WelcomeLinks(
    val self: String,
    val html: String,
    val download: String,

    @SerialName("download_location")
    val downloadLocation: String
)

@Serializable
data class RelatedCollections(
    val total: Long,
    val type: String,
    val results: List<Result>
)

@Serializable
data class Result(
    val id: String,
    val title: String,
    val description: String? = null,

    @SerialName("published_at")
    val publishedAt: String,

    @SerialName("last_collected_at")
    val lastCollectedAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    val featured: Boolean,

    @SerialName("total_photos")
    val totalPhotos: Long,

    val private: Boolean,

    @SerialName("share_key")
    val shareKey: String,

    val tags: List<Tag>,
    val links: ResultLinks,
    val user: User,

    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto,

    @SerialName("preview_photos")
    val previewPhotos: List<PreviewPhoto>
)

@Serializable
data class CoverPhoto(
    val id: String,
    val slug: String,

    @SerialName("alternative_slugs")
    val alternativeSlugs: AlternativeSlugs,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("promoted_at")
    val promotedAt: String,

    val width: Long,
    val height: Long,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val description: String,

    @SerialName("alt_description")
    val altDescription: String? = null,

    val breadcrumbs: JsonArray,
    val urls: Urls,
    val links: WelcomeLinks,
    val likes: Long,

    @SerialName("liked_by_user")
    val likedByUser: Boolean,

    @SerialName("current_user_collections")
    val currentUserCollections: JsonArray,

    val sponsorship: JsonElement? = null,

    @SerialName("asset_type")
    val assetType: AssetType,

    val user: User
)


@Serializable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,

    @SerialName("small_s3")
    val smallS3: String
)

@Serializable
data class User(
    val id: String,

    @SerialName("updated_at")
    val updatedAt: String,

    val username: String,
    val name: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

    @SerialName("portfolio_url")
    val portfolioURL: String? = null,

    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,

    @SerialName("profile_image")
    val profileImage: ProfileImage,

    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("total_collections")
    val totalCollections: Long,

    @SerialName("total_likes")
    val totalLikes: Long,

    @SerialName("total_photos")
    val totalPhotos: Long,

    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Long,

    @SerialName("total_illustrations")
    val totalIllustrations: Long,

    @SerialName("total_promoted_illustrations")
    val totalPromotedIllustrations: Long,

    @SerialName("accepted_tos")
    val acceptedTos: Boolean,

    @SerialName("for_hire")
    val forHire: Boolean,

    val social: Social
)

@Serializable
data class UserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

@Serializable
data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

@Serializable
data class Social(
    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("portfolio_url")
    val portfolioURL: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

    @SerialName("paypal_email")
    val paypalEmail: JsonElement? = null
)

@Serializable
data class ResultLinks(
    val self: String,
    val html: String,
    val photos: String,
    val related: String
)

@Serializable
data class PreviewPhoto(
    val id: String,
    val slug: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("blur_hash")
    val blurHash: String,

    @SerialName("asset_type")
    val assetType: AssetType,

    val urls: Urls
)

@Serializable
data class Tag(
    val type: Type,
    val title: String
)

@Serializable
enum class Type(val value: String) {
    @SerialName("search")
    Search("search");
}


fun PhotoCollectionDto.toDomain(): PhotoCollection {
    return PhotoCollection(
        id = id,
        description = altDescription ?: "",
        tags = tags.map { it.title },
        profileImage = user.profileImage.medium,
        username = user.username,
        imageUrl = urls.regular,
        imageSize = ImageSize(width.toInt(), height.toInt()),
        bookmarked = false, // todo: check 해서 반환하기
    )
}
