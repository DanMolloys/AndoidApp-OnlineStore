package com.example.mad_ca1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private EditText name, cardNum, expDate, cCv;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        ArrayList<Product> receivedArrayList = intent.getParcelableArrayListExtra("ARRAY");

        //fill Number of items box
        int totalQ = addingUpTheQuantity(receivedArrayList);
        String sAddingUpTheQuantity = Integer.toString(totalQ);

        TextView totalQuantity = findViewById(R.id.tvInputtedNumOfItems);
        totalQuantity.setText(sAddingUpTheQuantity);


        //fill price box
        int totalP = addingUpThePrices(receivedArrayList);
        String sAddingUpThePrices = Integer.toString(totalP);

        TextView totalValue = findViewById(R.id.tvAddedTotalValue);
        totalValue.setText(sAddingUpThePrices);


        name = findViewById(R.id.etName);
        cardNum = findViewById(R.id.etCardNumber);
        expDate = findViewById(R.id.etExpirationDate);
        cCv = findViewById(R.id.etCCV);

        button = findViewById(R.id.buttonEnterCard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedOut();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.only_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.item1) {
            startActivity(new Intent(ThirdActivity.this, SecondaryActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkedOut() {

        String sName = name.getText().toString();
        String sCardNum = cardNum.getText().toString();
        String sExpDate = expDate.getText().toString();
        String sCCV = cCv.getText().toString();

        if (sName.isEmpty() || sCardNum.isEmpty() || sExpDate.isEmpty() || sCCV.isEmpty()) {
            Toast.makeText(this, "Must enter card info", Toast.LENGTH_SHORT).show();

        } else if (sCardNum.length() > 16 || sCardNum.length() < 16) {
            Toast.makeText(this, "Invalid card Number", Toast.LENGTH_SHORT).show();

        } else if (sCCV.length() > 3 || sCCV.length() < 3) {
            Toast.makeText(this, "Invalid CCV Number", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Thank you for shopping", Toast.LENGTH_SHORT).show();

        }

    }

    public static int addingUpThePrices(ArrayList<Product> receivedArrayList) {
        int total = 0;

        for (Product product : receivedArrayList) {
            total += product.addedTotal();
        }
        return total;
    }

    public static int addingUpTheQuantity(ArrayList<Product> receivedArrayList) {
        int total = 0;

        for (Product product : receivedArrayList) {
            total += product.getProductQuantity();
        }
        return total;
    }
}
