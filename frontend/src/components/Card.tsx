import axios from "axios";
import { useState, type FC } from "react";
import { BaseConstant } from "./constants/baseContants";

interface LinkEntry {
    id: number;
    shortUrl: string;
    longUrl: string;
}

const Card: FC = () => {
    const [url, setUrl] = useState<string>("");
    const [links, setLinks] = useState<LinkEntry[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const handleShorten = async () => {
        if (!url.trim()) {
            alert("Please enter a URL.");
            return;
        }

        try {
            setIsLoading(true);
            const response = await axios.post(`${BaseConstant.baseUrl}/url/shorten`, url, {
                headers: {
                    "Content-Type": "text/plain"
                }
            });

            const newEntry: LinkEntry = {
                id: Date.now(),
                shortUrl: response.data,
                longUrl: url.trim()
            };

            setLinks((previous) => [newEntry, ...previous]);
            setUrl("");
        } catch (error) {
            console.error(error);
            alert("Unable to shorten the URL. Please try again.");
        } finally {
            setIsLoading(false);
        }
    };

    const removeLink = (id: number) => {
        setLinks((previous) => previous.filter((link) => link.id !== id));
    };

    const SearchIcon = () => (
        <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <path d="M11 19a8 8 0 1 0 0-16 8 8 0 0 0 0 16Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round"/>
            <path d="m17 17 4 4" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
        </svg>
    );

    const ChartIcon = () => (
        <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <path d="M6 20V12" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M12 20V8" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M18 20V16" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M4 4h16" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
        </svg>
    );

    const TrashIcon = () => (
        <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
            <path d="M6 7h12" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M8 7V5.5C8 4.6716 8.6716 4 9.5 4h5C15.3284 4 16 4.6716 16 5.5V7" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M19 7 18.2 20.2C18.1361 21.2171 17.2468 22 16.2297 22H7.7703C6.7532 22 5.8639 21.2171 5.8 20.2L5 7" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round"/>
            <path d="M10 11v6" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
            <path d="M14 11v6" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round"/>
        </svg>
    );

    return (
        <div className="page-layout">
            <section className="hero-card">
                <div className="hero-copy">
                    <h1>Enter the long URL here...</h1>
                    <p>Shorten your links in one click and keep track of your most recent URLs.</p>
                </div>
                <div className="hero-form">
                    <input
                        className="shortener-input"
                        type="text"
                        value={url}
                        onChange={(e) => setUrl(e.target.value)}
                        placeholder="Enter the long URL here..."
                    />
                    <button
                        className="shortener-button"
                        type="button"
                        onClick={handleShorten}
                        disabled={isLoading}
                    >
                        {isLoading ? "Shortening..." : "Shorten"}
                    </button>
                </div>
            </section>

            <section className="recent-card">
                <div className="recent-header">
                    <div>
                        <h2>Recent Links</h2>
                        <p>Keep an eye on the URLs you shortened most recently.</p>
                    </div>
                </div>

                {links.length === 0 ? (
                    <div className="empty-state">No links yet. Shorten a URL to see it here.</div>
                ) : (
                    <div className="recent-list">
                        {links.map((link) => (
                            <div key={link.id} className="link-item">
                                <div className="link-details">
                                    <div className="link-title">{link.shortUrl}</div>
                                    <div className="link-subtitle">{link.longUrl}</div>
                                </div>
                                <div className="link-actions">
                                    <button
                                        type="button"
                                        className="icon-button"
                                        title="Open link"
                                        onClick={() => window.open(link.shortUrl, "_blank")}
                                    >
                                        <SearchIcon />
                                    </button>
                                    <button type="button" className="icon-button" title="View stats">
                                        <ChartIcon />
                                    </button>
                                    <button
                                        type="button"
                                        className="icon-button"
                                        title="Remove link"
                                        onClick={() => removeLink(link.id)}
                                    >
                                        <TrashIcon />
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </section>
        </div>
    );
};

export default Card;