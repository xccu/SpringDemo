package com.example;

import java.util.*;

public class Boss{

    private Car car = new Car();

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return this.car;
    }

    //list类型
    /*private List favorites = new ArrayList();

    public List getFavorites()  {
        return favorites;
    }

    public void setFavorites(List favorites)  {
        this.favorites = favorites;
    }*/

    //Set类型
    private Set favorites;

    public Set getFavorites() {
        return favorites;
    }

    public void setFavorites(Set favorites){
        this.favorites = favorites;
    }

    //Map类型
    /*private Map favorites;

    public Map getFavorites() {
        return favorites;
    }

    public void setFavorites(Map  favorites){
        this.favorites = favorites;
    }*/

    //Properties类型
    /*private Properties favorites;

    public Properties getFavorites() {
        return favorites;
    }


    public void setFavorites(Properties  favorites){
        this.favorites = favorites;
    }*/


    //强类型集合
    /*private Map<Integer, String> favorites;

    public Map getFavorites() {
        return favorites;
    }

    public void setFavorites(Map  favorites){
        this.favorites = favorites;
    }*/


    @Override
    public String toString() {
        return "Boss{car=" + car + '}';
    }
}