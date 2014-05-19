

import Array._
import scala.collection.mutable

object barterRates {


  def gcd(a: Int, b: Int) : Int = {
    if(b == 0) a
    else  gcd(b, a % b)
  }

  def dfs(graph : Array[Array[(Int, Int)]], indexMap: Map[String, Int], firstIndex : Int, secondIndex : Int, visitedNodes : Array[Int], initialTuple : (Int,Int)) : (Int, Int) = {
    if(firstIndex == secondIndex) {
      val gcdOfNumAndDen = gcd(initialTuple._1, initialTuple._2)
      (initialTuple._1/gcdOfNumAndDen, initialTuple._2/gcdOfNumAndDen)
    }
    else {
      for(i <- 0 to graph.length-1) {
        val tempTuple = graph(firstIndex)(i)
        if((i!=firstIndex) && (visitedNodes(i) != 1) && (tempTuple._1 != -1 && tempTuple._2 != -1))
        {
          visitedNodes(i) = 1
          val temp = dfs(graph, indexMap, i, secondIndex, visitedNodes, (initialTuple._1 * tempTuple._1, initialTuple._2 * tempTuple._2))
          visitedNodes(i) = 0
          if(temp._1 != -1 && temp._2 != -1)
            return temp
        }
      }
      (-1, -1)
    }
  }

  //Function to compute a barter relation between two elements
  def answerQuestions(graph : Array[Array[(Int,Int)]], indexMap: Map[String, Int], firstWord : String, secondWord : String) : String = {
    val nodeQueue = mutable.Queue.empty[Int]
    val tuplesQueue = mutable.Queue.empty[(Int, Int)]
    nodeQueue.enqueue(indexMap(firstWord))
    tuplesQueue.enqueue((1,1))
    val visitedArray = Array.fill(graph.size) {0}
    visitedArray(indexMap(firstWord)) = 1
    //val resultTuple = bfs(graph, indexMap, secondWord, visitedArray, nodeQueue, tuplesQueue)

    val resultTuple = dfs(graph, indexMap, indexMap(firstWord), indexMap(secondWord), visitedArray, (1, 1))

    if(resultTuple._1 != -1 && resultTuple._2 != -1)
    {
      resultTuple._1.toString + " " + firstWord + " = " + resultTuple._2.toString + " " + secondWord
    }
    else
      "? " + firstWord + " = " + "? " + secondWord
  }

  //Function to create a set of the elements
  def createSet(barterRelationsList : Array[String], setOfElements : Set[String], i : Int, maxIndex : Int) : Set[String] = {

    if(i ==  maxIndex)
      setOfElements
    else {
      val currentString = barterRelationsList(i)
      val splitStrings = currentString.split(" = ")
      val firstHalf = splitStrings(0).split("! ").filterNot(_ == "")
      val firstHalfParts: Array[String] = firstHalf(0).split(" ")
      val secondHalfParts: Array[String] = splitStrings(1).split(" ")
      val setWithAdditions: Set[String] = setOfElements ++ Set[String](firstHalfParts(1)) ++ Set[String](secondHalfParts(1))
      val newSet: Set[String] = createSet(barterRelationsList, setWithAdditions, i + 1, maxIndex)
      newSet
    }
  }

  def createGraph(graph : Array[Array[(Int, Int)]], barterRelationsList : Array[String], indexMap : Map[String, Int] ,i : Int) : Array[Array[(Int, Int)]] = {

    if(i == barterRelationsList.length)
      return graph
    else {
      val currentString = barterRelationsList(i)
      val splitStrings = currentString.split(" = ")
      val firstHalf = splitStrings(0).split("! ").filterNot(_ == "")
      val firstHalfParts = firstHalf(0).split(" ")
      val secondHalfParts = splitStrings(1).split(" ")
      val firstNumber = firstHalfParts(0).toInt
      val secondNumber = secondHalfParts(0).toInt
      graph(indexMap(firstHalfParts(1)))(indexMap(secondHalfParts(1))) = (firstNumber, secondNumber)
      graph(indexMap(secondHalfParts(1)))(indexMap(firstHalfParts(1))) = (secondNumber, firstNumber)
      createGraph(graph, barterRelationsList, indexMap, i + 1)
    }
  }

  //Function to compute the List of answers to input barter relations
  def answerQueriesHelper(graph : Array[Array[(Int,Int)]], indexMap: Map[String, Int], barterQueries : Array[String], i : Int, maxLength : Int) : List[String] = {
    if(i != maxLength)
    {
      val currentString = barterQueries(i)
      val splitStrings = currentString.split(" = ")
      val firstHalf = splitStrings(0).split(". ").filterNot(_ == "")
      val firstWord = firstHalf(0)
      val secondWord = splitStrings(1)
      val resultString : List[String] = List[String](answerQuestions(graph, indexMap, firstWord, secondWord)) ++ answerQueriesHelper(graph, indexMap, barterQueries, i+1, maxLength)
      return resultString
    }
    else
      List()
  }

  def findBarterRates(barterRelationsList : Array[String], barterQueries : Array[String]) : List[String] = {

    val setOfElements = createSet(barterRelationsList, Set[String](), 0, barterRelationsList.length).zipWithIndex
    val indexMap = setOfElements.map {i => i._1 -> i._2 }.toMap
    println(indexMap)
    var graph = Array.fill(setOfElements.size, setOfElements.size) { (-1, -1) }
    graph = createGraph(graph, barterRelationsList, indexMap, 0)
    answerQueriesHelper(graph, indexMap, barterQueries, 0, barterQueries.length)
  }

  def displayBarterRates(barterRelationsList : Array[String], barterQueries : Array[String]) = {
    val resultString : List[String] = findBarterRates(barterRelationsList, barterQueries)
    println(resultString)
    resultString
  }
}
