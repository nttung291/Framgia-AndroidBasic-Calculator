package com.framgia.framgia_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private double num1;
    private double num2;
    private static String s = "0";
    private boolean isNumber = true;
    private TextView tvUp;
    private TextView tvDown;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##########");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUp = findViewById(R.id.tv_result);
        tvDown =  findViewById(R.id.tv_bfresult);
        loadResult();
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (button!=null){
            switch (view.getId()){
                case R.id.bt_num:
                    isNumber = true;
                    tvDown.setText(tvDown.getText() + (String) button.getText());
                    break;
                case R.id.bt_bh:
                    computeCalculation();
                    break;
                case R.id.bt_math:
                    math(button);
                    break;
                case R.id.bt_clean:
                    clean();
                    break;
                case R.id.bt_c:
                    break;
                case R.id.bt_dot:
                    tvDown.setText(tvDown.getText()+".");
                    break;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnSave) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("Result", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("TextviewUp", tvUp.getText().toString());
            editor.putString("TextviewDown", tvDown.getText().toString());
            editor.apply();
        } else if (id == R.id.mnClear) {
            isNumber = true;
            num1 = 0;
            num2 = 0;
            s = "0";
            tvUp.setText("0");
            tvDown.setText("");
        }
        return super.onOptionsItemSelected(item);
    }

    private void computeCalculation(){
        if (!tvDown.getText().equals("")){
            num1 = Double.parseDouble(tvUp.getText().toString());
            num2 = Double.parseDouble(tvDown.getText().toString());
            if (num1 == 0 && !isNumber && !s.equals("√")){
                num1 = num2;
                tvUp.setText(decimalFormat.format(num1));
                tvDown.setText("");
            } else{
                if (s.equals("+")){
                    num1+=num2;
                }
                else if (s.equals("-")){
                    num1-=num2;
                }
                else if (s.equals("x")){
                    num1 = num1*num2;
                }
                else if (s.equals(":") && num2 !=0) {
                    num1 = num1/num2;
                }
                else if (s.equals("√")) {
                    num1 = Math.sqrt(num2);
                }else if (s.equals("0")){
                    num1 = num2;
                }
                tvUp.setText(decimalFormat.format(num1));
                tvDown.setText("");
            }
        }
        s = "0";
    }


    public void math(Button button){
        isNumber = false;
        if (button.getText().equals("+")){
            computeCalculation();
            s = "+";
        }
        else if (button.getText().equals("-")){
            computeCalculation();
            s = "-";
        }
        else if (button.getText().equals("x")){
            computeCalculation();
            s = "x";
        }
        else if (button.getText().equals(":")) {
            computeCalculation();
            s = ":";
        }
        else if (button.getText().equals("√")) {
            computeCalculation();
            s = "√";
        }
        tvUp.setText(decimalFormat.format(num1));
        tvDown.setText("");
    }

    public void loadResult(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("Result", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            String rUp = sharedPreferences.getString("TextviewUp", "0");
            String rDown= sharedPreferences.getString("TextviewDown","");
            tvUp.setText(rUp);
            tvDown.setText(rDown);
            Log.d("TAG", "loadResult: AAAA" );
        }

    }
    public void clean(){
        isNumber = true;
        num1 = 0;
        num2 = 0;
        s = "0";
        tvUp.setText("0");
        tvDown.setText("");
    }
}
