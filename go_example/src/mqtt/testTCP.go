package main

import (
	"bytes"
	"log"
	"net"
)

func main() {
	var listener net.Listener
	if l, err := net.Listen("tcp", "127.0.0.1:8999"); err != nil {
		log.Println("listen tcp error: ", err)
	} else {
		listener = l
	}
	defer listener.Close()
	for true {
		if conn, err := listener.Accept(); err != nil {
			log.Println("listener accept error:", err)
		} else {
			go msgHandler(conn)
		}
	}
}

func msgHandler(conn net.Conn) {
	defer conn.Close()
	buf := make([]byte, 1024)
	head := []byte("server receive:")
	var buffer bytes.Buffer
	for true {
		if n, err := conn.Read(buf); err != nil {
			log.Println("conn read error: ", err)
			log.Println(conn.RemoteAddr()," exit")
			break
		} else {
			buffer.Write(head)
			buffer.Write(buf[:n])
			conn.Write(buffer.Bytes())
			buffer.Reset()
		}
	}
}
