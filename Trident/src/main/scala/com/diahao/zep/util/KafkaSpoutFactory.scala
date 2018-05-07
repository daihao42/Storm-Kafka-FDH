package com.daihao.zep.util

import org.apache.storm.kafka.BrokerHosts
import org.apache.storm.kafka.ZkHosts
import org.apache.storm.kafka.SpoutConfig
import org.apache.storm.kafka.KafkaConfig
import org.apache.storm.kafka.KafkaSpout
import org.apache.storm.spout.SchemeAsMultiScheme
import org.apache.storm.kafka.StringScheme

import com.daihao.zep.imp._

object KafkaSpoutFactory extends IPropertiesTrait{

  def getSpout(topic:String) = {
    val bh = new ZkHosts(ZOOKEEPERHOSTS)
    val sp = new SpoutConfig(bh,topic,ZOOKEEPEROOT,topic+"_id")
    sp.scheme = new SchemeAsMultiScheme(new StringScheme())
    new KafkaSpout(sp)
  }
}
