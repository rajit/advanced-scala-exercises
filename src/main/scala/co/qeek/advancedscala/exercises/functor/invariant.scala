package co.qeek.advancedscala.exercises.functor

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this
    new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))

      override def decode(value: String): Option[B] = self.decode(value).map(dec)
    }
  }
}

object Codec {
  implicit val intCodec = new Codec[Int] {
    import cats.syntax.either._
    override def encode(value: Int): String = value.toString

    override def decode(value: String): Option[Int] =
      Either.catchOnly[NumberFormatException](value.toInt).toOption
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)
}

object Invariant {
  case class Box[A](value: A)

  def run: Unit = {
    implicit val doubleCodec = Codec.intCodec.imap[Double](_.toDouble, _.toInt)
    implicit def boxCodec[A: Codec] =
      implicitly[Codec[A]].imap[Box[A]](Box.apply, _.value)

    println(Codec.encode(Box(1)))
    println(Codec.decode[Box[Double]]("1"))
    println(Codec.encode(Box(1.0)))
  }
}
