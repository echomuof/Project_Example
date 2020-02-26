package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {

	client := http.Client{}

	url := "http://47.93.10.179:8080/api/v3/brokers/emqx@127.0.0.1"
	request, _ := http.NewRequest("GET", url, nil)
	request.SetBasicAuth("f41f3756310c8", "MjkxODIwMDMxNDgzMDAwNDUyNDM3MDQzMTAyNjM1MTMwODI")

	response, _ := client.Do(request)

	body, _ := ioutil.ReadAll(response.Body)
	fmt.Println(string(body))
}
