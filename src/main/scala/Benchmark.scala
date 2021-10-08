package codility

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.*

class CodilityBenchmark {
  import CodilityBenchmark.BenchmarkState

  @Benchmark
  def benchSolution1(state: BenchmarkState): Unit =
    Solution1.solution(state.data)

  @Benchmark
  def benchSolution2(state: BenchmarkState): Unit =
    Solution2.solution(state.data)

  @Benchmark
  def benchSolution3(state: BenchmarkState): Unit =
    Solution3.solution(state.data)

  @Benchmark
  def benchSolution4(state: BenchmarkState): Unit =
    Solution4.solution(state.data)

  @Benchmark
  def benchSolution5(state: BenchmarkState): Unit =
    Solution5.solution(state.data)
}
object CodilityBenchmark {
  @State(Scope.Benchmark)
  class BenchmarkState {
    val data: Array[Int] = Array.range(-50000, 50000)
  }
}
