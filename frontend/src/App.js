import React from "react";
import {BrowserRouter, Routes, Route} from "react-router-dom";

import GoogleButton from './component/Button/GoogleButton';
import Layout from './component/Layout/Layout';

import MainPage from './pages/MainPage';
import PopularRecipePage from "./pages/PopularRecipePage";

import RecipePage from './pages/RecipePage';
import RecipeDetailPage from "./pages/RecipeDetailPage";
import RecipeSearchPage from "./pages/RecipeSearchPage";
import RecipeResultPage from "./pages/ResipeResultPage";

import MypagePage from './pages/MypagePage';
import StoragePage from './pages/StoragePage';
import IngredientDetailPage from './pages/IngredientDetailPage';
import ShoppingPage from './pages/ShoppingPage';
import LikeRecipePage from './pages/LikeRecipePage';

import BeanPage from "./pages/BeanPage";
import BeanCreatePage from "./pages/BeanCreatePage";
import BeanDetailPage from "./pages/BeanDetailPage";
import BeanEditPage from "./pages/BeanEditPage";

function App(){
    return(
    <BrowserRouter>
    <div className="App">
      <Routes>
        <Route path="/login" element={<GoogleButton />} />
        <Route path='/' element={<Layout />} >
          <Route path="main" element={<MainPage />} />

          <Route path='/recipe' element={<RecipePage />} />
          <Route path="/recipe/popular" element={<PopularRecipePage />} />
          <Route path="/recipe/detail" element={<RecipeDetailPage />} />
          <Route path="/recipe/search" element={<RecipeSearchPage />} />
          <Route path="/recipe/result" element={<RecipeResultPage />} />
          

          <Route path="/mypage" element={<MypagePage />} />
          <Route path="/storage" element={<StoragePage />} />
          <Route path="/ingredient/detail" element={<IngredientDetailPage />} />
          <Route path="/shopping" element={<ShoppingPage />} />
          <Route path="/like_recipe" element={<LikeRecipePage />} />
          <Route path="/bean" element={<BeanPage />} />
          <Route path="/bean/detail" element={<BeanDetailPage />} />
          <Route path="/bean/create" element={<BeanCreatePage />} />
          <Route path="/bean/edit" element={<BeanEditPage />} />
        </Route>
      </Routes>
      </div>
      </BrowserRouter>
    )
}

export default App;