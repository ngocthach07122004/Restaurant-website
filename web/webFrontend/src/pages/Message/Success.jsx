import "./SuccessStyles.scss";
import { CircleCheck } from "lucide-react";
import { useNavigate } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "./SuccessStyles.scss";
const cx = classNames.bind(styles);
const Success = () => {
  const navigate = useNavigate();
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-5">
          <div className="message-box _success">
            <CircleCheck color="#28a745" size="48px" />
            <h2> Your payment was successful </h2>
            <p>
              {" "}
              Thank you for your payment. we will <br />
              be in contact with more details shortly{" "}
            </p>
          </div>
          <div className="text-center">
            <button
              className={cx("wrapper_btn_backMenu", "btn", "btn-primary")}
              onClick={() => navigate("/menu")}
            >
              Quay về trang thực đơn
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Success;
