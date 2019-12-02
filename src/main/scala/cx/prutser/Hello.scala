package cx.prutser


object Hello {

  def toInt(value: String): Option[Int] = {
    try {
      Some(Integer.parseInt(value))
    } catch {
      case _: NumberFormatException => None
    }
  }

  def main(args: Array[String]): Unit = {
    println("cx.prutser.Hello World")
    println(Fibonacci.expand(7) + " -- " + Fibonacci.fib(7))

    val p: Point = new Point()
    p.x = 2
    p.y = -3
    println(p)

    var c = new Counter(9)
    while (c.hasNext)
      println(c.next)

    c = new Counter
    println(c.next)
    println(c.next)
    println(c.next)

    val vals = Seq("1", "2", "3", "None", "5")
    println(vals.flatMap(toInt).sum)

    val names = Seq("Chris", "Maurice", "Fred", "☺")
    for (n <- names) {
      println(n)
    }

    val r = 1 to 9
    for (i <- r) println(s"$i squared = ${i * i}")

    val gen = for (i <- c)
      yield {
        println("generating value")
        i
      }

    println(gen.next)
    println("Other work")
    println(gen.next)

    println("Counter.foreach:")
    new Counter(6).foreach(println)

    val ns = for (n <- names if n != "Fred") yield {
      println("Capitalizing next name")
      n.capitalize
    }
    println(ns)

    names.foreach({
        case "Fred" => true
        case _ =>
      })

    (for {
      i <- 0 to 5
      j <- 0 to 5
    } yield s"$i * $j = ${i * j}") foreach println

    println(names.length)
    var names2 = for ((e, i) <- names.view.zip(LazyList.from(1))) yield s"$i: ${e.capitalize}"
    println(names2.mkString("\n"))

    val phones = Map("Erik" -> "650-522-0160", "Joe" -> "408-342-0143", "Fred" -> null)
    println("+-----------+----------------+")
    phones.toSeq.sorted.foreach{
      case (name: String, nr: String) => println(f"| ${name}%-10s|$nr%15s |")
      case _ => // filter out non-string values
    }
    println("+-----------+----------------+")

    phones.toSeq.sortWith((item1, item2) => item1._1.compareTo(item2._1) < 0).foreach{ (item) => println(f"| ${item._1}%-10s|${item._2}%15s |") }
    println("+-----------+----------------+")

    println(phones.keys)
    println("Exit")
  }


  override def toString: String = "object<Hello>"
}
