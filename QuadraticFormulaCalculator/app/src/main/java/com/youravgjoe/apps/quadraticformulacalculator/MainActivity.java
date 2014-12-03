package com.youravgjoe.apps.quadraticformulacalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity {

    double a;
    double b;
    double c;
    double discriminant;
    double answer1;
    double answer2;
    String answerString1;
    String answerString2;
    Button calcButton;
    Button clearButton;
    EditText aText;
    EditText bText;
    EditText cText;
    TextView answersText;
    TextView outputText;
    String tempString;
    NumberFormat formatter = new DecimalFormat("#0.00000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aText = (EditText)findViewById(R.id.editText);
        bText = (EditText)findViewById(R.id.editText2);
        cText = (EditText)findViewById(R.id.editText3);
        answersText = (TextView)findViewById(R.id.textView5);
        outputText = (TextView)findViewById(R.id.textView6);
        calcButton = (Button)findViewById(R.id.calc_button);
        clearButton = (Button)findViewById(R.id.clear_button);
        calcButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        try{
                            a = Double.parseDouble(aText.getText().toString());
                            b = Double.parseDouble(bText.getText().toString());
                            c = Double.parseDouble(cText.getText().toString());


                            //Check to see if discriminant is positive, negative, or zero.
                            discriminant = ((b * b) - 4 * a * c);


                            if (discriminant < 0 )
                            {
                                answersText.setText("No Real Answer");
                                outputText.setText("");
                            }
                            else if (discriminant == 0)
                            {
                                answer1 = (-b - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
                                answersText.setText("Only one answer:");
                                outputText.setText(formatter.format(answer1));
                            }
                            else
                            {
                                //Calculate output values with Quadratic Formula:

                                answersText.setText("Answers:");
                                //calculate negative value first
                                answer1 = (-b - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
                                //now the positive one
                                answer2 = (-b + Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
                                //output it to the editText5
                                outputText.setText(formatter.format(answer1) + ", " + formatter.format(answer2));
                            }
                        } catch (NumberFormatException e) {
                            answersText.setText("Answers:");
                            outputText.setText("");
                            Toast.makeText(getApplicationContext(), "Error: Blank text field.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        clearButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        aText.setText("");
                        bText.setText("");
                        cText.setText("");
                        answersText.setText("Answers:");
                        outputText.setText("");
                        aText.requestFocus();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {

            ActionAbout();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ActionAbout()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setTitle("About");
        dlgAlert.setMessage("This app was created by youravgjoe for Professor Weber's PHYS 2210 course.\n\nNo matter how hard quadratic equations may be, " +
                "I will not be held responsible for any bad grades received as a result of using this app. I've double-checked my math. Better double-check yours.\n\n" +
                "Please email any bug reports to joe@youravgjoe.com.\n\nCopyright 2014 youravgjoe");
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
//                finish();
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}


