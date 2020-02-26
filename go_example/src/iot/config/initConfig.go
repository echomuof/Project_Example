package config

import (
	"errors"
	"github.com/spf13/viper"
	"go_example/src/iot/utils"
	"strconv"
	"strings"
)

type EmqxConfig struct {
	Address  string
	Username string
	Password string
	ClientId string
}

type TopicInfo struct {
	TopicList []string
	QosList   []int
}

type RabbitConfig struct {
	Address  string
	Username string
	Password string
}

func LoadConfig() (eConfig EmqxConfig, topicInfo TopicInfo, rabbitConfig RabbitConfig) {
	path := "src/iot/config"
	v := viper.New()
	v.SetConfigName("config")
	v.AddConfigPath(path)
	err := v.ReadInConfig()
	utils.FailOnError("failed to read config", err)

	eConfig = loadEmqxConfig(v)
	topicInfo = loadTopicInfo(v)
	rabbitConfig = loadRabbitConfig(v)

	return eConfig, topicInfo, rabbitConfig
}

func loadRabbitConfig(v *viper.Viper) RabbitConfig {
	return RabbitConfig{
		Address:  v.GetString("rabbitmq.address"),
		Username: v.GetString("rabbitmq.username"),
		Password: v.GetString("rabbitmq.password"),
	}
}

func loadTopicInfo(v *viper.Viper) TopicInfo {
	topics := v.GetString("topicInfo.topicList")
	qoss := v.GetString("topicInfo.qosList")
	topicList := strings.Split(topics, ",")
	tmpQosList := strings.Split(qoss, ",")
	if len(topicList) != len(tmpQosList) {
		utils.FailOnError("topic and qos 's count should be same", errors.New("count not sum"))
	}
	var qosList []int
	for _, s := range tmpQosList {
		i, err := strconv.Atoi(s)
		utils.FailOnError("failed string format num", err)
		qosList = append(qosList, i)
	}
	info := TopicInfo{
		TopicList: topicList,
		QosList:   qosList,
	}
	return info
}

func loadEmqxConfig(v *viper.Viper) EmqxConfig {
	eConfig := EmqxConfig{
		Address:  v.GetString("emqx.address"),
		Username: v.GetString("emqx.username"),
		Password: v.GetString("emqx.password"),
		ClientId: v.GetString("emqx.clientId"),
	}
	return eConfig
}
