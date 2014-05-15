// function to check if a number is prime or not
def isPrime(num: Int): Boolean = {
  var count = 0
  for (x <- 1 to num) {
    if((num%x) == 0) {
      count = count+1
    }
  }
  if(count == 2) {
    return true
  }
  else
    return false
}

// function to generate a sequence of integers in a range
def rangeTailRecursive(start: Int, end: Int): List[Int] = {
  def rangeR(end: Int, result: List[Int]): List[Int] = {
    if (end < start) result
    else rangeR(end - 1, end :: result)
  }
  rangeR(end, Nil)
}

println(rangeTailRecursive(7,31).filter(x=>isPrime(x)))
