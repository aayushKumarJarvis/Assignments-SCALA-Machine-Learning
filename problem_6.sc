// function to drop every nth element in the list
def reverse[A](ls: List[A]): List[A] = {
  def reverseHelper(result: List[A], curList: List[A]): List[A] = curList match {
    case Nil       => result
    case num :: tail => reverseHelper(num :: result, tail)
  }
  reverseHelper(Nil, ls)
}

def drop[A](n: Int, ls: List[A]): List[A] = {
  def dropHelper(c: Int, curList: List[A], result: List[A]): List[A] = (c, curList) match {
    case (_, Nil) => reverse(result)
    case (1, _ :: tail) => dropHelper(n, tail, result)
    case (_, h :: tail) => dropHelper(c - 1, tail, h :: result)
  }
  dropHelper(n, ls, Nil)
}
drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
