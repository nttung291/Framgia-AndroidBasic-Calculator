package com.framgia.framgia_android;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFragment extends Fragment implements View.OnClickListener{

    private double num1;
    private double num2;
    private static String s = "0";
    private boolean isNumber = true;
    private TextView tvUp;
    private TextView tvDown;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setUp(view);
        return view;
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
    public void clean(){
        isNumber = true;
        num1 = 0;
        num2 = 0;
        s = "0";
        tvUp.setText("0");
        tvDown.setText("");
    }

    public void setUp(View view){
        tvUp = view.findViewById(R.id.tv_result);
        tvDown =  view.findViewById(R.id.tv_bfresult);
        tvUp.setText("0");
    }
}
