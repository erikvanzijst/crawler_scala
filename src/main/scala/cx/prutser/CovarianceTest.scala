package cx.prutser

object CovarianceTest extends App {
  abstract class Buffer[+T] {
    val element: T
  }
  abstract class SeqBuffer[U, +T <: Seq[U]] extends Buffer[T] {
    def length: Int = element.length
  }

  def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer[Int, Seq[Int]] =
    new SeqBuffer[Int, List[Int]] {
      val element: List[Int] = List(e1, e2)
    }

  val buf = newIntSeqBuf(7, 8)
  println("length = " + buf.length)
  println("content = " + buf.element)


  trait Cloneable extends java.lang.Cloneable {
    override def clone(): Cloneable = {
      super.clone().asInstanceOf[Cloneable]
    }
  }

  trait Resetable {
    def reset(): Unit
  }

  def cloneAndReset(obj: Cloneable with Resetable): Cloneable= {
    val cloned = obj.clone()
    obj.reset()
    cloned
  }

  class ResettingClone extends Cloneable with Resetable {
    override def reset(): Unit = {}
  }

  val ret = cloneAndReset(new ResettingClone)


  trait User {
    def username(): String
  }

  trait Tweeter {
    this: User =>  // reassign this
    def tweet(tweetText: String): Unit = println(s"${this.username()}: $tweetText")
  }

  class VerifiedTweeter(val username_ : String) extends Tweeter with User {  // We mixin User because Tweeter required it
    def username() = s"real $username_"
  }

  val realBeyoncé = new VerifiedTweeter("Beyoncé")
  realBeyoncé.tweet("Just spilled my glass of lemonade")
}
