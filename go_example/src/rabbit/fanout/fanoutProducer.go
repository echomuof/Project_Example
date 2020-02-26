package main

import (
	"github.com/streadway/amqp"
	"go_example/src/rabbit/utils"
	"time"
)

func main() {
	conn, err := amqp.Dial("amqp://guest:guest@127.0.0.1")
	utils.FailOnError("failed to get connection", err)
	defer conn.Close()

	channel, err := conn.Channel()
	utils.FailOnError("failed to get channel", err)
	defer channel.Close()

	err = channel.ExchangeDeclare(
		"f-exchange",
		"fanout",
		false,
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

	channel.Publish(
		"f-exchange",
		"",
		false,
		false,
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(msg),
		},
	)
}
