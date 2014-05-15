//function to reverse a list in
def reverse[A](ls: List[A]): List[A] = {
  def reverseHelper(result: List[A], curList: List[A]): List[A] = curList match {
    case Nil       => result
    case num :: tail => reverseHelper(num :: result, tail)
  }
  reverseHelper(Nil, ls)
}

val list_1 = List(1,1,2,3,5,8)
println(reverse(list_1))


