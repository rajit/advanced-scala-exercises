package co.qeek.advancedscala.exercises.monoid

object SuperAdderV1 {
  def add(items: List[Int]): Int = items.sum
}

object SuperAdderV2 {
  import cats.Monoid

  def add[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(Monoid[A].combine)
}