package org.example
import it.skrape.core.*
import it.skrape.fetcher.*


class Article(var number: Int, var title: String, var points: Int = 0, var comments: Int = 0){
    override fun toString(): String {
        return "$number: $title, points: $points, comments: $comments"
    }
}
fun main() {
    var articleList = mutableListOf<Article>()
    skrape(HttpFetcher) {
        request {

            url = "https://news.ycombinator.com/"

        }
        response {
            htmlDocument {
                ".athing"{
                    findAll{
                        forEach{
                            val number = it.findFirst(".rank").text.removeSuffix(".").toInt()
                            val title = it.findFirst(".titleline a").text
                            val tempArticle = Article(number, title)
                            articleList.add(tempArticle)
                        }
                    }
                }
                ".subtext"{
                    findAll{
                        forEach{
                            val points = try {
                                it.findFirst(".score").text.removeSuffix(" points").toInt()
                            } catch (e: Exception){
                                0
                            }
                            val comments = it.findLast("a").text
                            println(comments)
                        }
                    }
                }
            }
        }
        articleList.forEach{
//            println(it)
        }
    }
}