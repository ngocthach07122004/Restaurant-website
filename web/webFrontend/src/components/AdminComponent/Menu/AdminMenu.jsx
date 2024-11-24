import { Link } from "react-router-dom";
import { Home, User } from "lucide-react"
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
                <Link to="/admin" className="listItem">
                    <Home/>
                    <span className="listItemTitle">Home</span>
                </Link>
                <Link to="/admin/users" className="listItem">
                    <User/>
                    <span className="listItemTitle">Users</span>
                </Link>
                <Link to="/admin" className="listItem">
                    <Home/>
                    <span className="listItemTitle">Home</span>
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