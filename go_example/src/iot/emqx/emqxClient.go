package emqx

import (
	MQTT "github.com/eclipse/paho.mqtt.golang"
	"go_example/src/iot/config"
	"log"
	"os"
)

func GetEmqxOption(config config.EmqxConfig, publishHandler func(client MQTT.Client,
	message MQTT.Message)) *MQTT.ClientOptions {
	opts := MQTT.NewClientOptions().AddBroker(config.Address)
	opts.SetClientID(config.ClientId)
	opts.SetUsername(config.Username)
	opts.SetPassword(config.Password)
	opts.SetDefaultPublishHandler(publishHandler)

	return opts
}

func CreateConn(ops *MQTT.ClientOptions) MQTT.Client {
	client := MQTT.NewClient(ops)
	//创建连接
	if token := client.Connect(); token.Wait() && token.Error() != nil {
		panic(token.Error())
	}
	return client
}

//qos:允许监听的qos，2则可以监听0、1、2，  1则监听0，1   0监听0
func DoSubscribe(client *MQTT.Client, topic string, qos byte) {
	if token := (*client).Subscribe(topic, qos, MsgReceivedHandler); token.Wait() && token.Error() != nil {
		log.Println(token.Error())
		os.Exit(1)
	}
	log.Println("listening to ", topic)
}

func MsgReceivedHandler(client MQTT.Client, message MQTT.Message) {
	log.Printf("Receive - [topic: %s]  [Message: %s]  [QoS: %d]\n", message.Topic(), message.Payload(),
		message.Qos())
}
