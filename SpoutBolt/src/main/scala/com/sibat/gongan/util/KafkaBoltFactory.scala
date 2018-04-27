package com.sibat.gongan

import java.util.Properties
import org.apache.storm.kafka.bolt.KafkaBolt
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector
import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper

import com.sibat.gongan.imp._

object KafkaBoltFactory extends IPropertiesTrait{


  def getBolt(topic:String) = {
    //set producer properties.
    val props = new Properties()
    props.put("bootstrap.servers", KAFKABROKERS)
    props.put("acks", "1")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    new KafkaBolt().withProducerProperties(props)
                   .withTopicSelector(new DefaultTopicSelector(topic))
                   .withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper());

  }
}

