package codility

object Solution2 extends Solution {

  def solution(a: Array[Int]): Int = {
    def findLowest(numbers: List[Int]): Int =
      numbers match {
        case x :: Nil => x + 1
        case x :: xs  => if ((xs.head - x) > 1) x + 1 else findLowest(xs)
      }

    val clean: List[Int] = a.toList.filter(_ > 0).sorted

    if (clean.isEmpty) 1
    else if (clean.head != 1) 1
    else findLowest(clean)
  }
}
