package co.qeek.advancedscala.exercises.functor

import cats.Functor

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object BranchingOut {
  implicit def treeFunctor = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: (A) => B): Tree[B] =
      fa match {
        case Branch(left, right) =>
          Branch(map(left)(f), map(right)(f))
        case Leaf(value) =>
          Leaf(f(value))
      }
  }

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}
