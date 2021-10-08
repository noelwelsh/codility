package codility

import munit.*
import org.scalacheck.Prop.*

class SolutionSpec extends ScalaCheckSuite {
  def check[T](name: String, solution: Solution)(implicit
      loc: munit.Location
  ): Unit = {
    test(name ++ " pbt") {
      property("find lowest missing positive") {
        forAll(Generator.testData) { (answer: Int, data: Array[Int]) =>
          Solution1.solution(data) == answer
        }
      }
    }

    test(name ++ " empty data") {
      assertEquals(solution.solution(Array()), 1)
    }

    test(name ++ " negative data") {
      assertEquals(solution.solution(Array(-3, -2, -1)), 1)
    }

    test(name ++ " zero data") {
      assertEquals(solution.solution(Array(0)), 1)
    }

    test(name ++ " positive data") {
      assertEquals(solution.solution(Array(3, 2, 1)), 4)
    }
  }

  check("Solution 1", Solution1)
  check("Solution 2", Solution2)
  check("Solution 3", Solution3)
  check("Solution 4", Solution4)
  check("Solution 5", Solution5)
}
