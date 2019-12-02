package cx.prutser

object Fibonacci {
  def expand(value: Int): List[Int] = {
    if (value == 0)
      return List(0)
    if (value == 1)
      1 :: (0 :: Nil)
    else {
      val tail: List[Int] = expand(value-1)
      (tail.head + tail(1)) +: tail
    }
  }

  def fib(n: Int): Int = expand(n).sum
}

class Point() {
  private var _x = 0
  private var _y = 0

  def x: Int = _x
  def x_= (x: Int): Unit = _x = x

  def y: Int = _y
  def y_= (y: Int): Unit = _y = y

  override def toString: String = {
    s"Point<${_x}, $y>"
  }
}
