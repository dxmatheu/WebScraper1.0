package org.example

data class ArticleListWithMetadata(
    val timestamp: String,
    val filterApplied: String,
    val articleCount: Int,
    val articles: List<Article>
)