package co.qeek.advancedscala.exercises.functor

trait Printable[A] {
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = {
    val fmt = format _
    (value: B) => fmt(func(value))
  }
}

object Printable {
  val stringPrintable = new Printable[String] {
    override def format(thing: String): String = thing
  }

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  def run1: Unit = {
    implicit val sp = stringPrintable
    println(format("1234"))
  }

  def run2: Unit = {
    implicit val logger = stringPrintable.contramap((a: String) => a + "!")
    println(format("1234"))
  }

  implicit val stringyPrintable =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  final case class Box[A](value: A)

  implicit def boxPrintable[A](implicit p: Printable[A]) =
    p.contramap((b: Box[A]) => b.value)

  def run3: Unit = {
    println(format("hello"))
    println(format(true))

    println(format(Box("hello")))
    println(format(Box(false)))
  }
}
