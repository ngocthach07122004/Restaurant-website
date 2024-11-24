import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Signin from "../pages/Signin";
import Signup from "../pages/Signup";
import Menu from "../pages/Menu";
import DefaultLayout from "../layout/DefaultLayout";
import AdminLayout from "../layout/Admin/AdminLayout/AdminLayout";
import AdminDashboard from "../pages/Admin/Dashboard";
import Users from "../pages/Admin/Users";

// const checkAdminAuth = async () => {
//     const user = await fetch('/api/check-auth'); 
//     if (!user || user.role !== 'admin') {
//       throw new Error('Unauthorized');
//     }
//     return user;
//   };

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
    },
    {
        path: "/admin/*",
        element: <AdminLayout />,
        // loader: checkAdminAuth, // Secure admin routes
        children: [
            {index: true, element: <AdminDashboard />},
            {path: "users/", element: <Users />}
        ]
    }
]);

export default router;