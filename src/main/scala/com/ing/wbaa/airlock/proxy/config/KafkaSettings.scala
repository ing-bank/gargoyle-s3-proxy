package com.ing.wbaa.airlock.proxy.config

import akka.actor.{ ExtendedActorSystem, Extension, ExtensionId, ExtensionIdProvider }
import com.typesafe.config.Config

class KafkaSettings(config: Config) extends Extension {
  val kafkaEnabled: Boolean = config.getBoolean("akka.kafka.producer.enabled")
  val bootstrapServers: String = config.getString("akka.kafka.producer.bootstrapServers")
  val createEventsTopic: String = config.getString("akka.kafka.producer.createTopic")
  val deleteEventsTopic: String = config.getString("akka.kafka.producer.deleteTopic")
  val kafkaConfig = config.getConfig("akka.kafka.producer")
}

object KafkaSettings extends ExtensionId[KafkaSettings] with ExtensionIdProvider {
  override def createExtension(system: ExtendedActorSystem): KafkaSettings = new KafkaSettings(system.settings.config)
  override def lookup(): ExtensionId[KafkaSettings] = KafkaSettings
}