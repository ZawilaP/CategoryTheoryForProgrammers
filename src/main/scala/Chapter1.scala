

object Chapter1 extends App {
  /**
   * 1) Implement, as best as you can, the identity function in your favorite language (or the second favorite, if your favorite language
   * happens to be Haskell).
   */
  def identityFunction[T1]: T1 => T1 = T1 => T1

  /**
   * 2) Implement the composition function in your favorite language. It
   * takes two functions as arguments and returns a function that is
   * their composition.
   */
  def composition[T1, T2, T3](function1: T1 => T2, function2: T2 => T3): T1 => T3 = function2 compose function1

  /**
   * 3) Write a program that tries to test that your composition function
   * respects identity.
   */
  def testCompositionAndIdentity(): Unit = {
    def intToString: Int => String = Int => Int.toString

    def stringToString: String => String = String => String + "_longer"

    val firstComposition = composition[Int, Int, Int](identityFunction, identityFunction)

    println(s"First composition doesn't change int value: ${firstComposition(1) == 1}")

    val secondComposition = composition[Int, Int, String](identityFunction, intToString)
    val thirdComposition = composition[Int, String, String](intToString, identityFunction)

    val secondAndThirdCompositionsEquality = secondComposition(1) == thirdComposition(1)

    println(s"Second and third compositions give same resuls: $secondAndThirdCompositionsEquality")

    val fourthComposition = composition[String, String, String](identityFunction, stringToString)
    val fifthComposition = composition[String, String, String](stringToString, identityFunction)
    val fourthAndFifthCompositionEquality = fourthComposition("something") == fifthComposition("something")

    println(s"Fourth and fifth compositions give same resuls: $fourthAndFifthCompositionEquality")
  }

  testCompositionAndIdentity()

  /**
   * 4) Is the world-wide web a category in any sense? Are links Morphisms?
   *
   * Yes it is, if we consider objects to be webpages, and arrows as a way to get from one webpage to another clicking links
   */

  /**
   * 5) Is Facebook a category, with people as objects and friendships as morphisms?
   *
   * No, A being friends with B and B being friends with C, doesn't impose A being friends with C.
   */

  /**
   * 6) When is a directed graph a category?
   *
   * Only when every node has an egde that points back to the said node, and for every pair of nodes A, B connected by a path
   * from A to B, exists an edge connecting A and B.
   */

}