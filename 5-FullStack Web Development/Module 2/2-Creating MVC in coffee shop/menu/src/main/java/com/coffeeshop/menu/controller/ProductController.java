package com.coffeeshop.menu.controller;

import org.springframework.ui.Model;
import com.coffeeshop.menu.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/products") // THis mean that all URLs start with http://localhost:8080/products/
public class ProductController {

    private List<Product> productsList = List.of(
            new Product(1, "Espresso", 2.50),
            new Product(2, "Latte", 3.50),
            new Product(3, "Croissant", 2.00),
            new Product(4, "Chocolate Muffin", 2.25),
            new Product(5, "Americano", 2.75)
    );
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to Coffee Shop";
    }

    @RequestMapping("/list")
    public String listProducts(Model productListModel) {
        productListModel.addAttribute("products", productsList);
       return "menu";
    }

    @RequestMapping("/details/{id}")
    @ResponseBody
    public String getProductDetailsByID(@PathVariable int id) {
        for (Product product : productsList) {
            if (product.getId() == id) {
                return "<strong>Request Product Details: </strong> <hr> Product ID: " + product.getId() + "<br> Name: " + product.getName() + "<br> Price: $" + product.getPrice() + "<br>";
            }
        }
        return "Product not found!";
    }
}
