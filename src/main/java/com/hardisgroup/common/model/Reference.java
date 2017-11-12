package com.hardisgroup.common.model;

public class Reference {

    /**
     * Color.
     */
    private String color;

    /**
     * Price.
     */
    private String price;

    /**
     * Size.
     */
    private String size;

    /**
     * reference number.
     */
    private String numReference;

    /**
     * Constructor.
     *
     * @param color        color.
     * @param price        price.
     * @param size         size.
     * @param numReference reference number.
     */
    public Reference(String color, String price, String size, String numReference) {
        this.color = color;
        this.price = price;
        this.size = size;
        this.numReference = numReference;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumReference() {
        return numReference;
    }

    public void setNumReference(String numReference) {
        this.numReference = numReference;
    }
}
