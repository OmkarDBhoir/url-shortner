import Card from "./Card";
import Navbar from "./Navbar";

const Home: React.FC = () => {
    return (
        <>
            <Navbar />
            <div className="card-wrapper">
                <Card />
            </div>
        </>
    )
}

export default Home;