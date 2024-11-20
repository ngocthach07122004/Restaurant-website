import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Signin from "../pages/Signin";
import Signup from "../pages/Signup";
import Menu from "../pages/Menu";
import DefaultLayout from "../layout/DefaultLayout";

const router = createBrowserRouter([
    {
        path: "/",
        element: <DefaultLayout />,
        children: [
            {index: true, element: <Home />},
            {path: "signin/", element: <Signin />},
            {path: "signup/", element: <Signup />},
            {path: "menu/", element: <Menu />}
        ],
        // element: <Home />
    },
    // {
    //     path: "signin/",
    //     element: <Signin />
    // },
    // {
    //     path: "signup/",
    //     element: <Signup />
    // },
    // {
    //     path: "menu/",
    //     element: <Menu />
    // },
]);

export default router;