package cx.prutser



trait Iterator[T] {
  def hasNext: Boolean
  def next: T
}

trait ForeachIterator[T] {
  def foreach2(f: T => Unit): Unit
}

class Counter(end: Integer = null) extends Iterator[Int]
                                    with ForeachIterator[Int]
                                    with scala.collection.Iterator[Int] {

  private var current: Int = 0

  override def hasNext: Boolean = end == null || current < end

  override def next: Int = {
    current += 1
    current - 1
  }

  override def foreach2(f: Int => Unit): Unit = {
    while (hasNext) f(next)
  }
}
