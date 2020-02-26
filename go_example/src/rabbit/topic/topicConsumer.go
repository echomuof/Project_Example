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

	//声明交换机
	err = channel.ExchangeDeclare(
		"iot-test-exchange",
		"topic",
		false,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to declare exchange", err)

	//声明一个队列
	queue, err := channel.QueueDeclare(
		"iot-queue1",
		false,
		false,
		false,
		false,
		nil,
	)
	utils.FailOnError("failed to declare queue", err)

	//将队列和交换机进行绑定
	err = channel.QueueBind(
		queue.Name,
		"iotTest.#",
		"iot-test-exchange",
		false,
		nil,
	)
	utils.FailOnError("failed to bind", err)

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
			log.Println("Consumer receive: ", string(msg.Body), msg.RoutingKey)
		}
	}()

	select {}
}
