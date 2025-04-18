import React, { useEffect, useState, useRef } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { Bell } from "lucide-react"; // Import CSS for styling

import className from "classnames/bind";
import styles from "./styles.module.scss";
const cx = className.bind(styles);
const notificationData = [
  {
    id: 1,
    message: "Đơn hàng của bạn đã được xác nhận",
  },
  {
    id: 2,
    message: "Đơn hàng của bạn đang được giao",
  },
  {
    id: 3,
    message: "Đơn hàng của bạn đã được giao thành công",
  },
  {
    id: 4,
    message: "Đơn hàng của bạn đã bị hủy",
  },
  {
    id: 5,
    message: "Đơn hàng của bạn đã được hoàn tiền",
  },
];
const BellNotification = ({ stateNotification, setStateBellNotification }) => {
  const [notifications, setNotifications] = useState(notificationData);

  const notificationRef = useRef(null); // Tạo ref để tham chiếu danh sách thông báo

  // Xử lý click bên ngoài
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (
        notificationRef.current &&
        !notificationRef.current.contains(event.target)
      ) {
        setStateBellNotification(false); // Ẩn danh sách thông báo nếu click bên ngoài
      }
    };

    document.addEventListener("mousedown", handleClickOutside); // Lắng nghe sự kiện click
    return () => {
      document.removeEventListener("mousedown", handleClickOutside); // Dọn dẹp sự kiện khi unmount
    };
  }, []);

  return (
    <div className={cx("wrapper_bell_notification")}>
      <Bell
        size={24}
        className={cx("", {
          activeBell: stateNotification === true,
        })}
      />
      {stateNotification && (
        <div ref={notificationRef} className={cx("wrapper_notification")}>
          <h3 className={cx("wrapper_title")}>Thông báo</h3>
          <ul className={cx("notification_list")}>
            {notifications.map((notiMessage, index) => (
              <div className={cx("wrapper_message")}>
                <li className={cx("message")} key={index}>
                  {notiMessage.message}
                </li>
                <span className={cx("wrapper_date")}>1 ngày</span>
              </div>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default BellNotification;
