import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import './styles.scss'; // Import CSS for styling

const BellNotification = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    // Tạo kết nối WebSocket
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = new Client({
      webSocketFactory: () => socket,
      debug: function (str) {
        console.log(str); // Debug kết nối
      },
      onConnect: () => {
        console.log("Connected to WebSocket");
        // Subscribe tới topic notifications
        stompClient.subscribe("/topic/notifications", (message) => {
          const notification = JSON.parse(message.body);
          setNotifications((prev) => [...prev, notification]);
        });
      },
      onStompError: (frame) => {
        console.error("Broker reported error: ", frame.headers["message"]);
        console.error("Additional details: ", frame.body);
      },
    });

    stompClient.activate();

    return () => stompClient.deactivate(); // Dừng kết nối khi component bị huỷ
  }, []);

  return (
    <div className="bell-notification">
      <h2>Notifications</h2>
      <ul>
        {notifications.map((notification, index) => (
          <li key={index}>{notification.message}</li>
        ))}
      </ul>
    </div>
  );
};

export default BellNotification;