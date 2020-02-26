package main

import (
	"fmt"
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"strconv"
	"sync"
	"time"
)

func main() {
	//连接参数
	optionMap := make(map[string]string)
	optionMap["address"] = "tcp://47.93.10.179:1883"
	optionMap["clientId"] = "pub-simple"
	optionMap["username"] = "admin"
	optionMap["password"] = "123"
	//设置连接参数
	opts := pibInitClientOption(optionMap)
	client := MQTT.NewClient(opts)
	//创建连接
	if token := client.Connect(); token.Wait() && token.Error() != nil {
		panic(token.Error())
	}
	wg := sync.WaitGroup{}
	start:=time.Now()
	//发布消息
	sendMsg(&client, &wg)
	wg.Wait()
	fmt.Println("用时：",time.Since(start))
}

func sendMsg(client *MQTT.Client, wg *sync.WaitGroup) {
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func(g int) {
			for n := 0; n < 200; n++ {
				text := "[group:" + strconv.Itoa(g) + "]__[num:" + strconv.Itoa(n) + "]"
				fmt.Println(text)
				publish(client, "test/k9", text, 0)
			}
			wg.Done()
		}(i)
	}
}

func publish(client *MQTT.Client, topic string, msg string, qos byte) {
	token := (*client).Publish(topic, qos, false, msg)
	token.Wait()
}

func pibInitClientOption(optionMap map[string]string) *MQTT.ClientOptions {
	opts := MQTT.NewClientOptions().AddBroker(optionMap["address"])
	opts.SetClientID(optionMap["clientId"])
	opts.SetUsername(optionMap["username"])
	opts.SetPassword(optionMap["password"])
	opts.SetDefaultPublishHandler(nil)
	return opts
}
