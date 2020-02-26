package main

import (
	"github.com/streadway/amqp"
	"go_example/src/rabbit/utils"
	"time"
)

func main() {
	connection, err := amqp.Dial("amqp://guest:guest@127.0.0.1:5672")
	utils.FailOnError("failed to get connection", err)
	defer connection.Close()

	channel, err := connection.Channel()
	utils.FailOnError("failed to get channel", err)
	defer channel.Close()

	err = channel.ExchangeDeclare(
		"myExchange",
		"topic",
		true,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to declare exchange", err)

	//定义时间格式
	var timeLayoutStr = "2006-01-02 15:04:05"
	//要发送的消息
	msg := "Hello,this is the go client for rabbitmq___" + time.Now().Format(timeLayoutStr)

	err = channel.Publish(
		"myExchange",
		"myTopic.B",
		false,
		false,
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(msg),
		},
	)
	utils.FailOnError("publish error", err)

}
