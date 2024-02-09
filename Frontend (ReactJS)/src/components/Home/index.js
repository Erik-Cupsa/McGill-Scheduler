import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AnimatedLetters from "../AnimatedLetters";
import "./index.scss";
import Logo from "./Logo";
const Home = () => {
    const [letterClass, setLetterClass] = useState("text-animate")
    const welcomeArray1 = "Welcome To".split("");
    const welcomeArray2 = "McGill Exam Scheduler!".split("");
    
    useEffect(() => {
        const timerId = setTimeout(() => {
            setLetterClass("text-animate-hover");
        }, 4000);

        return () => {
            clearTimeout(timerId);
        };
    }, []);

    return(
        <>
            <div className = "container home-page">
                <div className = "text-zone">
                    <h1>
                        <AnimatedLetters letterClass={letterClass} strArray={welcomeArray1} idx={12} />
                        <br />
                        <AnimatedLetters letterClass={letterClass} strArray={welcomeArray2} idx = {14} />
                    </h1>
                    <br/>
                    <h2>Add your exams to your calendar with ease! <br/> Just search, click add, and export...
                    <br/><br/><br/><Link to="/search" className="flat-button">GET STARTED</Link>
                    </h2>
                </div>
                <Logo className="Home"/>
            </div>
        </>
    )
}

export default Home