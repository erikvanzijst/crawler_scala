package cx.prutser

class FuncTest(val factor: Int) {
  private def mutate(value: Int): Int = value * factor

  def mutateSeq(col: Seq[Int]): Seq[Int] = col.map(mutate)

}

object URLUtil {
  def urlFactory(scheme: String = "https", domain: String): (String, Map[String, String]) => String = {
    (path: String, params: Map[String, String]) => s"$scheme://$domain/$path?${(for ((k, v) <- params) yield s"$k=$v").mkString("&")}"
  }
}

object FuncTools {
  def mul(depth: Int = 10)(value: Int): Unit = for (i <- 1 to depth) println(f"$i%2d * $value = ${i * value}%3d")
}


object Main {
  def main(args: Array[String]): Unit = {
    val numbers = 1 to 10
    val multiplied = new FuncTest(3).mutateSeq(numbers)
    println(multiplied)

    val mkUrl = URLUtil.urlFactory("ftp", "example.net")
    println(mkUrl("some/path", Map("foo" -> "bar", "fu" -> "quux")))

    val table = FuncTools.mul(10) _
    table(5)
  }
}
