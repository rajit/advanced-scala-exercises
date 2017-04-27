import cats.syntax.functor._
import co.qeek.advancedscala.exercises.functor.BranchingOut._

object Run {
  val tree = branch(leaf(1), branch(leaf(2), branch(leaf(3), leaf(4))))
  tree.map(_.toString)
}