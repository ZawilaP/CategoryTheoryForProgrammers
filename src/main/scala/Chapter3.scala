object Chapter3 extends App {
  /**
   * 1. Generate a free category from:
   * (a) A graph with one node and no edges
   * (b) A graph with one node and one (directed) edge (hint: this
   * edge can be composed with itself)
   * (c) A graph with two nodes and a single arrow between them
   * (d) A graph with a single node and 26 arrows marked with the
   * letters of the alphabet: a, b, c … z.
   * 2. What kind of order is this?
   * (a) A set of sets with the inclusion relation: 𝐴 is included in 𝐵
   * if every element of 𝐴 is also an element of 𝐵.
   * (b) C++ types with the following subtyping relation: T1 is a subtype of T2 if a pointer to T1 can be passed to a function that
   * expects a pointer to T2 without triggering a compilation error.
   * 3. Considering that Bool is a set of two values True and False, show
   * that it forms two (set-theoretical) monoids with respect to, respectively, operator && (AND) and || (OR).
   * 4. Represent the Bool monoid with the AND operator as a category:
   * List the morphisms and their rules of composition.
   * 5. Represent addition modulo 3 as a monoid category
   */

  /**
   * 1) Skipping this, as it is more of a intelectual exercise
   */

  /**
   * 2) a) This is a partial order, as two sets can have empty intersection.
   * 2) b) This is a partial order, as there are types that do not have subtypes.
   */

  /**
   * 3) This requires finding 0 element of the operators && and ||, and showing associativity.
   * a) Operator &&
   * Associativity: (Bool1 && Bool2) && Bool3 == Bool1 && (Bool2 && Bool3) holds for any Bool{i} for i = 1,2,3 \in {TRUE, FALSE}
   * And TRUE is neutral element, as TRUE && TRUE == TRUE and TRUE && FALSE == FALSE
   *
   * b) Operator ||
   * Associativity: (Bool1 || Bool2) || Bool3 == Bool1 || (Bool2 || Bool3) holds for any Bool{i} for i = 1,2,3 \in {TRUE, FALSE}
   * And FALSE is neutral element, as FALSE || TRUE == TRUE and FALSE || FALSE == FALSE
   */

  /**
   * 4) Let's describe monoid as M = ({TRUE, FALSE}, Composition, Identity).
   * Morphisms are: AND TRUE, and AND FALSE, where the former is an identity
   *
   * Composition of those two is AND FALSE.
   */

  /**
   * 5) Similarly to point above, with changes to M = ({0, 1, 2}, Composition, Identity)
   *
   * Morphisms are: add 3*n, add 1+3*n, add 2+3*n, where add 3*n is an identity. Those morphisms are closed under association
   * as (add 3*n and add 3*n) / (add 1+3*n and add 2+3*n) / (add 2+3*n and add 1+3*n) == add 3*n,
   * while (add 1+3*n and add 1+3*n) == add 2+3*n and (add 2+3*n and add 2+3*n) == add 1+3*n
   */
}
