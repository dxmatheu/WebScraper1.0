package org.example
import it.skrape.core.*
import it.skrape.fetcher.*


class Article(var id: String, var ranking: Int, var title: String, var points: Int = 0, var comments: Int = 0){
    override fun toString(): String {
        return "$ranking: $title, points: $points, comments: $comments"
    }
}
fun main() {
    val articleList = mutableListOf<Article>()
    skrape(HttpFetcher) {
        request {

            url = "https://news.ycombinator.com/"

        }
        response {
            htmlDocument {
                var articleCount = 0
                ".athing"{
                    findAll{
                        forEach{
                            val id = it.attribute("id")
                            val number = it.findFirst(".rank").text.removeSuffix(".").toInt()
                            val title = it.findFirst(".titleline a").text
                            val tempArticle = Article(id, number, title)
                            if (articleCount<30){
                                articleList.add(tempArticle)
                                articleCount++
                            }
                        }
                    }
                }

                articleList.forEach { article ->
                    val score = try {
                        findFirst("#score_${article.id}").text.removeSuffix(" points").toInt()
                    } catch (e: Exception){
                        0
                    }
                    article.points = score
                    val comments = findSecond("a[href=\"item?id=${article.id}\"]").text
                    if (comments.contains("comment"))
                        article.comments = when{
                            comments.endsWith(" comments") -> comments.removeSuffix(" comments").toInt()
                            comments.endsWith(" comment") -> comments.removeSuffix(" comment").toInt()
                            else -> 0
                        }
                }
            }
        }
        articleList.forEach{
            println(it)
        }
    }
}