package co.qeek.advancedscala.exercises.printablelibrary

import co.qeek.advancedscala.exercises.Cat

trait Printable[A] {
  def format(thing: A): String
}

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    override def format(thing: String): String = thing
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(thing: Int): String = thing.toString
  }
}

object Printable {
  def format[A](thing: A)(implicit printer: Printable[A]): String = printer.format(thing)

  def print[A](thing: A)(implicit printer: Printable[A]): Unit = println(format(thing))
}

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit printer: Printable[A]): String = Printable.format(value)
    def print(implicit printer: Printable[A]): Unit = Printable.print(value)
  }
}

object CatInstances {
  implicit val catPrintable = new Printable[Cat] {
    override def format(thing: Cat): String =
      s"${thing.name} is a ${thing.age} year-old ${thing.color} cat."
  }
}

object PrintableCat {
  import CatInstances._

  def doIt: Unit = {
    val cat = Cat("Hermione", 12, "Auburn")
    Printable.print(cat)
  }

  def doItBetter: Unit = {
    import PrintableSyntax._
    val cat = Cat("Ron", 7, "Red")
    cat.print
  }
}
