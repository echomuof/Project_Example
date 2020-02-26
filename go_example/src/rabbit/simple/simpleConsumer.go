package main

import (
	"github.com/streadway/amqp"
	"go_example/src/rabbit/utils"
	"log"
)

func main() {
	connection, err := amqp.Dial("amqp://guest:guest@127.0.0.1:5672")
	utils.FailOnError("customer failed connect to rabbit", err)
	defer connection.Close()

	channel, err := connection.Channel()
	utils.FailOnError("failed to get channel", err)
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

	msgs, err := channel.Consume(
		queue.Name,
		"",
		true,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("Failed to register a consumer", err)

	go func() {
		for msg := range msgs {
			log.Println("Consumer receive: ", string(msg.Body))
		}
	}()

	select {}
}
