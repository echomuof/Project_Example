package main

import (
	"fmt"
	"net"
	"runtime"
)

func main() {
	listener, err := net.Listen("tcp", ":8999")
	if err != nil {
		fmt.Println("net.Listen err:", err)
		return
	}
	defer listener.Close()
	for true {
		conn, err := listener.Accept()
		if err != nil {
			fmt.Println("listener.Accept err:", err)
			return
		}
		go GetMSG(conn)
	}
}

func GetMSG(conn net.Conn) {
	fmt.Println(conn.RemoteAddr(), "连接")
	defer conn.Close()
	buf := make([]byte, 1024)
	for true {
		n, err := conn.Read(buf)
		if err != nil {
			fmt.Println("conn.Read err:", err)
			runtime.Goexit()
		}
		s := string(buf[:n])
		//fmt.Println(s)
		conn.Write([]byte("receive:" + s))
	}

}
