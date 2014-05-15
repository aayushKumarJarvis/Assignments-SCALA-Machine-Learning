// function to remove consecutive duplicates
def reverse[A](ls: List[A]): List[A] = {
  def reverseHelper(result: List[A], curList: List[A]): List[A] = curList match {
    case Nil       => result
    case num :: tail => reverseHelper(num :: result, tail)
  }
  reverseHelper(Nil, ls)
}
def duplicates[A](ls: List[A]): List[A] = {
  def duplicatesHelper(result: List[A], curList: List[A]): List[A] = curList match {
    case h :: tail => duplicatesHelper(h :: result, tail.dropWhile(_ == h))
    case Nil       => reverse(result)
  }
  duplicatesHelper(Nil, ls)
}

val list_1 = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
println(duplicates(list_1))

