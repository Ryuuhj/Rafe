import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import GoogleButton from './GoogleButton';
import Base from './Base';
import User from './routes/User';
import Coffee from './routes/Coffee';
import Latte from './routes/Latte';
import Smoothie from './routes/Smoothie';
import Juice from './routes/Juice';
import Ade from './routes/Ade';
import Cocktail from './routes/Cocktail';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<GoogleButton />} />
        <Route path="main" element={<Base />} />
        <Route path="user" element={<User />} />
        <Route path='coffee' element={<Coffee />} />
        <Route path="latte" element={<Latte />} />
        <Route path="smoothie" element={<Smoothie />} />
        <Route path="Juice" element={<Juice />} />
        <Route path="ade" element={<Ade />} />
        <Route path="cocktail" element={<Cocktail />} />
      </Routes>
      </BrowserRouter>
  </React.StrictMode>
);

