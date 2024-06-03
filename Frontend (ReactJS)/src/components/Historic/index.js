import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './index.scss';
import AnimatedLetters from '../AnimatedLetters';

const Historic = () => {
  const [error, setError] = useState(null);
  const [examData, setExamData] = useState([]);
  const [selectedExams, setSelectedExams] = useState([]);
  const [letterClass, setLetterClass] = useState("text-animate");
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedYears, setSelectedYears] = useState([]);
  const isMobileView = window.innerWidth <= 1150;

  const yearOptions = [
    { label: "W2024", value: "w2024" },
    { label: "F2023", value: "f2023" },
    { label: "W2023", value: "w2023" },
    { label: "F2022", value: "f2022" }
  ];

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const className = params.get('names');
    const years = params.get('years');

    if (years) {
      setSelectedYears(years.split(','));
    }

    const timer = setTimeout(() => {
      setLetterClass("text-animate-hover");
    }, 1);

    if (className && years) {
      axios
        .get(`https://mcgillexams-5294e99e879f.herokuapp.com/api/v1/historic-exams/historic?names=${encodeURIComponent(className)}&years=${encodeURIComponent(years)}`)
        .then((response) => {
          setExamData(response.data);
        })
        .catch((error) => {
          setError(error);
        });
    } else {
    }

    return () => {
      clearTimeout(timer);
    };
  }, []);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleYearChange = (event) => {
    const value = event.target.value;
    setSelectedYears((prevSelectedYears) =>
      prevSelectedYears.includes(value)
        ? prevSelectedYears.filter((year) => year !== value)
        : [...prevSelectedYears, value]
    );
  };

  const handleGoButtonClick = () => {
    const selectedYearsString = selectedYears.join(',');

    setTimeout(() => {
      window.location.href = `/historical?names=${encodeURIComponent(searchQuery)}&years=${encodeURIComponent(selectedYearsString)}`;
    }, 1);
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleGoButtonClick();
    }
  };



  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <>
    <div className="container data-page">
      <div className="text-zone">
        <h1>
          <br />
          <br />
          <AnimatedLetters letterClass={letterClass} strArray={"Historical Exams".split("")} idx={10} />
        </h1>
        {examData.length === 0 ? (
          <h2>Please search for one or more courses and select at least one year!</h2>
        ): (
          <div className="table-container">
          <table>
            <thead>
              <tr>
                {isMobileView ? (
                  <>
                    <th>Course</th>
                    <th>Year</th>
                    <th>Exam Start Time</th>
                    <th>Exam End Time</th>
                  </>
                ): (
                  <>
                    <th>Course</th>
                    <th>Year</th>
                    <th>Exam Start Time</th>
                    <th>Exam End Time</th>
                    <th>Exam Type</th>
                    <th>Building</th>
                    <th>Room</th>
                    <th>Rows</th>
                    <th>Row Start</th>
                    <th>Row End</th>
                  </>
                )}
              </tr>
            </thead>
            <tbody>
              {examData.map((exam) => (
                <tr key={`${exam.course}-${exam.section}`}>
                  <td>{exam.course}</td>
                  <td>{exam.year}</td>
                  <td>{exam.exam_start_time}</td>
                  <td>{exam.exam_end_time}</td>
                  {!isMobileView && (
                    <>
                      <td>{exam.exam_type}</td>
                      <td>{exam.building}</td>
                      <td>{exam.room}</td>
                      <td>{exam.rows_from}</td>
                      <td>{exam.row_start}</td>
                      <td>{exam.row_end}</td>
                    </>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        )}
        <h2>
          <div className="searchContainer">
            <div className="searchBox">
              <input
                className="searchInput"
                type="text"
                placeholder="Search for more exams"
                value={searchQuery}
                onChange={handleSearchChange}
                onKeyPress={handleKeyPress}
              />
             <button class="searchButton" href="#" onClick={handleGoButtonClick}>
              <svg xmlns="http://www.w3.org/2000/svg" width="29" height="29" viewBox="0 0 29 29" fill="none">
              <g clip-path="url(#clip0_2_17)">
              <g filter="url(#filter0_d_2_17)">
              <path d="M23.7953 23.9182L19.0585 19.1814M19.0585 19.1814C19.8188 18.4211 20.4219 17.5185 20.8333 16.5251C21.2448 15.5318 21.4566 14.4671 21.4566 13.3919C21.4566 12.3167 21.2448 11.252 20.8333 10.2587C20.4219 9.2653 19.8188 8.36271 19.0585 7.60242C18.2982 6.84214 17.3956 6.23905 16.4022 5.82759C15.4089 5.41612 14.3442 5.20435 13.269 5.20435C12.1938 5.20435 11.1291 5.41612 10.1358 5.82759C9.1424 6.23905 8.23981 6.84214 7.47953 7.60242C5.94407 9.13789 5.08145 11.2204 5.08145 13.3919C5.08145 15.5634 5.94407 17.6459 7.47953 19.1814C9.01499 20.7168 11.0975 21.5794 13.269 21.5794C15.4405 21.5794 17.523 20.7168 19.0585 19.1814Z" stroke="white" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" shape-rendering="crispEdges"></path>
              </g>
              </g>
              <defs>
              <filter id="filter0_d_2_17" x="-0.418549" y="3.70435" width="29.7139" height="29.7139" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
              <feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood>
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
            <br/>
            <br/>
            <div className="yearSelectBox">
              {yearOptions.map((year) => (
                <label key={year.value}>
                  <input
                    type="checkbox"
                    value={year.value}
                    checked={selectedYears.includes(year.value)}
                    onChange={handleYearChange}
                  />
                  {year.label}
                </label>
              ))}
            </div>
          </div>
        </h2>
      </div>
    </div>
    </>
  );
};

export default Historic;
