package org.example

class Article(var id: String, var ranking: Int, var title: String, var points: Int = 0, var comments: Int = 0){
    override fun toString(): String {
        return "$ranking: $title, points: $points, comments: $comments"
    }
    fun wordsInTitle(): Int{
        val onlyWordTitle = this.title.replace(("[^\\w\\d ]").toRegex(), "").replace("  ", " ").split(" ").size
        return onlyWordTitle
    }
}