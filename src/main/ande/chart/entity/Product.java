package com.ande.chart.entity;

/**
 * @author lyx
 *
 * 2015-7-7上午11:10:11
 *
 *springechart.com.entity.Sales
 */
public class Product {

    private String name;    //类别名称
    private int sale;        //销量

    public Product(String name, int sale) {
        this.name = name;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSale() {
        return sale;
    }
    public void setSale(int num) {
        this.sale = sale;
    }

}
