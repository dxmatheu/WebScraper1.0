import org.example.Article
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArticleTest {

    @Test
    fun testWordsInTitle() {
    val article1 = Article("id", 1, "This is - a self-explained example")
    val article2 = Article("id", 2, "FLUX1.1 [pro] – New SotA text-to-image model from Black Forest Labs")
        assertEquals(5, article1.wordsInTitle())
        assertEquals(10, article2.wordsInTitle())
    }
}