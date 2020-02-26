package main

import (
	"fmt"
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"log"
	"os"
	"time"
)

func main() {
	fmt.Println("test sub start...")
	//连接参数
	optionMap := make(map[string]string)
	optionMap["address"] = "tcp://47.93.10.179:1883"
	optionMap["clientId"] = "sub-simple"
	optionMap["username"] = "admin"
	optionMap["password"] = "123"
	//设置连接参数
	opts := subInitClientOption(optionMap)
	client := MQTT.NewClient(opts)
	//创建连接
	if token := client.Connect(); token.Wait() && token.Error() != nil {
		panic(token.Error())
	}
	topic := "test1/#"
	//订阅

	go subscribe(&client, topic, 0)
	countCh <- 0
	select {
	case <-jobDoneCh:
	}

	log.Println(time.Since(start))

}

//qos:允许监听的qos，2则可以监听0、1、2，  1则监听0，1   0监听0
func subscribe(client *MQTT.Client, topic string, qos byte) {
	if token := (*client).Subscribe(topic, qos, msgReceivedHandler); token.Wait() && token.Error() != nil {
		log.Fatalln(token.Error())
		os.Exit(1)
	}
}

var start time.Time

var countCh chan int = make(chan int)

var jobDoneCh chan int = make(chan int)

func msgReceivedHandler(client MQTT.Client, message MQTT.Message) {
	go func() {
		count := <-countCh
		if count == 0 {
			start = time.Now()
		}
		log.Printf("Receive - [topic: %s]  [Message: %s]  [QoS: %d] \n", message.Topic(), message.Payload(),
			message.Qos())
		count++
		log.Println(count)
		if count == 20000 {
			jobDoneCh <- 1
		}
		countCh <- count
	}()
}

func subInitClientOption(optionMap map[string]string) *MQTT.ClientOptions {
	opts := MQTT.NewClientOptions().AddBroker(optionMap["address"])
	opts.SetClientID(optionMap["clientId"])
	opts.SetUsername(optionMap["username"])
	opts.SetPassword(optionMap["password"])
	opts.SetDefaultPublishHandler(nil)

	return opts
}
