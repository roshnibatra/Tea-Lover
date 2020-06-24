package com.example.android.tealover;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.NumberFormat;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Toolbar menuToolBar = findViewById(R.id.order_summary_toolbar);
        setSupportActionBar(menuToolBar);
        getSupportActionBar().setTitle("Order Summary");

        Intent intent = getIntent();
        String teaName = intent.getStringExtra(OrderActivity.EXTRA_TEA_NAME);
        int price = intent.getIntExtra(OrderActivity.EXTRA_TOTAL_PRICE,0);
        String size = intent.getStringExtra(OrderActivity.EXTRA_SIZE);
        String milkType = intent.getStringExtra(OrderActivity.EXTRA_MILK_TYPE);
        String sugarType = intent.getStringExtra(OrderActivity.EXTRA_SUGAR_TYPE);
        int quantity = intent.getIntExtra(OrderActivity.EXTRA_QUANTITY,0);

        displayOrderSummary(teaName , price, size ,milkType ,sugarType , quantity);
    }

    private void displayOrderSummary(String teaName, int price, String size, String milkType,
                                     String sugarType, int quantity) {

        TextView teaNameTextView = findViewById(R.id.summary_tea_name);
        teaNameTextView.setText(teaName);

        TextView quantityTextView = findViewById(R.id.summary_quantity);
        quantityTextView.setText(String.valueOf(quantity));


        TextView sizeTextView = findViewById(R.id.summary_tea_size);
        sizeTextView.setText(size);

        TextView milkTypeTextView = findViewById(R.id.summary_milk_type);
        milkTypeTextView.setText(milkType);

        TextView sugarTypeTextView = findViewById(R.id.summary_sugar_amount);
        sugarTypeTextView.setText(sugarType);

        TextView priceTextView = findViewById(R.id.summary_total_price);
        String convertPrice = NumberFormat.getCurrencyInstance().format(price);
        priceTextView.setText(convertPrice);
    }

    public void sendEmail(View view) {
        String emailMessage = getString(R.string.email_message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT,
                emailMessage);

        if(intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
    }
}
