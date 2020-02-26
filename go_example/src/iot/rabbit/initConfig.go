package rabbit

import (
	"github.com/streadway/amqp"
	"go_example/src/iot/config"
	"go_example/src/iot/utils"
)

func GetChannel(config config.RabbitConfig) *amqp.Channel {
	connUrl := "amqp://" + config.Username + ":" + config.Password + "@" + config.Address
	connection, err := amqp.Dial(connUrl)
	utils.FailOnError("failed get connection", err)

	channel, err := connection.Channel()
	utils.FailOnError("failed get channel", err)

	return channel
}
