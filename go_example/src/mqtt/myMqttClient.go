package main

import (
	"fmt"
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"os"
	"sync"
	"time"
)

func main() {
	//连接参数
	optionMap := make(map[string]string)
	optionMap["address"] = "tcp://47.93.10.179:1883"
	optionMap["clientId"] = "go-simple"
	optionMap["username"] = "admin"
	optionMap["password"] = "123"
	//设置连接参数
	opts := initClientOption(optionMap)
	client := MQTT.NewClient(opts)
	//创建连接
	if token := client.Connect(); token.Wait() && token.Error() != nil {
		panic(token.Error())
	}
	//用于保持连接
	wg := sync.WaitGroup{}
	//订阅
	go subscribe(&client, "t1/k1", 0)
	//发布消息
	go userConsole(&client, &wg)
	wg.Add(1)

	wg.Wait()
}

func userConsole(client *MQTT.Client, wg *sync.WaitGroup) {
	for true {
		var text string
		fmt.Scanln(&text)
		if text == "exit~" {
			wg.Done()
			return
		}
		//发布消息
		publish(client, "t1/k1", text, 0)
	}
}

func initClientOption(optionMap map[string]string) *MQTT.ClientOptions {
	opts := MQTT.NewClientOptions().AddBroker(optionMap["address"])
	opts.SetClientID(optionMap["clientId"])
	opts.SetUsername(optionMap["username"])
	opts.SetPassword(optionMap["password"])
	opts.SetDefaultPublishHandler(publishHandler)

	return opts
}

//qos:允许监听的qos，2则可以监听0、1、2，  1则监听0，1   0监听0
func subscribe(client *MQTT.Client, topic string, qos byte) {
	if token := (*client).Subscribe(topic, qos, msgReceivedHandler); token.Wait() && token.Error() != nil {
		fmt.Println(token.Error())
		os.Exit(1)
	}
}

func msgReceivedHandler(client MQTT.Client, message MQTT.Message) {
	fmt.Printf("Receive - [topic: %s]  [Message: %s]  [QoS: %d]\n", message.Topic(), message.Payload(), message.Qos())
}

func publish(client *MQTT.Client, topic string, msg string, qos byte) {
	token := (*client).Publish(topic, qos, false, msg)
	token.Wait()
}

//发布消息的回调方法
func publishHandler(client MQTT.Client, message MQTT.Message) {
	fmt.Println(time.Now())
	fmt.Printf("Publish - [topic: %s]  [Message: %s]  [QoS: %d]\n", message.Topic(), message.Payload(), message.Qos())
}
