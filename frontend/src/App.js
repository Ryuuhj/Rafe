import React from "react";
import {BrowserRouter, Routes, Route} from "react-router-dom";

import GoogleButton from './component/Button/GoogleButton';
import Layout from './component/Layout/Layout';

import MainPage from './pages/MainPage';

import RecipePage from './pages/RecipePage';
import CoffeePage from './pages/CoffeePage';
import LattePage from './pages/LattePage';
import SmoothiePage from './pages/SmoothiePage';
import JuicePage from './pages/JuicePage';
import AdePage from './pages/AdePage';
import CocktailPage from './pages/CocktailPage';

import MypagePage from './pages/MypagePage';
import StoragePage from './pages/StoragePage';
import IngredientDetailPage from './pages/IngredientDetailPage';
import ShoppingPage from './pages/ShoppingPage';
import LikeRecipePage from './pages/LikeRecipePage';

function App(){
    return(
    <BrowserRouter>
    <div className="App">
      <Routes>
        <Route path="/login" element={<GoogleButton />} />
        <Route path='/' element={<Layout />} >
          <Route path="main" element={<MainPage />} />

          <Route path='/recipe' element={<RecipePage />} />
          <Route path='/coffee' element={<CoffeePage />} />
          <Route path="/latte" element={<LattePage />} />
          <Route path="smoothie" element={<SmoothiePage />} />
          <Route path="/Juice" element={<JuicePage />} />
          <Route path="/ade" element={<AdePage />} />
          <Route path="/cocktail" element={<CocktailPage />} />

          <Route path="/mypage" element={<MypagePage />} />
          <Route path="/storage" element={<StoragePage />} />
          <Route path="/ingredient/detail" element={<IngredientDetailPage />} />
          <Route path="/shopping" element={<ShoppingPage />} />
          <Route path="/like_recipe" element={<LikeRecipePage />} />
        </Route>
      </Routes>
      </div>
      </BrowserRouter>
    )
}

export default App;