import { Link } from "react-router-dom";
import { BadgeDollarSign, Home, ListOrdered, User, ShoppingBasket } from "lucide-react"
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
                <Link to="/admin/users" className="listItem">
                    <User/>
                    <span className="listItemTitle">Users</span>
                </Link>
            </div>
        </div>
    )
}

export default AdminMenu;