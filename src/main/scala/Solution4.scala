package codility

import java.util.Arrays

object Solution4 extends Solution {
  def solution(a: Array[Int]): Int = {
    def findLowest(result: Int, idx: Int, numbers: Array[Int]): Int = {
      if idx == numbers.length then result
      else if result == numbers(idx) then
        findLowest(result + 1, idx + 1, numbers)
      else result
    }

    val clean: Array[Int] = a.filter(_ > 0)

    Arrays.sort(clean)
    findLowest(1, 0, clean)
  }
}
