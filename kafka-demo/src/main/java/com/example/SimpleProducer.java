package com.example;

//import util.properties packages
import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

//创建 java类 名为"SimpleProducer"

/**
 * 简易生产者类
 * https://www.w3cschool.cn/apache_kafka/apache_kafka_simple_producer_example.html
 */
public class SimpleProducer {

    //参数：topic名称
    public static void main(String[] args) throws Exception{

        // 检查参数长度
        if(args.length == 0){
            System.out.println("Enter topic name");
            return;
        }

        //topic名称赋值给String类型变量
        String topicName = args[0].toString();
        //创建properties实例来访问producer配置
        Properties props = new Properties();
        //赋值 localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //acks配置控制生产者请求下的标准是完全的。
        props.put("acks", "all");
        //若请求失败, producer自动重试次数
        props.put("retries", 0);
        //配置缓存大小（buffer size）
        props.put("batch.size", 16384);
        //将请求数减少到0以下：如果你想减少请求的数量，你可以将linger.ms设置为大于某个值的东西。
        props.put("linger.ms", 1);
        //控制生产者可用于缓冲的存储器的总量。
        props.put("buffer.memory", 33554432);

        //key 序列化
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        //value序列化
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        //Producer对象创建
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        //遍历发送消息
        for(int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>(topicName,
                    Integer.toString(i), Integer.toString(i)));

        System.out.println("Message sent successfully");
        producer.close();
    }
}