import { Route, Routes } from 'react-router-dom';
import './App.scss';
import Layout from "./components/Layout";
import React from 'react';
import Home from "./components/Home";
import Search from './components/Search';
import DataHandling from './components/DataHandling';
import Calendar from './components/Calendar';
import Contact from './components/Contact';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="search" element={<Search />} />
          <Route path="data" element={<DataHandling />} />
          <Route path="calendar" element={<Calendar />} />
          <Route path="contact" element={<Contact />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
