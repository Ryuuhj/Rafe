import React, { Component } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css"
import "./css/ImageSlider.css";

import { Link } from "react-router-dom";

export default function ImageSlider(data) {
  
    const settings = {
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1
    };
    return (
      <div className="main_slider">
        <Slider {...settings}>
          {data.map((val)=>{ //오류 시, 넘어오는 값 console.log(data)로 확인
            return(
                <div className="slider_img_box">
                    <Link to={`/recipe/detail`} state={{ recipeId: val.recipeId }}>
                    <p className="slider_title">{val.recipeTitle}</p>
                    <img className="slider_img" src={val.recipeImg} alt={val.recipeTitle} style={{width:'40%'}} />
                    </Link>
                </div>
            )
          })}
        </Slider>
      </div>
    );

}