package com.example.tipcalculatorsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate, btnClear, btnSplit;
    EditText etBillAmount, etTipPercentage,etnpersons;
    TextView tvTotal, tvTip, tvTotalPerPerson,tvTtlpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnSplit = findViewById(R.id.btnSplit);
        etBillAmount = findViewById(R.id.etBillAmount);
        etTipPercentage = findViewById(R.id.etTipPercentage);
        etnpersons = findViewById(R.id.etnpersons);
        tvTotal = findViewById(R.id.tvTotal);
        tvTip = findViewById(R.id.tvTip);
        tvTotalPerPerson = findViewById(R.id.tvtotalPerPerson);
        tvTtlpp = findViewById(R.id.tvTtlpp);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(etBillAmount.getText());
                String s2 = String.valueOf(etTipPercentage.getText());
                // Code executed when button gets clicked
                if(s1.isEmpty() || s2.isEmpty()) {
                    if(s1.isEmpty() && s2.isEmpty())
                        Toast.makeText(MainActivity.this,"Enter Tip and Amount",Toast.LENGTH_SHORT).show();
                    else if(s1.isEmpty())
                        Toast.makeText(MainActivity.this,"Enter Amount",Toast.LENGTH_SHORT).show();
                    else if(s2.isEmpty())
                        Toast.makeText(MainActivity.this,"Enter Tip",Toast.LENGTH_SHORT).show();
                }
                else
                    onButtonClicked();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBillAmount.setText("");
                etTipPercentage.setText("");
                etnpersons.setVisibility(View.GONE);
                tvTotalPerPerson.setVisibility(View.GONE);
                tvTtlpp.setVisibility(View.GONE);
                tvTip.setText("₹ 0.00");
                tvTotal.setText("₹ 0.00");
                etnpersons.setText("");
            }
        });
        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(etBillAmount.getText());
                String s2 = String.valueOf(etTipPercentage.getText());
                String s3 = String.valueOf(etnpersons.getText());
                if(etnpersons.getVisibility()==View.GONE){
                    etnpersons.setVisibility(View.VISIBLE);
                    tvTotalPerPerson.setVisibility(View.VISIBLE);
                    tvTtlpp.setVisibility(View.VISIBLE);
                }
                if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                    if(s1.isEmpty() && s2.isEmpty())
                        Toast.makeText(MainActivity.this,"Enter Tip and Amount",Toast.LENGTH_SHORT).show();
                    else if(s1.isEmpty())
                        Toast.makeText(MainActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                    else if(s2.isEmpty())
                        Toast.makeText(MainActivity.this, "Enter Tip", Toast.LENGTH_SHORT).show();
                    else if(s3.isEmpty())
                        Toast.makeText(MainActivity.this, "Enter number of persons", Toast.LENGTH_SHORT).show();
                }
                else
                onSplitButtonClicked();
            }
        });
    }

    public void onButtonClicked(){

        DecimalFormat df = new DecimalFormat("##.00");
        //get the amounts from the EditText fields

        Double billAmount = new Double(etBillAmount.getText().toString());
        Double tipPercentage = new Double(etTipPercentage.getText().toString()) / 100;
        //Calculate Tip Amount
        Double tipAmount = billAmount * tipPercentage;
        Double totalBill = tipAmount + billAmount;

        //Display the final tip amounts that we calculated
        tvTip.setText("₹ "+df.format(tipAmount).toString());
        tvTotal.setText("₹ "+df.format(totalBill).toString());
    }
    public void onSplitButtonClicked(){
        DecimalFormat df = new DecimalFormat("##.00");
        //get the amounts from the EditText fields

        Double nofpersons = new Double(etnpersons.getText().toString());
        Double billAmount = new Double(etBillAmount.getText().toString());
        Double tipPercentage = new Double(etTipPercentage.getText().toString()) / 100;
        //Calculate Tip Amount
        Double tipAmount = billAmount * tipPercentage;
        Double totalBill = tipAmount + billAmount;
        Double totalperperson = totalBill/nofpersons;

        //Display the final tip amounts that we calculated
        tvTtlpp.setText("₹ "+df.format(totalperperson).toString());

    }
}