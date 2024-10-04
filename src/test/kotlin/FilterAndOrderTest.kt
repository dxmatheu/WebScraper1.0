import org.example.Article
import org.example.FilterAndOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FilterAndOrderTest {
    private lateinit var articleList: MutableList<Article>
    private lateinit var filterAndOrder: FilterAndOrder
    @BeforeEach
    fun setUp() {
        articleList = mutableListOf(
            Article("1", 1, "This is a six word title", 100, 10),
            Article("2", 2, "Short title", 200, 5),
            Article("3", 3, "This is a title with more than five words", 50, 15),
            Article("4", 4, "Another short title", 150, 7)
        )

        filterAndOrder = FilterAndOrder()
    }
    @Test
    fun  `test filtering long title ordered by comments`() {
        val filteredLongTitlesByCommentsList = filterAndOrder.moreThanFiveWordTitlesOrderByComments(articleList)

        assertEquals(2, filteredLongTitlesByCommentsList.size)
        assertEquals("This is a six word title", filteredLongTitlesByCommentsList[0].title)
        assertEquals("This is a title with more than five words", filteredLongTitlesByCommentsList[1].title)
    }

    @Test
    fun `test filtering short titles ordered by points`() {
        val filteredShortTitlesByPointsList = filterAndOrder.equalOrLessThanFiveWordTitlesOrderByPoints(articleList)

        // Assert the list is filtered and sorted by points
        assertEquals(2, filteredShortTitlesByPointsList.size)
        assertEquals("Another short title", filteredShortTitlesByPointsList[0].title)
        assertEquals("Short title", filteredShortTitlesByPointsList[1].title)
    }
}