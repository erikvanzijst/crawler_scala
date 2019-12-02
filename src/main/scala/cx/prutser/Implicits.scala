package cx.prutser

abstract class Monoid[A] {
  def add(x: A, y: A): A
  def unit: A
}

object Implicits extends App {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def add(x: String, y: String): String = x concat y
    def unit: String = ""
  }

  implicit val Foo: Monoid[Int] = new Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y
    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))

  println(sum(List(1, 2, 3)))       // uses intMonoid implicitly
  println(sum(List("a", "b", "c"))) // uses stringMonoid implicitly
//  println(sum(Range(0, 10000).toList))  // stack overflow


  def fac(n: Int): Int = if (n == 0) 1 else n * fac(n - 1)

  def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)

  println(getIndex("abc", 'a'))



  def whileLoop(condition: => Boolean, body: => Unit): Unit =
    while (condition) { body }
//    if (condition) {
//      body
//      whileLoop(condition)(body)
//    }

  var i = 2

  whileLoop (i > 0, {
    println("iteration: " + i)
    i -= 1
  })


  class Foo(var x: Int) {
    def apply(x: Int): Foo = new Foo(x)

    def update(x: Int): Unit = {
      this.x = x
    }

    override def toString: String = s"Foo<$x>"
  }

  val f = new Foo(3)
  println(f(5))
  f() = 4
  println(f)
  1.to(25)
}
