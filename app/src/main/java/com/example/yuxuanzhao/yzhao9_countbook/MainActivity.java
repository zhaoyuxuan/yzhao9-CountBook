package com.example.yuxuanzhao.yzhao9_countbook;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * MainActivity
 *
 * Version 1.0
 *
 * October 1, 2017
 *
 *
 * Copyright(c) 2017 Yuxuan Zhao. CMPUT301, University of alberta - All Rights Reserved
 * You may use distribute or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 *
 */

/**
 *
 * @author Yuxuan Zhao
 * @version 1.0
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Counter> all_info= new ArrayList<Counter>();
    public static TextView count_number;
    public  ArrayList<String> name_list = new ArrayList<String>();
    public  ArrayList<String> value_list= new ArrayList<String>();
    public ListAdapter Adapter;
    private static final String FILENAME = "file.sav";


    /**
     * it is called when the user wants to pass it the information for the new counter,
     * the alert dialog shows up after it is called
     * @param view the view that user clicks
     */
    public void newCounter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();


// 2. Chain together various setter methods to set the dialog characteristics
        builder.setView(inflater.inflate(R.layout.popup, null));



// 3. Get the AlertDialog from create()
        final AlertDialog dialog = builder.create();

        dialog.show();
        Button input_button = (Button) dialog.findViewById(R.id.input_button);
        input_button.setText("Add");

        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText name = (EditText) dialog.findViewById(R.id.input_name);
                    String Name = name.getText().toString();
                    EditText comment = (EditText) dialog.findViewById(R.id.input_comment);
                    String Comment = comment.getText().toString();
                    EditText initial_value = (EditText) dialog.findViewById(R.id.input_initial);
                    int Initial = Integer.parseInt(initial_value.getText().toString());
                    String sinitial = initial_value.getText().toString();
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat =
                            new SimpleDateFormat(pattern, new Locale("da", "DK"));
                    String date = simpleDateFormat.format(new Date());
                    name_list.add(Name);
                    value_list.add(sinitial);
                    if (name.getText().toString().trim().equalsIgnoreCase("")) {
                        name.setError("Name can not be blank");

                    }
                    else {
                        Counter counter = new Counter(Initial, Name, Comment, date);
                        all_info.add(counter);
                        add_new();
                        dialog.dismiss();
                    }
                }
                catch (NumberFormatException e){
                    EditText initial_value = (EditText) dialog.findViewById(R.id.input_initial);
                    initial_value.setError("no number is provided as intial value");
                }


            }
        });

    }

    /**
     * the function is called when a new counter is added
     */
    public void add_new(){

        final ListView list_view= (ListView) findViewById(R.id.mainList);
        count_number= (TextView)findViewById(R.id.count_number);

        count_number.setText(Integer.toString(all_info.size()));

        list_view.setAdapter(Adapter);
        saveInFile(this);


    }

    /**
     * load the saved file from local
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            all_info = gson.fromJson(in, listType);



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    /**
     * save the data locally
     * @param context context where the function is called
     */
    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(all_info,out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();

        setContentView(R.layout.activity_main);
        Adapter = new CustomAdapter(this,all_info);
        final ListView list_view= (ListView) findViewById(R.id.mainList);
        list_view.setAdapter(Adapter);
        count_number= (TextView)findViewById(R.id.count_number);
        count_number.setText(all_info.size()+"");




    }
}
