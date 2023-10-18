package com.example.mad_ca1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SecondaryActivity extends AppCompatActivity {

    private EditText productName, productPrice, productQuantity;

    private ArrayList<Product> products = new ArrayList<>();
    private Button buttonAdd, buttonView, buttonRemove, buttonCheckOut;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Intent intent = getIntent();
        String passedEmail = intent.getStringExtra("EMAIL");

        TextView passedOverEmail = findViewById(R.id.tvpassedOverEmail);
        passedOverEmail.setText(passedEmail);


        productName = findViewById(R.id.etProductName);
        productPrice = findViewById(R.id.etPrice);
        productQuantity = findViewById(R.id.etQuntity);

        buttonAdd = findViewById(R.id.buttonAddProduct);
        buttonView= findViewById(R.id.buttonViewProduct);
        buttonRemove = findViewById(R.id.buttonRemoveProduct);
        buttonCheckOut = findViewById(R.id.buttonCheckOut);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewProduct();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProduct();
            }
        });

        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCheckOut();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.only_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.item1) {
            startActivity(new Intent(SecondaryActivity.this, MainActivity.class));
        } else if (itemID == R.id.item2) {
            startActivity(new Intent(SecondaryActivity.this, ThirdActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



    public void addProduct() {

        String prodName = productName.getText().toString();
        int prodPrice = Integer.parseInt(productPrice.getText().toString());
        int prodQuantity = Integer.parseInt(productQuantity.getText().toString());

        if (products.contains(prodName)) {
            Toast.makeText(this,"Item is already in List", Toast.LENGTH_SHORT).show();
        }
        else{
            Product product = new Product(prodName, prodPrice, prodQuantity);
            products.add(product);

            productName.setText("");
            productPrice.setText("");
            productQuantity.setText("");
        }

    }

    public void viewProduct() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product List");

        ScrollView scrollView = new ScrollView(this);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (Product product : products) {
            TextView productTextView = new TextView(this);
            productTextView.setText(product.getProductName() + " - $" + product.getProductPrice() + " - " + product.getProductQuantity());
            linearLayout.addView(productTextView);
        }

        scrollView.addView(linearLayout);
        builder.setView(scrollView);
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void removeProduct() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product List");

        ScrollView scrollView = new ScrollView(this);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (Product product : products) {
            TextView productTextView = new TextView(this);
            productTextView.setText(product.getProductName() + " - $" + product.getProductPrice() + " - " + product.getProductQuantity());

            productTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    products.remove(product);
                    linearLayout.removeView(productTextView);
                }
            });

            linearLayout.addView(productTextView);
        }

        scrollView.addView(linearLayout);
        builder.setView(scrollView);
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void toCheckOut() {

        Intent intent = new Intent(SecondaryActivity.this, ThirdActivity.class);
        intent.putParcelableArrayListExtra("ARRAY", products);
        startActivity(intent);
    }
}
