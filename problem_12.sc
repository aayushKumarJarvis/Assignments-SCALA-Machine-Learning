// function to calculate the millionth lexicographic permutation
def ps(s: String): Seq[String] = if(s.size == 1) Seq(s) else
  s.flatMap(c => ps(s.filterNot(_ == c)).map(c +))
//millionth permutation is given by using the 999999th iteration of the sequence
val r = ps("0123456789")(999999)



