# url-shorten

Shorten the long URL.

## Requirements:

* Database - PostgreSQL preferable. [DDL](url_shorten.sql)
* JRE 17 or above
* Host the application where the short URL domain is directs (OR) Nginx for reverse proxy


## Generate the short URL

1. Insert's the original URL to the database and gets the primary key
2. Generate short path/URL using primary key 
3. Store the short URL to database and return

## Redirect to Original URL

* Make sure the application is running on the short URL domain & nginx/any other proxy redirect to this application
* Short to primary key 
* Get original URL from the database
* Redirects to the original URL with HTTP status code 302


Input

```
{

  "originalUrl": "https://www.thirumal.com/la/a015a1fd-f649-4aa4-ae87-a7855af9083d/16663ad0-cbb8-46ed-ad0a-22958aef62d0",
  "shortUrlHostAndProtocol": "http://link.thirumal.com",
  "expireOn": "2024-02-15T15:44:45.320Z"
}

```


output:

```
{
    "shortenUrlId": 2,
    "originalUrl": "https://www.thirumal.com/la/a015a1fd-f649-4aa4-ae87-a7855af9083d/16663ad0-cbb8-46ed-ad0a-22958aef62d0",
    "shortUrlHostAndProtocol": "http://link.thirumal.com",
    "shortUrl": "http://link.thirumal.com/c93a",
    "expireOn": "2024-02-15T15:44:45.32"
}
```