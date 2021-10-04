package codility

import munit.*
import org.scalacheck.Prop.*

class Solution2Spec extends ScalaCheckSuite {
  property("find lowest missing positive") {
    forAll(Generator.testData) { (answer: Int, data: Array[Int]) =>
      Solution1.solution(data) == answer
    }
  }
}
