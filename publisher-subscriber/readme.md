Project : Publisher - Subscriber

Keynotes:

1) Publisher can PUBLISH message to a TOPIC
2) Subscriber, a client or consumer, can SUBSCRIBE to a TOPIC
3) There can be more than Subscriber of a single TOPIC
4) Once Message is PUBLISHED to a TOPIC, it should be fanned-out to all it's Subscriber
5) Subscriber should be able to receive Messages Parallelly
6) To re-transmit/replay message, Subscriber can reset offset of Message for a TOPIC


Public Interfaces Required:

1) Publisher - Interface (DONE)
   - createTopic(name, id) : Create a Topic with Id 
   - publish(message, topic) : Publish Message to a Topic

2) Subscriber - Interface (DONE)
   - subscribe(topic) : Subscribe to a Topic
   - consume() : Consume Message from a Topic by current offset
   - resetOffset(topic, offset) : Reset to new offset for a given topic


Internal Interfaces/Classes Required:

1) Message - Model/Data Class of Message being published
   - ID, Timestamp, Data (Object), ttl

2) X-Message - Extends Message to Handle Different type of Message Data

3) MessageHandler - Extends Message to Handle Different type of Message Data

4) QueueHandler - Provides methods to access Queue, Abstraction Layer
   - createTopic(name, id) : Create a Topic with Id
   - publish(message, topic) : Publish Message to a Topic

2) Queue - Multi-Threaded Queue
   - <Topic-ID, TopicHandler>
   - createTopic(name, id)
   - publish(message, topic)
   - subscribe(id, topic)

3) TopicHandler - Provides methods to access Topic, Abstraction Layer
   - subscribe()
   - publish(message)
   - removeSubscriber(subscriberId)
   - startConsumeWorker()

4) Topic
   - Id, Name
   - Subscribers[]
   - messages[]

Subscriber (Interface) - Client or consumer
   - ID
   - Name
   - Offset
   - consume(message)

XType-Subscriber - Implementation of a Subscriber
   - consume() 


5) SubscriberImpl (TODO)
   - subscribe(topic) : Subscribe to a Topic
   - consume() : Consume Message from a Topic by current offset
   - resetOffset(topic, offset) : Reset to new offset for a given topic

6) PublisherImpl (TODO)
   - createTopic(name, id) : Create a Topic with Id
   - publish(message, topic) : Publish Message to a Topic

7) ConsumeMessageWorker - Multi-Threaded Workers to consume message from a Topic for each subscriber
   - run() -> Start Consuming message : For each Topic consumers will be running, if there is any message available in the Queue
   - wakeUpIfRequired() : Starts the thread which is in waiting state