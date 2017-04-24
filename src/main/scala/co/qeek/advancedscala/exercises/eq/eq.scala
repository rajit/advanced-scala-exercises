package co.qeek.advancedscala.exercises.eq

import cats.Eq
import cats.instances.string._
import cats.instances.int._
import cats.instances.option._
import cats.instances.boolean._
import cats.syntax.eq._
import cats.syntax.option._
import cats.syntax.show._
import co.qeek.advancedscala.exercises.Cat

object CatInstances {
  implicit val catEq = Eq.instance[Cat] { (c1, c2) =>
    c1.name === c2.name && c1.age === c2.age && c1.color === c2.color
  }
}

object EqCat {
  def doIt = {
    import CatInstances._

    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")

    val optionCat1 = cat1.some
    val optionCat2 = Option.empty[Cat]
    println((optionCat1 === optionCat2).show)
  }
}