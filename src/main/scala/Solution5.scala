package codility

import java.util.Arrays
import java.util.BitSet

object Solution5 extends Solution {
  def solution(a: Array[Int]): Int = {
    def populateBitSet(
        bitSet: BitSet,
        idx: Int,
        numbers: Array[Int]
    ): BitSet = {
      if idx == numbers.length then bitSet
      else {
        val elt = numbers(idx)
        if elt < 1 then populateBitSet(bitSet, idx + 1, numbers)
        else {
          bitSet.set(elt)
          populateBitSet(bitSet, idx + 1, numbers)
        }
      }
    }

    val bitSet = populateBitSet(BitSet(1000000), 0, a)
    val result = bitSet.nextClearBit(1)
    result
  }
}
