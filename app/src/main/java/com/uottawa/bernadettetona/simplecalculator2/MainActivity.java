package com.uottawa.bernadettetona.simplecalculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resulttv,solutiontv;
    MaterialButton btn00,btn01,btn02,btn03,btn04,btn05;
    MaterialButton btn06,btn07,btn08,btn09,btnEQ,btnAdd;
    MaterialButton btnDOT,btnCE,btnDiv,btnMin,btnMul;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv= findViewById(R.id.resulttv);
        solutiontv = findViewById(R.id.solutiontv);

        assignId(btn00,R.id.btn00);
        assignId(btn01,R.id.btn01);
        assignId(btn02,R.id.btn02);
        assignId(btn03,R.id.btn03);
        assignId(btn04,R.id.btn04);
        assignId(btn05,R.id.btn05);
        assignId(btn06,R.id.btn06);
        assignId(btn07,R.id.btn07);
        assignId(btn08,R.id.btn08);
        assignId(btn09,R.id.btn09);
        assignId(btnEQ,R.id.btnEQ);
        assignId(btnAdd,R.id.btnAdd);
        assignId(btnDOT,R.id.btnDOT);
        assignId(btnCE,R.id.btnCE);
        assignId(btnDiv,R.id.btnDiv);
        assignId(btnMin,R.id.btnMin);
        assignId(btnMul,R.id.btnMul);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutiontv.getText().toString();

        if (buttonText.equals("CE")){
        solutiontv.setText("");
        resulttv.setText("0");
        return;}

        if (buttonText.equals("=")){
            solutiontv.setText(resulttv.getText());
            return;
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutiontv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resulttv.setText(finalResult);
        }


    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = (String) context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return"Err";
        }

    }
}