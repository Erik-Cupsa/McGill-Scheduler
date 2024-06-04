import React, { useEffect, useState } from "react";
import "./index.scss";
import AnimatedLetters from "../AnimatedLetters";

const Search = () => {
  const [letterClass, setLetterClass] = useState("text-animate");
  const [searchQuery, setSearchQuery] = useState("");
  useEffect(() => {
    const timer = setTimeout(() => {
      setLetterClass("text-animate-hover");
    }, 1);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleGoButtonClick = () => {

    if(searchQuery.indexOf(',') !== -1){
      window.location.href = `/multiselect?names=${encodeURIComponent(searchQuery)}`;
    } else {
      setTimeout(() => {
        window.location.href = `/data?name=${encodeURIComponent(searchQuery)}`;
      }, 1);
    }
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleGoButtonClick();
    }
  };

  const handleCalendarButtonClick = () => {
    window.location.href = "/calendar";
  };

  return (
    <>
      <div className="container search-page">
        <div className="text-zone">
          <h1>
            <br />
            <br />
            <AnimatedLetters letterClass={letterClass} strArray={"Search".split("")} idx={15} />
          </h1>
          <h2>
            Find your exam schedule by entering the course code (e.g. ECSE 200). Feel free to search more broadly using the relevant code for your faculty if needed!
            <br /><br />
            Additionally, you have the option to search for multiple courses simultaneously. Just separate your search queries with a comma, for example: ECSE 200, ECSE 206.
            <br /><br />
            To add an exam to your calendar, simply click the button. Once you are done, go to the Calendar section and export your personalized schedule!
            <div className="searchBox">
              <input
                className="searchInput"
                type="text"
                placeholder="Search for exams"
                value={searchQuery}
                onChange={handleSearchChange}
                onKeyPress={handleKeyPress}
              />
              <button className="searchButton" onClick={handleGoButtonClick}>
                <svg xmlns="http://www.w3.org/2000/svg" width="29" height="29" viewBox="0 0 29 29" fill="none">
                  <g clipPath="url(#clip0_2_17)">
                    <g filter="url(#filter0_d_2_17)">
                      <path d="M23.7953 23.9182L19.0585 19.1814M19.0585 19.1814C19.8188 18.4211 20.4219 17.5185 20.8333 16.5251C21.2448 15.5318 21.4566 14.4671 21.4566 13.3919C21.4566 12.3167 21.2448 11.252 20.8333 10.2587C20.4219 9.2653 19.8188 8.36271 19.0585 7.60242C18.2982 6.84214 17.3956 6.23905 16.4022 5.82759C15.4089 5.41612 14.3442 5.20435 13.269 5.20435C12.1938 5.20435 11.1291 5.41612 10.1358 5.82759C9.1424 6.23905 8.23981 6.84214 7.47953 7.60242C5.94407 9.13789 5.08145 11.2204 5.08145 13.3919C5.08145 15.5634 5.94407 17.6459 7.47953 19.1814C9.01499 20.7168 11.0975 21.5794 13.269 21.5794C15.4405 21.5794 17.523 20.7168 19.0585 19.1814Z" stroke="white" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" shapeRendering="crispEdges"></path>
                    </g>
                  </g>
                  <defs>
                    <filter id="filter0_d_2_17" x="-0.418549" y="3.70435" width="29.7139" height="29.7139" filterUnits="userSpaceOnUse" colorInterpolationFilters="sRGB">
                      <feFlood floodOpacity="0" result="BackgroundImageFix"></feFlood>
                      <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix>
                      <feOffset dy="4"></feOffset>
                      <feGaussianBlur stdDeviation="2"></feGaussianBlur>
                      <feComposite in2="hardAlpha" operator="out"></feComposite>
                      <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"></feColorMatrix>
                      <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_2_17"></feBlend>
                      <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_2_17" result="shape"></feBlend>
                    </filter>
                    <clipPath id="clip0_2_17">
                      <rect width="28.0702" height="28.0702" fill="white" transform="translate(0.403503 0.526367)"></rect>
                    </clipPath>
                  </defs>
                </svg>
              </button>
            </div>
            <a className="button" style={{ "--clr": "#7808d0" }} href="#" onClick={handleCalendarButtonClick}>
              <span className="button__icon-wrapper">
                <svg width="10" className="button__icon-svg" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 15">
                  <path fill="currentColor" d="M13.376 11.552l-.264-10.44-10.44-.24.024 2.28 6.96-.048L.2 12.56l1.488 1.488 9.432-9.432-.048 6.912 2.304.024z"></path>
                </svg>
                <svg className="button__icon-svg button__icon-svg--copy" xmlns="http://www.w3.org/2000/svg" width="10" fill="none" viewBox="0 0 14 15">
                  <path fill="currentColor" d="M13.376 11.552l-.264-10.44-10.44-.24.024 2.28 6.96-.048L.2 12.56l1.488 1.488 9.432-9.432-.048 6.912 2.304.024z"></path>
                </svg>
              </span>
              Go to Calendar
            </a>
          </h2>
        </div>
      </div>
    </>
  );
};

export default Search;
