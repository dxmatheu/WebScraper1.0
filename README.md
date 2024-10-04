# Web Scraper

This project implements a web scraper in Kotlin that extracts the first 30 entries from [Hacker News](https://news.ycombinator.com/), specifically the ranking, title, points, and number of comments for each entry. It also includes filtering operations and automated tests for validation. This was built using IntelliJ (I love JetBrains' IDEs) so for consistancy I recommend also running it with that.

## Features

- **Web Scraping**: Extracts the ranking, title, points, and number of comments from the first 30 entries on Hacker News.
- **Filtering Operations**:
  - Titles with more than five words, ordered by number of comments.
  - Titles with five or fewer words, ordered by points.
- **Automated Tests**: Verifies the filtering and sorting logic using JUnit.
- **Output**: The scraped data is converted to JSON and saved to a file including metadata such as:
  - Request timestamp.
  - Filter applied.
  - Number of articles.

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/dxmatheu/WebScraper1.0.git
   cd WebScraper1.0
   ```
2. Build and Run the project:
```bash
   ./gradlew build
   ./gradlew run
   ```

## Testing
To run the automated tests:
```bash
   ./gradlew test
   ```
