

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.matchers.{ShouldMatchers, Matcher}
import scala.collection.immutable.List

class barterRatesTest extends FlatSpec with Matchers {

  "BarterRates" should "compute for given inputs" in {
    barterRates.displayBarterRates(Array("! 6 apple = 15 banana", "! 47 grape = 9 mango", "! 2 banana = 1 grape"), Array("? banana = apple", "? mango = apple")) should be (List[String]("5 banana = 2 apple", "45 mango = 188 apple"))
    barterRates.displayBarterRates(Array("! 9 cat = 1 dog", "! 1 cow = 12 chicken"), Array("? chicken = dog")) should be (List[String]("? chicken = ? dog"))
    barterRates.displayBarterRates(Array("! 1 fork = 1 spoon", "! 4 television = 83 knife", "! 91 knife = 2 dvdplayer", "! 1 microwave = 37 fork", "! 7 toaster = 2 microwave", "! 2 spoon = 1 knife"), Array("? dvdplayer = toaster", "? television = microwave")) should be (List[String]("74 dvdplayer = 637 toaster", "74 television = 83 microwave"))
    barterRates.displayBarterRates(Array("! 1 fork = 1 spoon", "! 1 television = 1 knife", "! 1 knife = 1 dvdplayer", "! 1 microwave = 1 fork", "! 1 toaster = 1 microwave", "! 1 spoon = 1 knife"), Array("? dvdplayer = toaster", "? television = microwave")) should be (List[String]("1 dvdplayer = 1 toaster", "1 television = 1 microwave"))
    barterRates.displayBarterRates(Array("! 9 cat = 1 dog", "! 1 cow = 12 chicken"), Array("? dog = cat", "? dog = chicken")) should be (List[String]("1 dog = 9 cat", "? dog = ? chicken"))
  }



}


