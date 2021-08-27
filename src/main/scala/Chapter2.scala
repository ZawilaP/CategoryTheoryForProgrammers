import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

object Chapter2 extends App {

  /**
   * Define a higher-order function (or a function object) memoize in
   * your favorite language. This function takes a pure function f as
   * an argument and returns a function that behaves almost exactly
   * like f, except that it only calls the original function once for every
   * argument, stores the result internally, and subsequently returns
   * this stored result every time it’s called with the same argument.
   * You can tell the memoized function from the original by watching its performance. For instance, try to memoize a function that
   * takes a long time to evaluate. You’ll have to wait for the result
   * the first time you call it, but on subsequent calls, with the same
   * argument, you should get the result immediately.
   */
  def memoize[T1, T2](pureFunction: T1 => T2): T1 => T2 = new mutable.HashMap[T1, T2]() {
    override def apply(key: T1): T2 = getOrElseUpdate(key, pureFunction(key))
  }

  /**
   * Tail recursive factorial implementation
   *
   * @param input : BigInt
   * @return "BigInt" containing Factorial of the input
   */
  def tailRecursiveFactorial(input: BigInt): BigInt = {
    @tailrec
    def accumulator(x: BigInt, acc: BigInt = 1): BigInt = {
      if (x <= 1) acc
      else accumulator(x - 1, x * acc)
    }

    accumulator(input)
  }

  /**
   * Function to compare execution times for memoized function
   *
   * @param function  function to memoize
   * @param parameter parameter to initialize memoized function
   * @tparam T1 input type of function
   * @tparam T2 output type of function
   */
  def compareExecutionTimeForMemoize[T1, T2](function: T1 => T2, parameter: T1): Unit = {
    val memoizedFunction: T1 => T2 = memoize(function)

    val t1: Long = System.nanoTime
    val firstMemoized = memoizedFunction(parameter)
    val duration: Double = (System.nanoTime - t1) / 1e9d
    println(s"Showing firstMemoized: $firstMemoized")
    println(s"Duration of first call was: $duration")

    val t2: Long = System.nanoTime()
    val secondMemoized = memoizedFunction(parameter)
    val duration2: Double = (System.nanoTime - t2) / 1e9d
    println(s"Showing secondMemoized: $secondMemoized")
    println(s"Duration of second call was: $duration2")
    println(s"Second call took: ${duration / duration2} times less time")
  }

  compareExecutionTimeForMemoize(tailRecursiveFactorial, BigInt(40))

  val t1: Long = System.nanoTime()
  println(memoize(tailRecursiveFactorial)(50))
  val duration: Double = (System.nanoTime - t1) / 1e9d
  println(s"Duration of new call of factorial was: $duration")


  /**
   * Most random number generators can be initialized with a seed.
   * Implement a function that takes a seed, calls the random number
   * generator with that seed, and returns the result. Memoize that
   * function. Does it work?
   *
   */
  def returnNextInt(seed: Int): Int = {
    Random.setSeed(seed)
    Random.nextInt()
  }

  compareExecutionTimeForMemoize(returnNextInt, 40)

  /**
   * Which of these C++ functions are pure? Try to memoize them
   * and observe what happens when you call them multiple times:
   * memoized and not.
   * (a) The factorial function from the example in the text.
   * (b) std::getchar()
   * (c) bool f() {
   * std::cout << "Hello!" << std::endl;
   * return true;
   * }
   * (d) int f(int x) {
   * static int y = 0;
   * y += x;
   * return y;
   * }
   *
   */

  /**
   * a) Factorial, done above, obviously a pure function
   */

  /**
   * b) std::getchar(), not a pure function, as the call itself is dependant on the cmd input
   */

  /**
   * c) bool f() {
   * std::cout << "Hello!" << std::endl;
   * return true;
   * }
   *
   * Below isn't a pure function as it has a side effect of printing out to terminal
   */
  def printHelloReturnTrue(something: String): Boolean = {
    println("Hello!")
    true
  }

  compareExecutionTimeForMemoize(printHelloReturnTrue, "Something")

  // Second call doesn't produce the side effects of printing out hello


  /**
   * (d) int f(int x) {
   * static int y = 0;
   * y += x;
   * return y;
   * }
   *
   * Although I suffer using var, we have to here.
   */

  def add25(x: Int): Int = {
    var y = 0
    y += x
    y
  }

  compareExecutionTimeForMemoize(add25, 129048329)

  // Second call takes about 1.5 times less to compute and print out

  /**
   * How many different functions are there from Bool to Bool? Can
   * you implement them all?
   *
   * As we need to find all functions from bool set to bool set, we need to look into all combinations:
   *
   * f => f, t => f
   *
   * f => f, t => t
   *
   * f => t, t => f
   *
   * f => t, t => t
   */

  def alwaysFalse(bool: Boolean): Boolean = false
  def booleanIdentity(bool: Boolean): Boolean = bool
  def booleanNegation(bool: Boolean): Boolean = !bool
  def alwaysTrue(bool: Boolean): Boolean = true
}
