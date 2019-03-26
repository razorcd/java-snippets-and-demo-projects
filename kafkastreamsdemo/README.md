### Basic `kafka-streams` demo to simulate missing `key.serializer` bug when setting `topic.segment.index.bytes`.

### Exception thrown:


```
/home/cristiandugacicu/.sdkman/candidates/java/current/bin/java -javaagent:/home/cristiandugacicu/applications/idea-IU-182.4505.22/lib/idea_rt.jar=39567:/home/cristiandugacicu/applications/idea-IU-182.4505.22/bin -Dfile.encoding=UTF-8 -classpath /home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/charsets.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/deploy.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/cldrdata.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/dnsns.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/jaccess.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/jfxrt.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/localedata.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/nashorn.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/sunec.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/sunjce_provider.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/sunpkcs11.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/ext/zipfs.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/javaws.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/jce.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/jfr.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/jfxswt.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/jsse.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/management-agent.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/plugin.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/resources.jar:/home/cristiandugacicu/.sdkman/candidates/java/current/jre/lib/rt.jar:/home/cristiandugacicu/projects/personal/kafkastreamsdemo/target/classes:/home/cristiandugacicu/.m2/repository/org/apache/kafka/kafka-streams/2.2.0/kafka-streams-2.2.0.jar:/home/cristiandugacicu/.m2/repository/org/apache/kafka/kafka-clients/2.2.0/kafka-clients-2.2.0.jar:/home/cristiandugacicu/.m2/repository/com/github/luben/zstd-jni/1.3.8-1/zstd-jni-1.3.8-1.jar:/home/cristiandugacicu/.m2/repository/org/lz4/lz4-java/1.5.0/lz4-java-1.5.0.jar:/home/cristiandugacicu/.m2/repository/org/xerial/snappy/snappy-java/1.1.7.2/snappy-java-1.1.7.2.jar:/home/cristiandugacicu/.m2/repository/org/apache/kafka/connect-json/2.2.0/connect-json-2.2.0.jar:/home/cristiandugacicu/.m2/repository/org/apache/kafka/connect-api/2.2.0/connect-api-2.2.0.jar:/home/cristiandugacicu/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.9.8/jackson-databind-2.9.8.jar:/home/cristiandugacicu/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar:/home/cristiandugacicu/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.9.8/jackson-core-2.9.8.jar:/home/cristiandugacicu/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.9.8/jackson-datatype-jdk8-2.9.8.jar:/home/cristiandugacicu/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/cristiandugacicu/.m2/repository/org/rocksdb/rocksdbjni/5.15.10/rocksdbjni-5.15.10.jar:/home/cristiandugacicu/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar app.Main
[main] INFO org.apache.kafka.streams.StreamsConfig - StreamsConfig values: 
	application.id = wordcount-application
	application.server = 
	bootstrap.servers = [localhost:9092]
	buffered.records.per.partition = 1000
	cache.max.bytes.buffering = 10485760
	client.id = 
	commit.interval.ms = 30000
	connections.max.idle.ms = 540000
	default.deserialization.exception.handler = class org.apache.kafka.streams.errors.LogAndFailExceptionHandler
	default.key.serde = class org.apache.kafka.common.serialization.Serdes$StringSerde
	default.production.exception.handler = class org.apache.kafka.streams.errors.DefaultProductionExceptionHandler
	default.timestamp.extractor = class org.apache.kafka.streams.processor.FailOnInvalidTimestamp
	default.value.serde = class org.apache.kafka.common.serialization.Serdes$StringSerde
	max.task.idle.ms = 0
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	num.standby.replicas = 0
	num.stream.threads = 1
	partition.grouper = class org.apache.kafka.streams.processor.DefaultPartitionGrouper
	poll.ms = 100
	processing.guarantee = at_least_once
	receive.buffer.bytes = 32768
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	replication.factor = 1
	request.timeout.ms = 40000
	retries = 0
	retry.backoff.ms = 100
	rocksdb.config.setter = null
	security.protocol = PLAINTEXT
	send.buffer.bytes = 131072
	state.cleanup.delay.ms = 600000
	state.dir = /tmp/kafka-streams
	topology.optimization = none
	upgrade.from = null
	windowstore.changelog.additional.retention.ms = 86400000

[main] INFO org.apache.kafka.clients.admin.AdminClientConfig - AdminClientConfig values: 
	bootstrap.servers = [localhost:9092]
	client.dns.lookup = default
	client.id = wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-admin
	connections.max.idle.ms = 300000
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	receive.buffer.bytes = 65536
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 120000
	retries = 5
	retry.backoff.ms = 100
	sasl.client.callback.handler.class = null
	sasl.jaas.config = null
	sasl.kerberos.kinit.cmd = /usr/bin/kinit
	sasl.kerberos.min.time.before.relogin = 60000
	sasl.kerberos.service.name = null
	sasl.kerberos.ticket.renew.jitter = 0.05
	sasl.kerberos.ticket.renew.window.factor = 0.8
	sasl.login.callback.handler.class = null
	sasl.login.class = null
	sasl.login.refresh.buffer.seconds = 300
	sasl.login.refresh.min.period.seconds = 60
	sasl.login.refresh.window.factor = 0.8
	sasl.login.refresh.window.jitter = 0.05
	sasl.mechanism = GSSAPI
	security.protocol = PLAINTEXT
	send.buffer.bytes = 131072
	ssl.cipher.suites = null
	ssl.enabled.protocols = [TLSv1.2, TLSv1.1, TLSv1]
	ssl.endpoint.identification.algorithm = https
	ssl.key.password = null
	ssl.keymanager.algorithm = SunX509
	ssl.keystore.location = null
	ssl.keystore.password = null
	ssl.keystore.type = JKS
	ssl.protocol = TLS
	ssl.provider = null
	ssl.secure.random.implementation = null
	ssl.trustmanager.algorithm = PKIX
	ssl.truststore.location = null
	ssl.truststore.password = null
	ssl.truststore.type = JKS

[main] WARN org.apache.kafka.clients.admin.AdminClientConfig - The configuration 'topic.segment.index.bytes' was supplied but isn't a known config.
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 2.2.0
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 05fcfde8f69b0349
[main] INFO org.apache.kafka.streams.processor.internals.StreamThread - stream-thread [wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-StreamThread-1] Creating restore consumer client
[main] INFO org.apache.kafka.clients.consumer.ConsumerConfig - ConsumerConfig values: 
	auto.commit.interval.ms = 5000
	auto.offset.reset = none
	bootstrap.servers = [localhost:9092]
	check.crcs = true
	client.dns.lookup = default
	client.id = wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-StreamThread-1-restore-consumer
	connections.max.idle.ms = 540000
	default.api.timeout.ms = 60000
	enable.auto.commit = false
	exclude.internal.topics = true
	fetch.max.bytes = 52428800
	fetch.max.wait.ms = 500
	fetch.min.bytes = 1
	group.id = null
	heartbeat.interval.ms = 3000
	interceptor.classes = []
	internal.leave.group.on.close = false
	isolation.level = read_uncommitted
	key.deserializer = class org.apache.kafka.common.serialization.ByteArrayDeserializer
	max.partition.fetch.bytes = 1048576
	max.poll.interval.ms = 2147483647
	max.poll.records = 1000
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor]
	receive.buffer.bytes = 65536
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 30000
	retry.backoff.ms = 100
	sasl.client.callback.handler.class = null
	sasl.jaas.config = null
	sasl.kerberos.kinit.cmd = /usr/bin/kinit
	sasl.kerberos.min.time.before.relogin = 60000
	sasl.kerberos.service.name = null
	sasl.kerberos.ticket.renew.jitter = 0.05
	sasl.kerberos.ticket.renew.window.factor = 0.8
	sasl.login.callback.handler.class = null
	sasl.login.class = null
	sasl.login.refresh.buffer.seconds = 300
	sasl.login.refresh.min.period.seconds = 60
	sasl.login.refresh.window.factor = 0.8
	sasl.login.refresh.window.jitter = 0.05
	sasl.mechanism = GSSAPI
	security.protocol = PLAINTEXT
	send.buffer.bytes = 131072
	session.timeout.ms = 10000
	ssl.cipher.suites = null
	ssl.enabled.protocols = [TLSv1.2, TLSv1.1, TLSv1]
	ssl.endpoint.identification.algorithm = https
	ssl.key.password = null
	ssl.keymanager.algorithm = SunX509
	ssl.keystore.location = null
	ssl.keystore.password = null
	ssl.keystore.type = JKS
	ssl.protocol = TLS
	ssl.provider = null
	ssl.secure.random.implementation = null
	ssl.trustmanager.algorithm = PKIX
	ssl.truststore.location = null
	ssl.truststore.password = null
	ssl.truststore.type = JKS
	value.deserializer = class org.apache.kafka.common.serialization.ByteArrayDeserializer

[main] WARN org.apache.kafka.clients.consumer.ConsumerConfig - The configuration 'topic.segment.index.bytes' was supplied but isn't a known config.
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 2.2.0
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 05fcfde8f69b0349
[main] INFO org.apache.kafka.streams.processor.internals.StreamThread - stream-thread [wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-StreamThread-1] Creating shared producer client
[main] INFO org.apache.kafka.clients.producer.ProducerConfig - ProducerConfig values: 
	acks = 1
	batch.size = 16384
	bootstrap.servers = [localhost:9092]
	buffer.memory = 33554432
	client.dns.lookup = default
	client.id = wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-StreamThread-1-producer
	compression.type = none
	connections.max.idle.ms = 540000
	delivery.timeout.ms = 120000
	enable.idempotence = false
	interceptor.classes = []
	key.serializer = class org.apache.kafka.common.serialization.ByteArraySerializer
	linger.ms = 100
	max.block.ms = 60000
	max.in.flight.requests.per.connection = 5
	max.request.size = 1048576
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	partitioner.class = class org.apache.kafka.clients.producer.internals.DefaultPartitioner
	receive.buffer.bytes = 32768
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 30000
	retries = 2147483647
	retry.backoff.ms = 100
	sasl.client.callback.handler.class = null
	sasl.jaas.config = null
	sasl.kerberos.kinit.cmd = /usr/bin/kinit
	sasl.kerberos.min.time.before.relogin = 60000
	sasl.kerberos.service.name = null
	sasl.kerberos.ticket.renew.jitter = 0.05
	sasl.kerberos.ticket.renew.window.factor = 0.8
	sasl.login.callback.handler.class = null
	sasl.login.class = null
	sasl.login.refresh.buffer.seconds = 300
	sasl.login.refresh.min.period.seconds = 60
	sasl.login.refresh.window.factor = 0.8
	sasl.login.refresh.window.jitter = 0.05
	sasl.mechanism = GSSAPI
	security.protocol = PLAINTEXT
	send.buffer.bytes = 131072
	ssl.cipher.suites = null
	ssl.enabled.protocols = [TLSv1.2, TLSv1.1, TLSv1]
	ssl.endpoint.identification.algorithm = https
	ssl.key.password = null
	ssl.keymanager.algorithm = SunX509
	ssl.keystore.location = null
	ssl.keystore.password = null
	ssl.keystore.type = JKS
	ssl.protocol = TLS
	ssl.provider = null
	ssl.secure.random.implementation = null
	ssl.trustmanager.algorithm = PKIX
	ssl.truststore.location = null
	ssl.truststore.password = null
	ssl.truststore.type = JKS
	transaction.timeout.ms = 60000
	transactional.id = null
	value.serializer = class org.apache.kafka.common.serialization.ByteArraySerializer

[main] WARN org.apache.kafka.clients.producer.ProducerConfig - The configuration 'topic.segment.index.bytes' was supplied but isn't a known config.
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 2.2.0
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 05fcfde8f69b0349
[main] INFO org.apache.kafka.streams.processor.internals.StreamThread - stream-thread [wordcount-application-c02ff79f-9c3e-4339-b41a-d01f280f5bdc-StreamThread-1] Creating consumer client
Exception in thread "main" org.apache.kafka.common.config.ConfigException: Missing required configuration "key.serializer" which has no default value.
	at org.apache.kafka.common.config.ConfigDef.parseValue(ConfigDef.java:474)
	at org.apache.kafka.common.config.ConfigDef.parse(ConfigDef.java:464)
	at org.apache.kafka.common.config.AbstractConfig.<init>(AbstractConfig.java:62)
	at org.apache.kafka.common.config.AbstractConfig.<init>(AbstractConfig.java:75)
	at org.apache.kafka.clients.producer.ProducerConfig.<init>(ProducerConfig.java:392)
	at org.apache.kafka.streams.StreamsConfig.getMainConsumerConfigs(StreamsConfig.java:1014)
	at org.apache.kafka.streams.processor.internals.StreamThread.create(StreamThread.java:666)
	at org.apache.kafka.streams.KafkaStreams.<init>(KafkaStreams.java:718)
	at org.apache.kafka.streams.KafkaStreams.<init>(KafkaStreams.java:634)
	at org.apache.kafka.streams.KafkaStreams.<init>(KafkaStreams.java:544)
	at app.Main.main(Main.java:36)

Process finished with exit code 1
``` 