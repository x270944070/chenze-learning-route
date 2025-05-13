# 生产者
1. 主线程：生产消息 -> 拦截器 -> 序列化器 -> 分区器
    拦截器、序列化器、分区器都是可配置的
    其中分区规则：如果发送消息时指定分区则发送到指定分区，如果没有指定分区，如果设置了对应的key，将按照key的hash值取模发送到分区，如果没有设置key，将按照黏性规则，随机发送到某个批次堆满（16k）之后再换随机分区
2. 到达缓存区（记录累加器）
    * 缓存总大小默认32M
    * 在缓存中有很多双端队列，一个Deque对应一个Partition
    * 一个Deque最大大小为16k
3. Sender线程
   * 当缓存区中的某个批次满足batch.size或者linger.ms时，将其放置在待发送区域，根据配置`in.flight.requests`，默认是5
   * acks：
        * 0：表示只管发送消息，不管是否发送成功
        * 1：表示当Leader接收成功之后就表示成功了
        * -1或者all：表示当所有的ISR同步成功之后，才会返回成功（当配置为1时，实际会按照Broker端参数`min.insync.replicas`数量确定）
   * retries：重试次数，默认最大值。当消息发送失败之后重试
4. 将多个请求的消息发送到Broker

# Broker
1. Leader的选举
   1. 每个Broker中都有一个组件叫做Controller，它主要用于管理各个Broker节点，那么对于Broker的选举也是它来操作的，首先会选出主Controller节点。
        Controller的选举比较简单：谁先注册到Zookeeper谁就是主Controller
   2. 以ISR中的节点按照AR中的顺序选第一个Partition作为Leader
   3. 上述这些信息，Controller、Leader、Follower、ISR、OSR等信息都会同步到Zookeeper中

2. 接收消息
    1. 生产者只与

