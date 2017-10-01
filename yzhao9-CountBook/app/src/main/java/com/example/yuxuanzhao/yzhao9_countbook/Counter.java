package com.example.yuxuanzhao.yzhao9_countbook;

/**
 * Created by yuxuanzhao on 2017-09-27.
 */

public class Counter {
    private int value;
    private String date;
    private String comment;
    private String name;
    private int inital_value;


    public Counter(int initial, String name, String comment, String date){
        value = initial;
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.inital_value=initial;

    }
    public int getInital_value(){
        return this.inital_value;
    }
    public void setInital_value(int inital_value){
        this.inital_value= inital_value;
    }
    public int getValue(){
        return  this.value;
    }
    public String getName(){
        return this.name;
    }
    public void setValue(int new_value){
        this.value=new_value;
        }
    public void setComment(String new_comment){
        this.comment=new_comment;
    }
    public void setName(String new_name){
        this.name=new_name;
    }
    public String getDate(){

        return this.date;
    }
    public String getComment(){
        return this.comment;
    }
    public void increment(){
        value+=1;
    }
    public void decrement(){
        if (value >0) {
            value -= 1;
        }
    }


}
