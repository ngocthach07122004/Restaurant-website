import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import Signin from "../pages/Signin";
import Signup from "../pages/Signup";
import Menu from "../pages/Menu";
import DefaultLayout from "../layout/DefaultLayout";
import AdminLayout from "../layout/Admin/AdminLayout/AdminLayout";
import AdminDashboard from "../pages/Admin/Dashboard";
import ProductManagement from "../pages/Admin/ProductManagement"
import Users from "../pages/Admin/Users";
import OrderManagement from "../pages/Admin/OrderManagement";
import Cart from "../pages/Cart";
import ProductDetail from "../pages/ProductDetail";
import Profile from "../pages/UserProfile";
import Discount from "../pages/Admin/Discount"
import Employee from "../pages/Admin/Employee"
import BranchManagement from "../pages/Admin/BranchManagement"
import Success from "../pages/Message/Success";
import Branch from "../pages/Branch";
import Recruit from "../pages/Recruit";

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
            {path: "profile/", element: <Profile />},
            {path: "menu/", element: <Menu />},
            {path: "menu/details/:id", element: <ProductDetail />},
            {path: "cart/", element: <Cart />},
            {path: "successPayment/", element: <Success />},
            {path: "branch/", element: <Branch />},
            {path: "careers/", element: <Recruit />}
        ],
    },
    {
        path: "/admin/*",
        element: <AdminLayout />,
        // loader: checkAdminAuth, // Secure admin routes
        children: [
            {index: true, element: <AdminDashboard />},
            // {path: "users/", element: <AdminDashboard />},
            {path: "products/", element: <ProductManagement />},
            {path: "users/", element: <Users />},
            {path: "orders/", element: <OrderManagement />},
            {path: "discount/", element: <Discount />},
            {path: "employee/", element: <Employee />},
            {path: "branch/", element: <BranchManagement />},,
        ]
    }
]);

export default router;