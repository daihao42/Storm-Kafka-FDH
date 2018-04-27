package com.sibat.gongan.base
/****
***/
import org.apache.storm.topology.IRichBolt
//import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import scala.language.implicitConversions
import org.apache.storm.tuple.{Fields, Tuple, Values}
import scala.util.Try

//class ExcutorBolt extends BaseRichBolt {
class ExcutorBolt extends IRichBolt {
    var _context: TopologyContext = _
    var _conf: java.util.Map[_, _] = _
    var _collector: OutputCollector = _

    override def execute(t: Tuple){
      Try{
        val st = t.getValue(0).toString()
        println("消费："+st)
        _collector.emit(new Values(st))
      }
    }

    def declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(new Fields("ap_location"))
    }


    //def _collector_(collector:OutputCollector) = Unit

    def prepare(conf:java.util.Map[_, _], context:TopologyContext, collector:OutputCollector) {
         _collector = collector
         _context = context
         _conf = conf
    }

    override def cleanup() = println("结束！")

    def getComponentConfiguration() = null
}

