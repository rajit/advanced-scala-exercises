package co.qeek.advancedscala.exercises.monoid

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

object BooleanMonoid {
  class AndMonoid extends Monoid[Boolean] {
    // true && empty == true
    // false && empty == false
    // empty && true == true
    // empty && false == false
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  class OrMonoid extends Monoid[Boolean] {
    // true || empty == true
    // false || empty == false
    // empty || true == true
    // empty || false == false
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  class XorMonoid extends Monoid[Boolean] {
    // true ^^ empty == true
    // false ^^ empty == false
    // empty ^^ true == true
    // empty ^^ false == false
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }
}