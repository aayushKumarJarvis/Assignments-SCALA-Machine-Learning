// Function to get the second last element of the list

def len(list: List[_]): Int = {

  def lenHelper(list: List[_], len: Int): Int = {
    if (list == Nil) len
    else lenHelper(list.tail, len + 1)
  }
  lenHelper(list, 0)
}
val list_1 = List(1,1,2,3,5,8)
val a = list_1(len(list_1)-2)
println(a)


