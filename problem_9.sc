//function to generate a list of a given range
def rangeTailRecursive(start: Int, end: Int): List[Int] = {
  def rangeR(end: Int, result: List[Int]): List[Int] = {
    if (end < start) result
    else rangeR(end - 1, end :: result)
  }
  rangeR(end, Nil)
}

val list_1 = rangeTailRecursive(1,1000)

//function to compute the sum of all the elements in a list
def sum(xs: List[Int]): Int = {
  xs match {
    case x :: tail => x + sum(tail)
    case Nil => 0
  }
}
println(sum(list_1.filterNot(x=>((x%3==0) && (x%5==0)))))





  