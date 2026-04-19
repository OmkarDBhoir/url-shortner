import type { FC } from "react";
import './styles.css';

const Navbar: FC = () => {
    return (
        <header className="navbar">
            <div className="navbar-brand">
                <div className="brand-logo">URL</div>
                <div className="brand-name">Shortener</div>
            </div>
            <div className="navbar-actions">
                <button className="ghost-button">Sign Up</button>
                <button className="primary-button">Log In</button>
            </div>
        </header>
    )
}

export default Navbar;