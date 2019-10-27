package com.geekbrains;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    List<Product> productList;

    public void deleteList()
    {
        this.productList.clear();
    }

    public String getProductList() {
        String res = "";
        for (Product i : this.productList) {
            res += i.title;
        }
        return res;
    }

    public void fillArray()
    {
        this.productList=new ArrayList<Product>();

        this.productList.add(new Product(1, "Milk", 1));
        this.productList.add(new Product(2, "Bread", 2));
    }

    public Repo(){
        fillArray();
    }
}
