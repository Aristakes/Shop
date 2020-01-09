package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    EditText userNameEditText;

    String goodsName;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();
        createMap();



    }


    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Cup");
        spinnerArrayList.add("Keyboard");
        spinnerArrayList.add("Table");

        spinnerAdapter = new ArrayAdapter(this , android.R.layout.simple_spinner_item , spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("Cup" , 200.0);
        goodsMap.put("Keyboard" , 350.0);
        goodsMap.put("Table" , 500.0);
    }


    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }


    public void decreaseQuantity(View view) {
        quantity--;
        if(quantity < 0){
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName  = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName){
            case "Cup" :
                goodsImageView.setImageResource(R.drawable.code);
                break;
            case "Keyboard" :
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            case "Table" :
                goodsImageView.setImageResource(R.drawable.table);
                break;
                default:
                    goodsImageView.setImageResource(R.drawable.code);
                    break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodsName;

        order.quantity = quantity;

        order.orderPrice = price * quantity;

        Intent orderIntent = new Intent(MainActivity.this , OrderActivity.class);
        orderIntent.putExtra("userNameForIntent" , order.userName);
        orderIntent.putExtra("goodsName" , order.goodsName);
        orderIntent.putExtra("quantity" , order.quantity);
        orderIntent.putExtra("orderPrice" , order.orderPrice);
        orderIntent.putExtra("price" , price);
        startActivity(orderIntent);

    }
}
