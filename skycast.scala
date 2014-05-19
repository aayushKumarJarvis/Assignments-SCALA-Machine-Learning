object Skycast {

  //Function to calculate the number of digits in a number
  def numOfDigits(channelNumber : Int) : Int = {
    var count = 0
    var num = channelNumber
    while(num != 0) {
      count += 1
      num = num / 10
    }
    return count
  }

  //Function to count the clicks when traversed by using UP key
  def forwardCount(srcChannel: Int, destChannel: Int, count : Int, highCh : Int, lowCh : Int, blockedList : List[Int]) : Int = {
    if(srcChannel == destChannel)
      return count

    else {
      var x = srcChannel + 1
      if (x == (highCh + 1))
        x = lowCh
      if (blockedList.contains(x)) {

        if(blockedList.contains(x+1))
          forwardCount(x + 1, destChannel, count, highCh, lowCh, blockedList)
        else
          forwardCount(x + 1, destChannel, count+1, highCh, lowCh, blockedList)
      }
      else
        forwardCount(x, destChannel, count + 1, highCh, lowCh, blockedList)
    }
  }

  //Function to count the clicks when traverse by using DOWN key
  def reverseCount(srcChannel: Int, destChannel: Int, count : Int, highCh : Int, lowCh : Int, blockedList : List[Int]) : Int = {

    if(srcChannel == destChannel)
      return count

    else {
      var x = srcChannel - 1
      if(x == (lowCh - 1))
        x = highCh

      if(blockedList.contains(x)) {
        if (blockedList.contains(x - 1))
          reverseCount(x - 1, destChannel, count, highCh, lowCh, blockedList)
        else
          reverseCount(x - 1, destChannel, count + 1, highCh, lowCh, blockedList)
      }
      else
        reverseCount(x, destChannel, count + 1, highCh, lowCh, blockedList)
    }
  }

  def min(a: Int, b: Int) = if(a <= b) a else b // Computing the minimum of two numbers

  //Function to find minimum no. of clicks out of the two ways in which Gaurav can traverse
  def findMinClicks(blockedList : List[Int], channelsToView : List[Int], count : Int, prevNumber : Int, currentIndex : Int, highCh : Int, lowCh : Int, maxIndex: Int ) : Int = {

    if(currentIndex == maxIndex)
      return count

    else
    {

      if((channelsToView(currentIndex) >= 1 && channelsToView(currentIndex) <= 9) || (prevNumber == channelsToView(currentIndex))) {
        val updatedCount = count + 1
        val prev = channelsToView(currentIndex-1)
        findMinClicks(blockedList, channelsToView, updatedCount, prev, currentIndex+1, highCh, lowCh, maxIndex)
      }

      else {
        var temp1 = forwardCount(channelsToView(currentIndex-1), channelsToView(currentIndex), 0, highCh, lowCh, blockedList)
        var temp2 = reverseCount(channelsToView(currentIndex-1), channelsToView(currentIndex), 0, highCh, lowCh, blockedList)
        var t = min(temp1, temp2)

        if(prevNumber != -1) {
          temp1 = forwardCount(prevNumber, channelsToView(currentIndex), 0, highCh, lowCh, blockedList)
          temp2 = reverseCount(prevNumber, channelsToView(currentIndex), 0, highCh, lowCh, blockedList)
          var p2 = min(temp1, temp2) + 1
          t = min(t, p2)
        }

        var prev = -1
        var updatedCount = 0
        val numDigits = numOfDigits(channelsToView(currentIndex))

        if(t < numDigits)
          updatedCount = count + t
        else {
          updatedCount = count + numDigits
          prev = channelsToView(currentIndex-1)
        }

        findMinClicks(blockedList, channelsToView, updatedCount, prev, currentIndex+1, highCh, lowCh, maxIndex)
      }
    }
  }

  //Function to check the constraints provided in the question
  def constraintsCheck(lowCh: Int, highCh: Int, blockedChannels: List[Int], channelsToView: List[Int]): Boolean = {
    var flag = 0
    if (((lowCh < 0) || (highCh > 10000) || (highCh <= lowCh))) flag = 1
    for (x <- 0 to (blockedChannels.size - 1)) {
      if ((blockedChannels(x)) < 0 || (blockedChannels(x) > 10000))
        flag = 1
    }
    if (channelsToView.size >= 10) flag = 1
    if (blockedChannels.size >= 10) flag = 1
    if(flag == 0) return true
    else return false
  }

  //Main function apply inputs and check constraints
  def main(args: List[String]) {

    val lowch = readInt()
    val highch = readInt()
    val blockedChannels = List[Int](18, 19)
    val channelsToView = List(15, 14, 17, 1, 17)

    //Constraint Checks and displaying the output

    if(constraintsCheck(lowch, highch, blockedChannels,channelsToView) == true) {
      val res = computeMinClicks(lowch, highch, blockedChannels, channelsToView)
      println(res)

    }
     else println("Constraints not followed")
  }

  def computeMinClicks(lowch: Int, highch: Int, blockedChannels: List[Int], channelsToView: List[Int]): Int = {
    if(constraintsCheck(lowch, highch, blockedChannels,channelsToView) == true)
      return findMinClicks(blockedChannels, channelsToView, 0, -1, 1, highch, lowch, channelsToView.length) + numOfDigits(channelsToView(0))
    else
      return 0
    }
  }
