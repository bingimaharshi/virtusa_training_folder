# Movie Recommendation & Rating Analysis System

A MySQL database that models a streaming platform's core recommendation engine with user ratings, watch history, and genre analytics.

## Database Schema

| Table | Key Columns |
|-------|-------------|
| Users | user_id, name, age, city |
| Movies | movie_id, title, genre, release_year, language |
| Ratings | user_id, movie_id, rating (1.0–5.0), rated_on |
| Watch_History | user_id, movie_id, watch_date, completed |

## Queries Included

1. **Top Rated Movies** - Average rating with minimum review threshold
2. **Most Popular Genres** - By watch count and unique viewers
3. **Collaborative Filtering** - "Users like you also watched..." recommendations
4. **Most Active Users** - Watch count, ratings given, average score
5. **Trending Movies** - Most-watched in last 90 days
6. **Movies Never Watched** - Content gap analysis
7. **City-wise Viewing Patterns** - Geographic taste profiling
8. **Completion Rate Analysis** - Which movies users stop watching

## Tools Used

- **MySQL** - Relational database
- **UNIQUE constraint** - Prevents duplicate ratings
- **CHECK constraint** - Ensures rating range 1.0–5.0
- **DATE_SUB / CURDATE()** - Time-window filtering
- **GROUP_CONCAT** - Genre aggregation per city
- **Self-join on Watch_History** - Collaborative filtering logic
- **LEFT JOIN + IS NULL** - Finding unwatched content

## Setup

```sql
mysql -u root -p < movie_recommendation.sql
```
