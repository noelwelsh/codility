package codility

object Solution3 extends App {
  def solution(a: Array[Int]): Int = {
    def findLowest(result: Int, numbers: List[Int]): Int =
      numbers match {
        case Nil => result
        case x :: xs =>
          if result == x then findLowest(result + 1, xs) else result
      }

    val clean: List[Int] = a.toList.filter(_ > 0).sorted

    findLowest(1, clean)
  }
}
