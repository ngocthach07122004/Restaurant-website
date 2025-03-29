import React from "react";
import { Col, Row } from "antd";
import bk01 from "../../assets/bku01.jpeg";
import res1 from "../../assets/res1.jpg";
import res2 from "../../assets/res2.jpg";

import styles from "./styles.scss";
import classNames from "classnames/bind";
const cx = classNames.bind(styles);
const About = () => (
  <div class={cx("wrapper_about container")}>
    <div class={cx("wrapper_about_row row")}>
      <div class="col-sm-12 col-md-12 col-lg-6">
        <img className={cx("warpper_picture")} src={bk01} alt="bk01" />
      </div>
      <div class="col-sm-12 col-md-12 col-lg-6">
        <div>
          <h2>Giới thiệu về trường</h2>
          <p>
            Tên trường: Đại học Bách Khoa Đại học Quốc Gia TPHCM, tên tiếng Anh
            là Ho Chi Minh City University of Technology, viết tắt là HCMUT.
            –Địa chỉ: 268 Lý Thường Kiệt, P.14, Q.10, TP. Hồ Chí Minh Tiền thân
            của trường là Trung tâm Kỹ thuật Quốc gia được đổi tên thành Đại học
            Bách Khoa TPHCM vào năm 1976 với 5 khoa chuyên ngành: Điện – Điện
            tử, Xây Dựng, Thủy lợi, Hóa học và Cơ khí. Đến năm 1996, Đại học
            Bách Khoa chính thức trở thành thành viên của Đại học Quốc gia TP.
            Hồ Chí Minh. Nhà trường luôn phấn đấu để trở thành cơ sở đào tạo đại
            học đạt trình độ cao với đa ngành đa lĩnh vực, đồng thời là trung
            tâm nghiên cứu khoa học công nghệ hàng đầu của khu vực miền Nam nói
            riêng và của cả nước nói chung. Đội ngũ lãnh đạo nhà trường cũng
            luôn phấn đấu để đưa đại học Bách Khoa trở thành địa chỉ đáng tin
            cậy và hấp dẫn đối với những nhà đầu tư phát triển công nghệ và với
            giới doanh nghiệp trong nước cũng như quốc tế. Đại học Bách Khoa có
            đội ngũ cán bộ công nhân viên chức gồm hơn 930 người, trong đó có 9
            giáo sư, 103 phó giáo sư, hơn 338 Tiến sĩ, hơn 443 Thạc sĩ và 99
            Giảng viên có trình độ đại học. Mỗi giảng viên đều có dày dạn kinh
            nghiệm, có nhiệt huyết đối với các hoạt động đào tạo và nghiên cứu
            khoa học, chuyển giao công nghệ. Nhà trường cũng đang từng bước nâng
            cao chất lượng giảng viên để xây dựng một trường đại học vững mạnh,
            phục vụ đất nước.{" "}
          </p>
        </div>
      </div>
    </div>
    <div class={cx("wrapper_about_row row")}>
      <div class="col-sm-12 col-md-12 col-lg-6">
        <img className={cx("warpper_picture")} src={res2} alt="bk03" />
      </div>
      <div class="col-sm-12 col-md-12 col-lg-6">
        <div>
          <h2>Giới thiệu về dự án PIKABEE</h2>
          <p>
            Hệ thống quản lý nhà hàng phục vụ ăn uống tại các chi nhánh và cung
            cấp dịch vụ đặt đồ ăn giao tận nơi giúp đáp ứng nhu cầu ẩm thực của
            khách hàng một cách thuận tiện và linh hoạt. Với hệ thống nhiều chi
            nhánh trải rộng khắp các khu vực, khách hàng có thể lựa chọn dùng
            bữa tại nhà hàng hoặc đặt món trực tuyến thông qua website và ứng
            dụng. Để sử dụng dịch vụ, khách hàng cần tạo tài khoản, từ đó có thể
            đặt món, theo dõi đơn hàng, thanh toán linh hoạt bằng nhiều phương
            thức và nhận thông báo về các chương trình ưu đãi. Hệ thống cũng cho
            phép khách hàng đánh giá chất lượng dịch vụ và gửi phản hồi đến tổng
            đài để cải thiện trải nghiệm. Mỗi chi nhánh được vận hành bởi đội
            ngũ nhân viên chuyên biệt, bao gồm nhân viên quản lý, phục vụ, tổng
            đài, thu ngân, giao hàng và bếp. Nhà hàng còn ứng dụng công nghệ
            hiện đại trong quản lý đơn hàng, phân tích hành vi khách hàng và tối
            ưu quy trình vận hành, giúp nâng cao chất lượng phục vụ và mở rộng
            quy mô kinh doanh một cách hiệu quả.{" "}
          </p>
        </div>
      </div>
    </div>
  </div>
);
export default About;
