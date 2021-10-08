package codility

object Solution1 extends Solution {

  def solution(a: Array[Int]): Int = {

    def tolis(b: List[Int]): Int = b match {
      case x :: Nil => x + 1
      case x :: hs  => if ((hs.head - x) > 1) x + 1 else tolis(hs)

    }
    var b: List[Int] = a.toList.filter(_ > 0).sorted
    //b.sorted
    if (b.isEmpty) 1
    else if (b.head != 1) 1
    else tolis(b)

  }
}
