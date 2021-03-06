//recursive problem to compute the grid problem

def f(row: Seq[Long], c: Int): Long =
  if (c == 0) row.last else {
    f(row.scan(0L)(_ + _), c - 1)
  }

def r(n: Int) = f(Seq.fill(n + 1)(1L), n)

println(r(20))
