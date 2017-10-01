package com.example.yuxuanzhao.yzhao9_countbook;

/**
 * Created by yuxuanzhao on 2017-09-27.
 */
/*
 * Copyright(c) 2017 Yuxuan Zhao. CMPUT301, University of alberta - All Rights Reserved
 * You may use distribute or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 */

public class Counter {
    private int value;
    private String date;
    private String comment;
    private String name;
    private int inital_value;

    /**
     * initilize an class instance that stores all the information about a counter
     * @param initial the initial value user passed in
     * @param name  the name of the counter
     * @param comment the comment of the counter
     * @param date the data that user create the counter
     */
    public Counter(int initial, String name, String comment, String date){
        value = initial;
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.inital_value=initial;

    }

    /**
     * get the inital value of current counter
     * @return the inital value
     */
    public int getInital_value(){
        return this.inital_value;
    }

    /**
     * set the initial value of the counter
     * @param inital_value
     */
    public void setInital_value(int inital_value){
        this.inital_value= inital_value;
    }

    /**
     * get the value of the counter
     * @return the value of counter
     */
    public int getValue(){
        return  this.value;
    }

    /**
     * get the name of the counter
     * @return the name of the counter
     */
    public String getName(){
        return this.name;
    }

    /**
     * update the value of the counter
     * @param new_value the new value that user changed or set
     */
    public void setValue(int new_value){
        this.value=new_value;
        }

    /**
     * set the comment for the counter
     * @param new_comment the new comment user pass in
     */
    public void setComment(String new_comment){
        this.comment=new_comment;
    }

    /**
     * set the name for the counter
     * @param new_name the new_name that user wants to update
     */
    public void setName(String new_name){
        this.name=new_name;
    }

    /**
     * get the current Date
     * @return
     */
    public String getDate(){

        return this.date;
    }

    /**
     * get the comment of the counter
     * @return
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * increment the counter value by 1
     */
    public void increment(){
        value+=1;
    }

    /**
     * decrease the counter value by 1 
     */
    public void decrement(){
        if (value >0) {
            value -= 1;
        }
    }


}
