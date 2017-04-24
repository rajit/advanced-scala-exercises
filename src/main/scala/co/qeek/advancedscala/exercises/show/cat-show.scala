package co.qeek.advancedscala.exercises.show

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import co.qeek.advancedscala.exercises.Cat

object CatInstances {
  implicit val catShow: Show[Cat] = Show.show { c =>
    val name = c.name.show
    val age = c.age.show
    val color = c.color.show
    s"$name is a $age year-old $color cat."
  }
}

object ShowCat {
  import CatInstances._

  def doIt: Unit = {
    val cat = Cat("Hermione", 12, "Auburn")
    println(Show.apply[Cat].show(cat))
  }

  def doItBetter: Unit = {
    val cat = Cat("Ron", 7, "Red")
    println(cat.show)
  }
}