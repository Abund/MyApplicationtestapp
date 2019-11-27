package com.example.myapplicationtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplicationtestapp.fragments.DatePickerFragment;
import com.example.myapplicationtestapp.fragments.TimePickerFragment;
import com.example.myapplicationtestapp.model.Calorie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class CaloriesAddPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private Button submit;
    private TextView date,time,foodName,foodType;
    private DatabaseReference myRef;
    StringBuilder comp1= new StringBuilder();
    int calorieAmount;
    int i=0;

    //Alcoholic Beverages
    ///beer
    private CheckBox checkBoxForBeer;
    private Button incrementorForBeer,decrementorForBeer;
    private TextView beerShow,beerName;
    ///redwine
    private CheckBox checkBoxForRedWine;
    private Button incrementorForRedWine,decrementorForRedWine;
    private TextView redWineShow,redWineName;
    ///spirit
    private CheckBox checkBoxForSpirit;
    private Button incrementorForSpirit,decrementorForSpirit;
    private TextView spiritShow,spiritName;
    ///whitewine
    private CheckBox checkBoxForWhiteWine;
    private Button incrementorForWhiteWine,decrementorForWhiteWine;
    private TextView whitewineShow,whitewineName;

    //Drinks and Juice

    //fruits
    //vegetables
    //Grains and legumes
    //Nuts
    //spreads
    //sweeterners
    //snacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_add_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beerShow =(TextView) findViewById(R.id.beerShow);
        incrementorForBeer =(Button) findViewById(R.id.beerInc);
        decrementorForBeer =(Button) findViewById(R.id.beerDec);
        submit =(Button) findViewById(R.id.calSubmit);
        checkBoxForBeer =(CheckBox) findViewById(R.id.beerID);
        beerName=(TextView) findViewById(R.id.beerName);
        time= (TextView) findViewById(R.id.timeCA);
        date= (TextView) findViewById(R.id.dateCA);
        foodName= (TextView) findViewById(R.id.foodName);
        foodType= (TextView) findViewById(R.id.foodType);

        //Alcoholic Beverages
        //Drinks and Juice
        //fruits
        //vegetables
        //Grains and legumes
        //Nuts
        //spreads
        //sweeterners
        //snacks

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Calorie").child(FirebaseAuth.getInstance().getUid()).push();

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        incrementorForBeer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                beerShow.setText(""+i);
            }
        });

        decrementorForBeer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                 i--;
                }else {

                }

                beerShow.setText(""+i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(checkBoxForBeer.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(beerShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+beerName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                Calorie calorie = new Calorie();
                calorie.setTime(time.getText().toString());
                calorie.setDate(date.getText().toString());
                calorie.setFoodName(foodName.getText().toString());
                calorie.setFoodType(foodType.getText().toString());
                calorie.setCalorieUnits(""+calorieAmount);
                calorie.setFoodContent(comp1.toString());

                myRef.setValue(calorie).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                });
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c =Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        //c.set(Calendar.DAY_OF_MONTH,i2);
        String currentDatePicker = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        date.setText(currentDatePicker);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        time.setText(""+i+":"+i1);
    }
}
