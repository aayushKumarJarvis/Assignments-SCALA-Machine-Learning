// function to convert numbers to words
def matchTest(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case 3 => "three"
  case 4 => "four"
  case 5 => "five"
  case 6 => "six"
  case 7 => "seven"
  case 8 => "eight"
  case 9 => "nine"
  case _ => "not a valid integer"
}
// function to reverse a number
def reverseNum(num: Int) : Int = {
  var rev = 0
  var d1 = 0
  var t = num
  while(t>0) {
    d1 = t%10
    rev = (rev*10)+d1
    t = t/10
  }
  return rev
}
//function to give words to the number
def fullWords(num: Int) = {
  var k = num
  var d = 0
  while(k>0) {
    d = k%10
    k = k/10
    print(" "+matchTest(d))
   }
}

//println(reverseNum(175))
fullWords(reverseNum(175))






