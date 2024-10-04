package org.example

class FilterAndOrder(){
    fun moreThanFiveWordTitlesOrderByComments(articleList: MutableList<Article>): MutableList<Article> {
        val filteredArticleList = mutableListOf<Article>()

        articleList.forEach {
            if (it.wordsInTitle() > 5)
                filteredArticleList.add(it)
        }
        filteredArticleList.sortBy { it.comments }
        return filteredArticleList
    }

    fun equalOrLessThanFiveWordTitlesOrderByPoints(articleList: MutableList<Article>): MutableList<Article> {
        val filteredArticleList = mutableListOf<Article>()

        articleList.forEach {
            if (it.wordsInTitle() <= 5)
                filteredArticleList.add(it)
        }
        filteredArticleList.sortBy { it.points }
        return filteredArticleList
    }
}