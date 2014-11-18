package akka.first.app.mapreduce

import akka.actor.Actor

/**
 * Created by b05752 on 2014/11/18.
 */
class ReduceActor extends Actor {
  def receive: Receive = {
    case MapData(dataList) =>
      sender ! reduce(dataList)
  }
  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    words.foldLeft(Map.empty[String, Int]) { (index, words) =>
      if (index contains words.word)
        index + (words.word -> (index.get(words.word).get + 1))
      else
        index + (words.word -> 1)
    }
  }
}
