package com.reach.kmp.feature.data.bingwallpaper.source

import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpapersModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

private const val BING_BASE_URL = "https://www.bing.com"
private const val BING_IMAGE_URL = "/HPImageArchive.aspx"

internal interface BingWallpaperApi {

    suspend fun getBingWallpapers(
        beforeDays: Int,
        count: Int,
    ): BingWallpapersModel
}

internal class DefaultBingWallpaperApi(
    private val httpClient: HttpClient,
) : BingWallpaperApi {

    override suspend fun getBingWallpapers(
        beforeDays: Int,
        count: Int,
    ): BingWallpapersModel {
        check(beforeDays > -1) { "Before days must be >= 0" }
        check(beforeDays < 8) { "Before days must be < 8" }
        check(count > 0) { "Image count must be > 0" }
        check(count < 8) { "Image count must be < 8" }

        val response: BingWallpapersModel = httpClient.get(BING_BASE_URL + BING_IMAGE_URL) {
            url {
                parameters.apply {
                    // response format json
                    append("format", "js")
                    // 1: ultra high definition resolution, 0: normal
                    append("uhd", "1")
                    // the number days previous to the present day
                    // with 0 meaning the present day, range 0 .. 7
                    append("idx", beforeDays.toString())
                    // the number of images, range 1 .. 8
                    append("n", count.toString())
                    // market code
//                    append("mkt", Locale.getDefault().toLanguageTag())
                }
            }
        }.body()

        if (response.images.isEmpty()) {
            throw RuntimeException("BingWallpaperApi return empty images")
        }

        val responseFormat = LocalDate.Format {
            year()
            monthNumber()
            dayOfMonth()
        }
        val targetFormat = LocalDate.Format {
            year()
            char('-')
            monthNumber()
            char('-')
            dayOfMonth()
        }
        return BingWallpapersModel(response.images.map {
            it.copy(
                imageUrl = BING_BASE_URL + it.imageUrl,
                startDate = targetFormat.format(responseFormat.parse(it.startDate)),
            )
        })
    }
}
