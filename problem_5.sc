//duplicating a number n times
def duplicateN[A](n: Int, ls: List[A]): List[A] =
  ls flatMap { List.make(n, _) }

val list_1 = List('a, 'b, 'c, 'c, 'd)
println(duplicateN(3,list_1))

