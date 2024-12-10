import { Link } from "react-router-dom";
import { Bell, Hotel, BadgeDollarSign, Home, ListOrdered, User, UserRoundCog, ShoppingBasket } from "lucide-react"
import "./styles.scss";


const AdminMenu = () => {
    return(
        <div className="menu">
            <div className="item">
                <span className="title">MAIN</span>
                <Link to="/admin" className="listItem">
                    <Home/>
                    <span className="listItemTitle">Home</span>
                </Link>
                <Link to="/admin/users" className="listItem">
                    <User/>
                    <span className="listItemTitle">Users</span>
                </Link>
                <Link to="/admin/products" className="listItem">
                    <ShoppingBasket />
                    <span className="listItemTitle">Products</span>
                </Link>
                <Link to="/admin/orders" className="listItem">
                    <ListOrdered />
                    <span className="listItemTitle">Orders</span>
                </Link>
                <Link to="/admin/discount" className="listItem">
                    <BadgeDollarSign />
                    <span className="listItemTitle">Discount</span>
                </Link>
                <Link to="/admin/employee" className="listItem">
                    <UserRoundCog />
                    <span className="listItemTitle">Staff</span>
                </Link>
                <Link to="/admin/branch" className="listItem">
                    <Hotel />
                    <span className="listItemTitle">Branch</span>
                </Link>
                <Link to="/admin/notification" className="listItem">
                    <Bell />
                    <span className="listItemTitle">Notification</span>
                </Link>
            </div>
        </div>
    )
}

export default AdminMenu;