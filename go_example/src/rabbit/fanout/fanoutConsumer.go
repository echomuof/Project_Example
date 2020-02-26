package main

import (
	"github.com/streadway/amqp"
	"go_example/src/rabbit/utils"
	"log"
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

	qA, err := channel.QueueDeclare(
		"qA",
		false,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to declare queue", err)


	err = channel.QueueBind(
		qA.Name,
		"",
		"f-exchange",
		false,
		nil,
	)
	utils.FailOnError("failed to binding queue to exchange", err)


	msgs, err := channel.Consume(
		qA.Name,
		"",
		false,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to receive msg", err)
	go func() {
		for msg := range msgs {
			log.Println("fanout receive:", string(msg.Body))
		}
	}()

	select {}
}
