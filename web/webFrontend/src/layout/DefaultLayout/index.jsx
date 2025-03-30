import React from "react";
import Header from "../Header";
import Footer from "../Footer";
import ProductCard from "../../pages/ProductCard";
import { Outlet } from "react-router-dom";
import styles from "./styles.module.scss";
import className from "classnames/bind";
const cx = className.bind(styles);

const DefaultLayout = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Header />
      <main
        className={cx("wrapper_body", "flex-grow-1")}
        style={{ marginTop: "0px" }}
      >
        {/* {children} */}
        <Outlet />
      </main>

      <Footer />
    </div>
  );
};

export default DefaultLayout;
