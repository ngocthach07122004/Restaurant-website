import { useEffect, useState } from "react";
import MainLayout from "../../layouts/MainLayout";
import axios from "axios";
import { useLocation, Link } from "react-router-dom";
import { useToastState } from "../../Recoil/Error/useToastState";
import { Helmet } from "react-helmet";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeftLong } from "@fortawesome/free-solid-svg-icons";
import { useLoadingState } from "../../Recoil/Loading/useLoadingState";

const News = () => {
    return (
        <>
            <title>{article.title + " | The Coffee Shop"}</title>
            <Link
                to="/blog"
                className="fixed top-28 left-12 lg:left-28 hidden md:flex"
            >
                <FontAwesomeIcon
                    icon={faArrowLeftLong}
                    className="text-4xl text-white hover:text-[#cda154]"
                />
            </Link>
            <div className="py-4 px-2 md:px-8 flex flex-col">
                <img
                    src={`${import.meta.env.VITE_BASE_URL}/blog/${article.image}`}
                    className="w-11/12 md:w-3/4 lg:w-1/2 rounded-xl mx-auto duration-500 object-cover h-56 md:h-[24rem]"
                    alt=""
                />
                <h1 className="text-white text-3xl text-center pt-8 pb-4">
                    {article.title}
                </h1>
                <pre className="text-white font-sans mx-auto whitespace-pre-wrap text-sm md:text-base w-5/6 md:w-2/3 lg:w-1/2 py-3">
                    {article.body}
                </pre>
            </div>
        </>
    );
}

export default News;
