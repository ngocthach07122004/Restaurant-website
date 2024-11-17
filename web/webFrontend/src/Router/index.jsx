import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Signin from "../pages/Signin";
import Signup from "../pages/Signup";
import Menu from "../pages/Menu";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Home />
    },
    {
        path: "signin/",
        element: <Signin />
    },
    {
        path: "signup/",
        element: <Signup />
    },
    {
        path: "menu/",
        element: <Menu />
    },
]);

export default router;