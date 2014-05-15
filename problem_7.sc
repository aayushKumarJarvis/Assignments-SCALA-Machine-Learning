// function to slice from the list
def reverse[A](ls: List[A]): List[A] = {
  def reverseHelper(result: List[A], curList: List[A]): List[A] = curList match {
    case Nil       => result
    case num :: tail => reverseHelper(num :: result, tail)
  }
  reverseHelper(Nil, ls)
}
def slice[A](start: Int, end: Int, ls: List[A]): List[A] = {
  def sliceHelper(count: Int, curList: List[A], result: List[A]): List[A] =
    (count, curList) match {
      case (_, Nil)                     => reverse(result)
      case (c, h :: tail) if end <= c   => reverse(result)
      case (c, h :: tail) if start <= c => sliceHelper(c + 1, tail, h :: result)
      case (c, _ :: tail)               => sliceHelper(c + 1, tail, result)
    }
  sliceHelper(0, ls, Nil)
}
slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
