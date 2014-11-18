package akka.first.app.mapreduce


import akka.actor.Actor

import scala.collection.mutable

/**
 * Created by b05752 on 2014/11/18.
 */
class AggregateActor extends Actor {
  val finalReducedMap = new mutable.HashMap[String, Int]

  def receive: Receive = {
    case ReduceData(reduceDataMap) => aggregateInMemoryReduce(reduceDataMap)
    case Result => {println("%%%%%"); sender ! finalReducedMap.toString()}
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Int]): Unit = {
    println("@@@@@@@@@@@@")
    for((key, value) <- reducedList) {
      if (finalReducedMap contains key)
        finalReducedMap(key) = (value  + finalReducedMap.get(key).get)
      else
        finalReducedMap += (key -> value)
    }
  }
}
