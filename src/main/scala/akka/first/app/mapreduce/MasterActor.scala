package akka.first.app.mapreduce

import akka.actor.{Props, Actor}
import akka.routing.RoundRobinRouter

/**
 * Created by b05752 on 2014/11/18.
 */
class MasterActor extends Actor{

  val mapActor = context.actorOf(Props[MapActor].withRouter(
    RoundRobinRouter(nrOfInstances = 5)), name = "map")

  val reduceActor = context.actorOf(Props[ReduceActor].withRouter(
    RoundRobinRouter(nrOfInstances = 5)), name = "reduce")

  val aggregateActor = context.actorOf(Props[AggregateActor], name = "aggregate")

  def receive: Receive = {
    case line: String => { println("000000");mapActor ! line}
    case mapData: MapData => {println("111111");reduceActor ! mapData}
    case reduceData: ReduceData => {println("22222222");aggregateActor ! reduceData}
    case Result => {println("33333333333");aggregateActor forward Result}
  }
}
