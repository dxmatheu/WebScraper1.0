package org.example
import com.google.gson.GsonBuilder
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val articleList = mutableListOf<Article>()
    val filterAndOrder = FilterAndOrder()
    val gson = GsonBuilder().setPrettyPrinting().create()
    val requestTime = LocalDateTime.now()
    skrape(HttpFetcher) {
        request {

            url = "https://news.ycombinator.com/"

        }
        response {
            htmlDocument {
                var articleCount = 0
                ".athing"{
//                    we extract all ".athing"'s, but we only need 30
                    findAll{
                        forEach{
                            val id = it.attribute("id")
                            val ranking = it.findFirst(".rank").text.removeSuffix(".").toInt()
                            val title = it.findFirst(".titleline a").text
                            val tempArticle = Article(id, ranking, title)
                            if (articleCount<30){
                                articleList.add(tempArticle)
                                articleCount++
                            }
                        }
                    }
                }

                articleList.forEach { article ->

                    article.points = try {
                        findFirst("#score_${article.id}").text.removeSuffix(" points").toInt()
                    } catch (e: Exception){
                        0
                    }
                    val comments = try {
                        findSecond("a[href=\"item?id=${article.id}\"]").text
                    } catch(e: Exception) {
                        "none"
                    }
                    if (comments.contains("comment"))
                        article.comments = when{
                            comments.endsWith(" comments") -> comments.removeSuffix(" comments").toInt()
                            comments.endsWith(" comment") -> comments.removeSuffix(" comment").toInt()
                            else -> 0
                        }
                }
            }
        }
        val outputDir = File("output")
        if (!outputDir.exists())
            outputDir.mkdirs()

        val formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmm")
        val timestamp = requestTime.format(formatter)

        val longTitlesByComments = filterAndOrder.moreThanFiveWordTitlesOrderByComments(articleList)
        val longTitlesByCommentsMetadata = ArticleListWithMetadata(
            timestamp = timestamp,
            filterApplied = "More than five words, ordered by comments",
            articleCount = longTitlesByComments.size,
            articles = longTitlesByComments
        )
        File(outputDir, "LongTitlesByComments_$timestamp.json").writeText(gson.toJson(longTitlesByCommentsMetadata))

        val shortTitlesByPoints = filterAndOrder.equalOrLessThanFiveWordTitlesOrderByPoints(articleList)
        val shortTitlesByPointsMetadata = ArticleListWithMetadata(
            timestamp = timestamp,
            filterApplied = "More than five words, ordered by comments",
            articleCount = shortTitlesByPoints.size,
            articles = shortTitlesByPoints
        )
        File(outputDir, "ShortTitlesByPoints_$timestamp.json").writeText(gson.toJson(shortTitlesByPointsMetadata))
    }
}