package utils

import (
	"fmt"
	"log"
)

func FailOnError(msg string, err error) {
	if err != nil {
		toPrint := fmt.Sprintf("%s : %s", msg, err)
		log.Fatalln(toPrint)
		panic(toPrint)
	}
}
