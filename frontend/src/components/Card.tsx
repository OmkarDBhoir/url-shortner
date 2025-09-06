import axios from "axios";
import { useState } from "react";
import { BaseConstant } from "./constants/baseContants";


const Card: React.FC = () => {
    const [url, setUrl] = useState<string>("");
    const [shortUrl, setShortUrl] = useState<string>("");

    // Mock URL shortener (replace with API call later)
    const handleShorten = async () => {
        try {
            if (!url) {
                alert("Please enter url!");
            } else {
                const response = await axios.post(`${BaseConstant.baseUrl}/api/url/shorten`, url, {
                    headers: {
                        "Content-Type": "text/plain"
                    }
                })
                setShortUrl(response.data);
            }
        } catch (error) {
            console.error(error);
        }
    };
    return (
        <>
            <div className="card">
                <input
                    type="text"
                    value={url}
                    onChange={(e) => setUrl(e.target.value)}
                    placeholder="Enter your URL" />

                <button onClick={handleShorten}>
                    Shorten
                </button>

                {shortUrl && (
                    <div className="result">
                        Short URL:{" "}
                        <a href={shortUrl} target="_blank" rel="noopener noreferrer">
                            {shortUrl}
                        </a>
                    </div>
                )}
            </div>
        </>
    )
}

export default Card;