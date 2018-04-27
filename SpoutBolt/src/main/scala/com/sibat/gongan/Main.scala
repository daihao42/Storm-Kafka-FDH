package com.sibat.gongan

import org.apache.storm.Config
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.LocalCluster

import com.sibat.gongan.base._
import com.sibat.gongan.util._
import com.sibat.gongan.imp._

object Main extends IPropertiesTrait{

	def main(args: Array[String]): Unit = {

      val builder = new TopologyBuilder()

      val conf = new Config()
      conf setDebug true

      val cluster = new LocalCluster()

      builder.setSpout("consumer",KafkaSpoutFactory.getSpout(INPUTTOPICS),1)
      builder.setBolt("executor", new ExcutorBolt, 3).shuffleGrouping("consumer")
      builder.setBolt("producer", KafkaBoltFactory.getBolt("test"),3).shuffleGrouping("executor")

      cluster.submitTopology("test", conf, builder.createTopology())

     //cluster.killTopology("test")
     //cluster.shutdown()
	}

}
