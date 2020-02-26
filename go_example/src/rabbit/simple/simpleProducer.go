package main

import (
	"github.com/streadway/amqp"
	"go_example/src/rabbit/utils"
	"time"
)

func main() {
	//获取连接
	conn, err := amqp.Dial("amqp://guest:guest@127.0.0.1:5672")
	utils.FailOnError("connected to rabbitmq error", err)
	defer conn.Close()

	//获取channel，后续大多是操作都是通过此channel
	channel, err := conn.Channel()
	utils.FailOnError("failed to open a channel", err)
	defer channel.Close()

	//声明一个队列
	queue, err := channel.QueueDeclare(
		"go-queue",
		false,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to declare queue", err)

	//定义时间格式
	var timeLayoutStr = "2006-01-02 15:04:05"
	//要发送的消息
	msg := "Hello,this is the go client for rabbitmq___" + time.Now().Format(timeLayoutStr)

	channel.Publish(
		"",
		queue.Name,
		false,
		false,
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(msg),
		},
	)
}
