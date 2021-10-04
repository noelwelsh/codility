# Incrementally Improving Code: A Case Study

I'm mentoring a new developer who is applying for their first job. They were asked to complete some tasks on [Codility](https://www.codility.com/) as the first step of the interview process. To get used to the platform they did the first example task, and I advised them on some changes. Since this is the example task, not a task used for real interviews, and I think my advice might be useful to others, here is their starting point and my suggested changes.


## The Problem

First, the Codility problem:


Write a function:

``` scala
object Solution {
  def solution(a: Array[Int]): Int
}
```

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5. Given A = [1, 2, 3], the function should return 4. Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

- N is an integer within the range [1..100,000];
- each element of array A is an integer within the range [−1,000,000..1,000,000].


## Initial Solution

Here's the student's initial solution.

```scala
object Solution1 extends App {

  def solution(a: Array[Int]): Int = {

    def tolis(b: List[Int]): Int = b match {
      case x :: Nil => x + 1
      case x :: hs => if ((hs.head - x) > 1) x + 1 else tolis(hs)

    }
    var b: List[Int] = a.toList.filter(_ > 0).sorted
    //b.sorted
    if (b.isEmpty) 1
    else if (b.head != 1) 1
    else tolis(b)


  }
}
```

## Code Cleanup

There are several issues with the initial solution. Let's start with the easiest ones:

- bad naming (what does `tolis` mean?)
- `var` is not necessary
- messy formatting

These are fairly small points but they are easy for an interviewer to complain about. We don't want to give the company an easy reason to reject us!


``` scala
object Solution2 extends App {

  def solution(a: Array[Int]): Int = {
    def findLowest(numbers: List[Int]): Int = 
      numbers match {
        case x :: Nil => x + 1
        case x :: xs => if ((xs.head - x) > 1) x + 1 else findLowest(xs)
      }

    val clean: List[Int] = a.toList.filter(_ > 0).sorted

    if (clean.isEmpty) 1
    else if (clean.head != 1) 1
    else findLowest(clean)
  }
}
```


## Partial and Total Functions

Let's now move on to deeper issues. I don't like the implementation of `findLowest`. There is some input for which it will crash---namely the empty list. In FP jargon we'd say it is a *partial function*, not a *total function*. The emtpy list case checked before it's called, but it easy for future modifications to break this.  We could use, say, Cats' `NonEmptyList` type to express that this function only works with non-empty lists, but it's not really appropriate to add a dependency in this context. We can, instead, rewrite `findLowest` to be a total function. Before we do that, though, I want to create a test suite so we can be sure we don't break anything.

To test this function we could create a few hand-crafted cases, the programmer equivalent of banging together sticks to make fire, or we could generate test cases from a specification. A fairly simple way to generate test cases is:

- create a many negative number as we like
- create a sequence of positive numbers, and remove one of the numbers
- join the two sets of numbers and shuffle

With this construction we know the result should be the number we removed.

Once we've setup the test suite we can proceed. I used [MUnit](https://scalameta.org/munit/) and its ScalaCheck integration to do the above.

We can make `findLowest` a total function by adding an extra parameter, which is the current guess for the lowest number. With this we can write `findLowest` as a standard structural recursion and the compiler will stop complaining about our incomplete match. Here's the code (written with Scala 3 syntax).

``` scala
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
```


## Performance

To be continued...
