package codility

import org.scalacheck.Gen
import scala.collection.mutable.ArraySeq
import scala.util.Random

object Generator {

  val positiveInt: Gen[Int] = Gen.choose[Int](1, 1000000)
  val nonPositiveInt: Gen[Int] = Gen.choose[Int](-1000000, 0)

  val nonPositiveArray: Gen[ArraySeq[Int]] =
    Gen.containerOf[ArraySeq, Int](nonPositiveInt)

  val sequentialPositiveArray: Gen[ArraySeq[Int]] =
    for {
      size <- Gen.choose[Int](0, 100000)
      array = if size > 0 then ArraySeq.range(1, size) else ArraySeq.empty[Int]
    } yield array

  val testData: Gen[(Int, Array[Int])] = {
    for {
      neg <- nonPositiveArray
      pos <- sequentialPositiveArray.map(Random.shuffle(_))
    } yield
      if pos.isEmpty then (1, Random.shuffle(neg).toArray)
      else (pos.head, Random.shuffle(neg ++ pos.tail).toArray)
  }
}
