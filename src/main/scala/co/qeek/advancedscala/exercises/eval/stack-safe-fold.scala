package co.qeek.advancedscala.exercises.eval

import cats.Eval

object UnsafeFold {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def factorial(n: BigInt): BigInt =
    foldRight((BigInt(1) to n).toList, BigInt(1))((acc, n) => acc * n)
}

object SafeFold {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(foldRight(tail, acc)(fn).map(rest => fn(head, rest)))
      case Nil =>
        Eval.now(acc)
    }

  def factorial(n: BigInt): Eval[BigInt] =
    foldRight((BigInt(1) to n).toList, BigInt(1))((acc, n) => acc * n)
}
