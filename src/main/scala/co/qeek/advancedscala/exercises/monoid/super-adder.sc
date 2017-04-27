import cats.Monoid
import co.qeek.advancedscala.exercises.monoid.{SuperAdderV1, SuperAdderV2}

object Run {
  SuperAdderV1.add(1 :: 2 :: 3 :: Nil)

  import cats.instances.int._
  SuperAdderV2.add(1 :: 2 :: 3 :: Nil)

  import cats.syntax.option._
  import cats.instances.option._
  SuperAdderV2.add(1.some :: 2.some :: 3.some :: Nil)
  SuperAdderV2.add(1.some :: 2.some :: None :: Nil)

  case class Order(totalCost: Double, quantity: Double)

  object Order {
    import cats.instances.double._
    import cats.syntax.semigroup._

    implicit val orderMonoid = new Monoid[Order] {
      override def empty: Order = Order(0, 0)

      override def combine(x: Order, y: Order): Order =
        Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)
    }
  }

  SuperAdderV2.add(List(Order(1, 2), Order(2, 3)))
}
