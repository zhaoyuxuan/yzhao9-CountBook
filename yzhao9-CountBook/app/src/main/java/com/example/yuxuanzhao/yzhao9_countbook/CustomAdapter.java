package com.example.yuxuanzhao.yzhao9_countbook;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yuxuanzhao on 2017-09-27.
 */

public class CustomAdapter extends ArrayAdapter<Counter> {

    CustomAdapter(Context context, ArrayList<Counter> all_info){
        super(context,R.layout.newcount,all_info);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        LayoutInflater Inflater= LayoutInflater.from(getContext());
        final View customview= Inflater.inflate(R.layout.newcount,parent,false);

        Button incre =(Button) customview.findViewById(R.id.increment);
        Button decre =(Button) customview.findViewById(R.id.decrement);
        Button Edit =(Button) customview.findViewById(R.id.edit_it);
        Button delete= (Button) customview.findViewById(R.id.delete_button);
        TextView count_name =(TextView) customview.findViewById(R.id.name_field);
        final TextView number =(TextView) customview.findViewById(R.id.number_field);
        final Counter counter = getItem(position);
        final TextView theDate =(TextView) customview.findViewById(R.id.date_field);

        theDate.setText(counter.getDate());
        count_name.setText(counter.getName());
        number.setText(counter.getValue()+"");

        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.increment();
                Log.d("count",counter.getValue()+"");
                number.setText(counter.getValue()+"");
                MainActivity.saveInFile(getContext());
            }
        });
        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.decrement();
                number.setText(counter.getValue() + "");
                MainActivity.saveInFile(getContext());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.all_info.remove(position);
                notifyDataSetChanged();
                MainActivity.count_number.setText(MainActivity.all_info.size()+"");
                MainActivity.saveInFile(getContext());

            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = null;
                builder.setView(view=inflater.inflate(R.layout.popup2, null));



// 3. Get the AlertDialog from create()
                final AlertDialog Dialog = builder.create();
                Dialog.show();

                Button save_button =(Button) view.findViewById(R.id.input_button);
                final EditText name_field = (EditText) view.findViewById(R.id.input_name);
                final EditText value_field = (EditText) view.findViewById(R.id.input_initial);
                final EditText value_comment = (EditText) view.findViewById(R.id.input_comment);
                final EditText value_initial = (EditText) view.findViewById(R.id.edit_initvalue);
                final Button reset_button = (Button) view.findViewById(R.id.Reset_button);
                name_field.setText(counter.getName());
                value_field.setText(Integer.toString(counter.getValue()));
                value_comment.setText(counter.getComment());
                value_initial.setText(counter.getInital_value()+"");
                reset_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value_field.setText(Integer.toString(counter.getInital_value()));
                        counter.setValue(counter.getInital_value());
                        notifyDataSetChanged();
                        MainActivity.saveInFile(getContext());
                        Dialog.dismiss();

                    }
                });

                save_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String new_name = (String) name_field.getText().toString();
                            int new_initial = Integer.parseInt(value_initial.getText().toString());
                            int new_value = Integer.parseInt(value_field.getText().toString());


                            String new_comment = (String) value_comment.getText().toString();
                            if (name_field.getText().toString().trim().equalsIgnoreCase("")) {
                                name_field.setError("Name can not be blank");

                            } else {
                                name_field.setText(new_name);
                                value_field.setText(Integer.toString(new_value));
                                value_initial.setText(Integer.toString(new_initial));
                                value_comment.setText(new_comment);
                                counter.setComment(new_comment);
                                counter.setName(new_name);
                                counter.setValue(new_value);
                                counter.setInital_value(new_initial);
                                MainActivity.saveInFile(getContext());
                                Dialog.dismiss();
                            }

                        }
                        catch(NumberFormatException e){
                            value_field.setError("value can't be none");
                        }
                    }
                });



            }
        });


        return customview;

    }


}
