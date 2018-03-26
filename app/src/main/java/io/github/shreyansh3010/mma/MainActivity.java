package io.github.shreyansh3010.mma;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner dropdown_age = (Spinner) findViewById(R.id.age);
        String[] items_age = new String[]{"Less than 10", "10 – 15", "16 – 40", "Above 40"};
        ArrayAdapter<String> adapter_age = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items_age);
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_age.setAdapter(adapter_age);

        final Spinner dropdown_training = (Spinner) findViewById(R.id.training);
        String[] items_training = new String[]{"Maui Thai", "Jujutsu", "Karate", "Judo"};
        ArrayAdapter<String> adapter_training = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items_training);
        adapter_training.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_training.setAdapter(adapter_training);

        final Spinner dropdown_exercise = (Spinner) findViewById(R.id.exercise);
        String[] items_exercise = new String[]{"Cardio", "Strength", "Stamina", "Muscle"};
        ArrayAdapter<String> adapter_exercise = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items_exercise);
        adapter_exercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_exercise.setAdapter(adapter_exercise);



        final RadioButton male_btn = (RadioButton) findViewById(R.id.male);
        male_btn.setChecked(true);
        final RadioButton female_btn = (RadioButton) findViewById(R.id.female);
        female_btn.setChecked(false);

        final LinearLayout male_box = (LinearLayout) findViewById(R.id.male_box);
        LinearLayout female_box = (LinearLayout) findViewById(R.id.female_box);

        male_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_btn.setChecked(true);
                female_btn.setChecked(false);
            }
        });
        female_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_btn.setChecked(false);
                female_btn.setChecked(true);
            }
        });

        final CheckBox swim = (CheckBox) findViewById(R.id.swimming);
        final CheckBox run = (CheckBox) findViewById(R.id.running);
        final CheckBox football = (CheckBox) findViewById(R.id.football);
        final CheckBox rugby = (CheckBox) findViewById(R.id.rugby);
        final CheckBox dance = (CheckBox) findViewById(R.id.dance);


        Button submit = (Button) findViewById(R.id.submit_btn);

        final EditText name = (EditText) findViewById(R.id.name);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        final TextView qr_text = (TextView) dialog.findViewById(R.id.final_id);
        final ImageView qr_img = (ImageView) dialog.findViewById(R.id.image);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                if(name.getText().toString() != "" && (swim.isChecked() || run.isChecked() || football.isChecked() || rugby.isChecked() || dance.isChecked())){
                    int hr=0,a=0,b=0,c=0,d=0,p=0,q=0,r=0,s=0,v=0,w=0,x=0,y=0,z=0;
                    Random rand = new Random();
                    int num = rand.nextInt(9000000) + 1000000;

                    if(dropdown_age.getSelectedItem().toString()== "Less than 10" || dropdown_age.getSelectedItem().toString()== "10 – 15" || female_btn.isChecked()){
                        hr=1;
                    }
                    else if(dropdown_age.getSelectedItem().toString()== "16 – 40" || dropdown_age.getSelectedItem().toString()== "Above 40" || male_btn.isChecked()){
                        hr=2;
                    }
                    switch(dropdown_training.getSelectedItem().toString()){
                        case "Maui Thai":
                            a=hr;
                            break;
                        case "Jujutsu":
                            b=hr;
                            break;
                        case "Karate":
                            c=hr;
                            break;
                        case "Judo":
                            d=hr;
                            break;
                    }
                    switch (dropdown_exercise.getSelectedItem().toString()){
                        case "Cardio":
                            p=1;
                            break;
                        case "Strength":
                            q=1;
                            break;
                        case "Stamina":
                            r=1;
                            break;
                        case "Muscle":
                            s=1;
                            break;
                    }
                    if(swim.isChecked()){
                        v=1;
                    }
                    if(run.isChecked()){
                        w=1;
                    }
                    if(football.isChecked()){
                        x=1;
                    }
                    if(rugby.isChecked()){
                        y=1;
                    }
                    if(dance.isChecked()){
                        z=1;
                    }
                    String final_qr = Integer.toString(num) + " " + "A:"+Integer.toString(a)+ " " + "B:"+Integer.toString(b)+ " " + "C:"+Integer.toString(c)+ " " + "D:"+Integer.toString(d)+ " " + "P:"+Integer.toString(p)+ " " + "Q:"+Integer.toString(q)+ " " + "R:"+Integer.toString(r)+ " " + "S:"+Integer.toString(s)+ " " + "V:"+Integer.toString(v)+ " " + "W:"+Integer.toString(w)+ " " + "X:"+Integer.toString(x)+ " " + "Y:"+Integer.toString(y)+ " " + "Z:"+Integer.toString(z);
                    qr_text.setText(final_qr);
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try{
                        BitMatrix bitMatrix = multiFormatWriter.encode(final_qr, BarcodeFormat.QR_CODE,200,200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        qr_img.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        e.printStackTrace();
                    }
                    dialog.show();
                }
                else {
                    Toast.makeText(MainActivity.this,"All fields required !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
