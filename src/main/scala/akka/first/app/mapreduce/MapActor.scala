package akka.first.app.mapreduce

import akka.actor.Actor

import scala.collection.mutable.ArrayBuffer

/**
 * Created by b05752 on 2014/11/18.
 */
class MapActor extends Actor{

  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be", "do",
  "go", "if", "in", "is", "it", "of", "on", "the", "to")

  def receive: Receive = {
    case message: String => {println("+++++"); sender ! evaluateExpression(message)}
  }

  def evaluateExpression(line: String): MapData = MapData {
    line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
      (index, word) =>
        if (!STOP_WORDS_LIST.contains(word.toLowerCase))
          index += WordCount(word.toLowerCase, 1)
        else index
    }
  }
}
