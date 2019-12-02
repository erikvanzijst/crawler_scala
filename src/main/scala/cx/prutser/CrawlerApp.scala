package cx.prutser

import scala.jdk.CollectionConverters._
import scala.collection.mutable.{Set => MSet}
import scala.collection.mutable.{Map => MMap}
import scala.util.{Failure, Success, Try}
import java.net.URL

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object CrawlerApp extends App {
  println(args.mkString(" "))
  new Crawler(new URL(args.head), for (u <- args.tail) yield new URL(u)).crawl().foreach {
    case (url, len) => println(s"Length of $url : $len\n")
  }
}


class Crawler(val root: URL, val urls: Seq[URL]) {
  import Crawler._

  def crawl(): Map[URL, Int] = {
    var seen: MSet[URL] = MSet()
    var todo = urls.toList
    var docs: MMap[URL, Int] = MMap()

    while (todo != Nil) {
      val url = todo.head
      todo = todo.tail
      if (!seen.contains(url)) {
        seen += url
        Try(download(url)) match {
          case Success(document) =>
            todo :::= findURLs(url, document).filter(_.toExternalForm.startsWith(root.toExternalForm))
            docs += (url -> document.html.length)
          case Failure(exception) => println(s"Download failed: $exception")
        }
      } else {
        println(s"Skipping duplicate URL $url")
      }
    }
    docs.toMap
  }
}

object Crawler {
  def download(url: URL): Document = {
    println(s"Downloading $url...")
    Jsoup.connect(url.toExternalForm).execute().parse()
  }

  def findURLs(base: URL, doc: Document): List[URL] = {
    doc.select("a[href]").asScala  // find all anchor tags
      .map(_ attr "abs:href")                 // resolve absolute URL from href attribute
      .map(_.split("#").head)         // drop fragment
      .map(new URL(_))                        // convert to Java URL
      .foldRight[List[URL]](List.empty)(_ :: _)
  }
}