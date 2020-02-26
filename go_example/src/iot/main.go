package main

import (
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"go_example/src/iot/config"
	"go_example/src/iot/emqx"
	"go_example/src/iot/rabbit"
)

func main() {
	emqxConfig, info, rabbitConfig := config.LoadConfig()

	ops := emqx.GetEmqxOption(emqxConfig, nil)
	client := emqx.CreateConn(ops)

	channel := rabbit.GetChannel(rabbitConfig)
	defer channel.Close()
	rabbit.set()
	DoSub(&client, info)
	select {}
}

func DoSub(client *MQTT.Client, info config.TopicInfo) {
	count := len(info.TopicList)
	for i := 0; i < count; i++ {
		emqx.DoSubscribe(client, info.TopicList[i], byte(info.QosList[i]))
	}
}
