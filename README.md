## Running

Crawls a series of web pages, following links in each page. Provide a base or
root URL prefix to restrict the crawler to wandering the entire internet.

For example, to crawl the entire Linux Documentation Project site:

```bash
$ sbt "run http://mirrors.kernel.org/LDP/ http://mirrors.kernel.org/LDP/guides.html"
```

Or using docker:

```bash
$ docker build -t crawler .
$ docker run --rm crawler http://mirrors.kernel.org/LDP/ http://mirrors.kernel.org/LDP/guides.html
```
