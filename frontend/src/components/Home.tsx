import type { FC } from "react";
import Card from "./Card";
import Navbar from "./Navbar";

const Home: FC = () => {
    return (
        <div className="home-page">
            <Navbar />
            <main className="content-wrapper">
                <Card />
            </main>
        </div>
    )
}

export default Home;