package com.daihao.zep.util

import java.util.Properties
import org.apache.storm.kafka.bolt.KafkaBolt
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector
import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper

import com.daihao.zep.imp._

object KafkaBoltFactory extends IPropertiesTrait{


  def getBolt(topic:String) = {
    //set producer properties.
    val props = new Properties()
    props.put("bootstrap.servers", KAFKABROKERS)
    props.put("acks", "1")
    props.put("serializer.class", "org.apache.kafka.common.serialization.StringSerializer");
    new KafkaBolt().withProducerProperties(props)
                   .withTopicSelector(new DefaultTopicSelector(topic))
                   .withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper());

  }
}

