import logo from './logo.svg';
import './App.css';
import ProductList from './ProductList';
import React, { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, createBrowserRouter, RouterProvider } from 'react-router-dom';
import axios from 'axios';
import ProductDetails from './ProductDetail';
function App() {
  const [products, setProducts] = useState([]);
  useEffect(()=>{
        axios.get('https://dummyjson.com/products')
      .then(res => setProducts(res.data.products))
      .catch(err => console.error(err));
    }, []);
  const router = createBrowserRouter([
    {
      path: "/",
      element: <ProductList products={products}/>
    },
    {
      path: "/details/:id",
      element: <ProductDetails products={products} />
    }
  ]);
  return <RouterProvider router={router} />;
}

export default App;
