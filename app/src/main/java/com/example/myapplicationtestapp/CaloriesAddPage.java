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
    ///7up50
    private CheckBox checkBoxFor7up50;
    private Button incrementorFor7up50,decrementorFor7up50;
    private TextView up50beerShow,up50Name;
    ///7up35
    private CheckBox checkBoxFor7up35;
    private Button incrementorFor7up35,decrementorFor7up35;
    private TextView up35Show,up35Name;
    ///Apple Juice
    private CheckBox checkBoxForAppleJuice;
    private Button incrementorForAppleJuice,decrementorForAppleJuice;
    private TextView appleJuiceShow,appleJuiceName;
    ///Grape Juice
    private CheckBox checkBoxForGrapeJuice;
    private Button incrementorForGrapeJuice,decrementorForGrapeJuice;
    private TextView grapeJuiceShow,grapeJuiceName;

    //fruits
    ///apple
    private CheckBox checkBoxForApple;
    private Button incrementorForApple,decrementorForApple;
    private TextView appleShow,appleName;
    ///avacardo
    private CheckBox checkBoxForAvacardo;
    private Button incrementorForAvacardo,decrementorForAvacardo;
    private TextView avacardoShow,avacardoName;
    ///banana
    private CheckBox checkBoxForBanana;
    private Button incrementorForBanana,decrementorForBanana;
    private TextView bananaShow,bananaName;
    ///cherry
    private CheckBox checkBoxForCherry;
    private Button incrementorForCherry,decrementorForCherry;
    private TextView cherryShow,cherryName;
    ///coconut
    private CheckBox checkBoxForCoconut;
    private Button incrementorForCoconut,decrementorForCoconut;
    private TextView coconutShow,coconutName;

    //vegetables
    ///broccoli
    private CheckBox checkBoxForBroccoli;
    private Button incrementorForBroccoli,decrementorForBroccoli;
    private TextView broccoliShow,broccoliName;
    ///cabbage
    private CheckBox checkBoxForCabbage;
    private Button incrementorForCabbage,decrementorForCabbage;
    private TextView cabbageShow,cabbageName;
    ///carrot
    private CheckBox checkBoxForCarrot;
    private Button incrementorForCarrot,decrementorForCarrot;
    private TextView carrotShow,carrotName;
    ///cassava
    private CheckBox checkBoxForCassava;
    private Button incrementorForCassava,decrementorForCassava;
    private TextView cassavaShow,cassavaName;
    ///garden egg
    private CheckBox checkBoxForGardenEgg;
    private Button incrementorForGardenEgg,decrementorForGardenEgg;
    private TextView gardenEggShow,gardenEggName;

    //Grains and legumes
    ///whiteBeans
    private CheckBox checkBoxForWhiteBeans;
    private Button incrementorForWhiteBeans,decrementorForWhiteBeans;
    private TextView whiteBeansShow,whiteBeansName;
    ///brown beans
    private CheckBox checkBoxForBrownBeans;
    private Button incrementorForBrownBeans,decrementorForBrownBeans;
    private TextView brownBeansShow,brownBeansName;
    ///Oat meal
    private CheckBox checkBoxForOatMeal;
    private Button incrementorForOatMeal,decrementorForOatMeal;
    private TextView oatMealShow,oatMealName;
    ///rice white
    private CheckBox checkBoxForWhiteRice;
    private Button incrementorForWhiteRice,decrementorForWhiteRice;
    private TextView riceWhiteShow,riceWhiteName;
    ///soya beans
    private CheckBox checkBoxForSoyaBeans;
    private Button incrementorForSoyaBeans,decrementorForSoyaBeans;
    private TextView soyaBeansShow,soyaBeansName;

    //Nuts
    ///Almonds
    private CheckBox checkBoxForAlmonds;
    private Button incrementorForAlmonds,decrementorForAlmonds;
    private TextView almondsShow,almondsName;
    ///cashew
    private CheckBox checkBoxForCashew;
    private Button incrementorForCashew,decrementorForCashew;
    private TextView cashewShow,cashewName;
    ///walnut
    private CheckBox checkBoxForWalnut;
    private Button incrementorForWalnut,decrementorForWalnut;
    private TextView walnutShow,walnutName;
    ///groundnut
    private CheckBox checkBoxForGroundNut;
    private Button incrementorForGroundNut,decrementorForGroundNut;
    private TextView groundnutShow,groundnutName;
    ///coco
    private CheckBox checkBoxForCoco;
    private Button incrementorForCoco,decrementorForCoco;
    private TextView cocoShow,cocoName;

    //spreads
    ///butter
    private CheckBox checkBoxForButter;
    private Button incrementorForButter,decrementorForButter;
    private TextView butterShow,butterName;
    ///ketchup
    private CheckBox checkBoxForKetchup;
    private Button incrementorForKetchup,decrementorForKetchup;
    private TextView ketchupShow,ketchupName;
    ///Margirine
    private CheckBox checkBoxForMargirine;
    private Button incrementorForMargirine,decrementorForMargirine;
    private TextView margirineShow,margirineName;
    ///Mayoniase
    private CheckBox checkBoxForMayoniase;
    private Button incrementorForMayoniase,decrementorForMayoniase;
    private TextView mayoniaseShow,mayoniaseName;
    ///peanut
    private CheckBox checkBoxForPeanut;
    private Button incrementorForPeanut,decrementorForPeanut;
    private TextView PeanutShow,PeanutName;

    //sweeterners
    ///honey
    private CheckBox checkBoxForHoney;
    private Button incrementorForHoney,decrementorForHoney;
    private TextView honeyShow,honeyName;
    ///raisin
    private CheckBox checkBoxForRaisin;
    private Button incrementorForRaisin,decrementorForRaisin;
    private TextView raisinShow,raisinName;
    ///sugar
    private CheckBox checkBoxForSugar;
    private Button incrementorForSugar,decrementorForSugar;
    private TextView sugarShow,sugarName;
    ///sugarGranulated
    private CheckBox checkBoxForSugarGranulated;
    private Button incrementorForSugarGranulated,decrementorForSugarGranulated;
    private TextView sugarGranulatedShow,sugarGranulatedName;

    //snacks
    ///cupCake
    private CheckBox checkBoxForcupCake;
    private Button incrementorForcupCake,decrementorForcupCake;
    private TextView cupCakeShow,cupCakeName;
    ///gala
    private CheckBox checkBoxForGala;
    private Button incrementorForGala,decrementorForGala;
    private TextView GalaShow,GalaName;
    ///iceCream
    private CheckBox checkBoxForIceCream1;
    private Button incrementorForIceCream,decrementorForIceCream1;
    private TextView iceCreamShow,iceCreamName;
    ///iceCream
    private CheckBox checkBoxForiceCream2;
    private Button incrementorForiceCream2,decrementorForiceCream2;
    private TextView iceCream2Show,iceCream2Name;
    ///samosa
    private CheckBox checkBoxForSamosa;
    private Button incrementorForSamosa,decrementorForSamosa;
    private TextView samosaShow,samosaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_add_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submit =(Button) findViewById(R.id.calSubmit);
        time= (TextView) findViewById(R.id.timeCA);
        date= (TextView) findViewById(R.id.dateCA);
        foodName= (TextView) findViewById(R.id.foodName);
        foodType= (TextView) findViewById(R.id.foodType);

        //Alcoholic Beverages
        ///beer
        beerShow =(TextView) findViewById(R.id.beerShow);
        incrementorForBeer =(Button) findViewById(R.id.beerInc);
        decrementorForBeer =(Button) findViewById(R.id.beerDec);
        checkBoxForBeer =(CheckBox) findViewById(R.id.beerID);
        beerName=(TextView) findViewById(R.id.beerName);
        ///redwine
        redWineShow =(TextView) findViewById(R.id.redShow);
        incrementorForRedWine =(Button) findViewById(R.id.redInc);
        decrementorForRedWine =(Button) findViewById(R.id.redDec);
        checkBoxForRedWine =(CheckBox) findViewById(R.id.RedWineCheck);
        redWineName=(TextView) findViewById(R.id.redName);
        ///spirit
        spiritShow =(TextView) findViewById(R.id.spiritShow);
        incrementorForSpirit =(Button) findViewById(R.id.spiritInc);
        decrementorForSpirit =(Button) findViewById(R.id.spiritDec);
        checkBoxForSpirit =(CheckBox) findViewById(R.id.spiritID);
        spiritName=(TextView) findViewById(R.id.spiritName);
        ///whitewine
        whitewineShow =(TextView) findViewById(R.id.whiteShow);
        incrementorForWhiteWine =(Button) findViewById(R.id.whiteInc);
        decrementorForWhiteWine =(Button) findViewById(R.id.whiteDec);
        checkBoxForWhiteWine =(CheckBox) findViewById(R.id.whiteID);
        whitewineName=(TextView) findViewById(R.id.whiteName);

        //Drinks and Juice
        ///7up50
        up50beerShow =(TextView) findViewById(R.id.idShow);
        incrementorFor7up50 =(Button) findViewById(R.id.idInc);
        decrementorFor7up50 =(Button) findViewById(R.id.idDec);
        checkBoxFor7up50 =(CheckBox) findViewById(R.id.upID);
        up50Name=(TextView) findViewById(R.id.upName);
        ///7up35
        up35Show =(TextView) findViewById(R.id.upBigShow);
        incrementorFor7up35 =(Button) findViewById(R.id.upBigInc);
        decrementorFor7up35 =(Button) findViewById(R.id.upBigDec);
        checkBoxFor7up35 =(CheckBox) findViewById(R.id.upBigId);
        up35Name=(TextView) findViewById(R.id.upBigName);
        ///Apple Juice
        appleJuiceShow =(TextView) findViewById(R.id.appleJuiceShow);
        incrementorForAppleJuice =(Button) findViewById(R.id.appleJuiceInc);
        decrementorForAppleJuice =(Button) findViewById(R.id.appleJuiceDec);
        checkBoxForAppleJuice =(CheckBox) findViewById(R.id.appleJuice);
        appleJuiceName=(TextView) findViewById(R.id.appleJuiceName);
        ///Grape Juice
        grapeJuiceShow =(TextView) findViewById(R.id.grapeJuiceShow);
        incrementorForGrapeJuice =(Button) findViewById(R.id.grapeJuiceInc);
        decrementorForGrapeJuice =(Button) findViewById(R.id.grapeJuiceDec);
        checkBoxForGrapeJuice =(CheckBox) findViewById(R.id.grapeJuice);
        grapeJuiceName=(TextView) findViewById(R.id.grapeJuiceName);

        //fruits
        ///apple
        appleShow =(TextView) findViewById(R.id.appleShow);
        incrementorForApple =(Button) findViewById(R.id.appleInc);
        decrementorForApple =(Button) findViewById(R.id.appleDec);
        checkBoxForApple =(CheckBox) findViewById(R.id.appleId);
        appleName=(TextView) findViewById(R.id.appleName);
        ///avacardo
        avacardoShow =(TextView) findViewById(R.id.avacadoShow);
        incrementorForAvacardo =(Button) findViewById(R.id.avacadoInc);
        decrementorForAvacardo =(Button) findViewById(R.id.avacadoDec);
        checkBoxForAvacardo =(CheckBox) findViewById(R.id.avacadoId);
        avacardoName=(TextView) findViewById(R.id.avacadoName);
        ///banana
        bananaShow =(TextView) findViewById(R.id.bananaShow);
        incrementorForBanana =(Button) findViewById(R.id.bananaInc);
        decrementorForBanana =(Button) findViewById(R.id.bananaDec);
        checkBoxForBanana =(CheckBox) findViewById(R.id.bananaId);
        bananaName=(TextView) findViewById(R.id.bananaName);
        ///cherry
        cherryShow =(TextView) findViewById(R.id.cherryShow);
        incrementorForCherry =(Button) findViewById(R.id.cherryInc);
        decrementorForCherry =(Button) findViewById(R.id.cherryDec);
        checkBoxForCherry =(CheckBox) findViewById(R.id.cherryId);
        cherryName=(TextView) findViewById(R.id.cherryName);
        ///coconut
        coconutShow =(TextView) findViewById(R.id.coconutShow);
        incrementorForCoconut =(Button) findViewById(R.id.coconutInc);
        decrementorForCoconut =(Button) findViewById(R.id.coconutDec);
        checkBoxForCoconut =(CheckBox) findViewById(R.id.coconutId);
        coconutName=(TextView) findViewById(R.id.coconutName);

        //vegetables
        ///broccoli
        broccoliShow =(TextView) findViewById(R.id.brocShow);
        incrementorForBroccoli =(Button) findViewById(R.id.broInc);
        decrementorForBroccoli =(Button) findViewById(R.id.brocDec);
        checkBoxForBroccoli =(CheckBox) findViewById(R.id.broId);
        broccoliName=(TextView) findViewById(R.id.broName);
        ///cabbage
        cabbageShow =(TextView) findViewById(R.id.cabbageShow);
        incrementorForCabbage =(Button) findViewById(R.id.cabbageInc);
        decrementorForCabbage =(Button) findViewById(R.id.cabbageDec);
        checkBoxForCabbage =(CheckBox) findViewById(R.id.cabbageId);
        cabbageName=(TextView) findViewById(R.id.cabbageName);
        ///carrot
        carrotShow =(TextView) findViewById(R.id.carrotShow);
        incrementorForCarrot =(Button) findViewById(R.id.carrotInc);
        decrementorForCarrot =(Button) findViewById(R.id.carrotDec);
        checkBoxForCarrot =(CheckBox) findViewById(R.id.carrotId);
        carrotName=(TextView) findViewById(R.id.carrotName);
        ///cassava
        cassavaShow =(TextView) findViewById(R.id.cassavaShow);
        incrementorForCassava =(Button) findViewById(R.id.cassavaInc);
        decrementorForCassava =(Button) findViewById(R.id.cassavaDec);
        checkBoxForCassava =(CheckBox) findViewById(R.id.cassavaId);
        cassavaName=(TextView) findViewById(R.id.cassavaName);
        ///garden egg
        gardenEggShow =(TextView) findViewById(R.id.gardenShow);
        incrementorForGardenEgg =(Button) findViewById(R.id.gardenInc);
        decrementorForGardenEgg =(Button) findViewById(R.id.gardenDec);
        checkBoxForGardenEgg =(CheckBox) findViewById(R.id.gardenId);
        gardenEggName=(TextView) findViewById(R.id.gardenName);

        //Grains and legumes
        ///whiteBeans
        whiteBeansShow =(TextView) findViewById(R.id.beanswhiteShow);
        incrementorForWhiteBeans =(Button) findViewById(R.id.beanswhiteInc);
        decrementorForWhiteBeans =(Button) findViewById(R.id.beanswhiteDec);
        checkBoxForWhiteBeans =(CheckBox) findViewById(R.id.beanswhiteId);
        whiteBeansName=(TextView) findViewById(R.id.beansWhiteName);
        ///brown beans
        brownBeansShow =(TextView) findViewById(R.id.beansBrownShow);
        incrementorForBrownBeans =(Button) findViewById(R.id.beansBrownInc);
        decrementorForBrownBeans =(Button) findViewById(R.id.beansBrownDec);
        checkBoxForBrownBeans =(CheckBox) findViewById(R.id.beansBrownId);
        brownBeansName=(TextView) findViewById(R.id.beansBrownName);
        ///Oat meal
        oatMealShow =(TextView) findViewById(R.id.oatmealShow);
        incrementorForOatMeal =(Button) findViewById(R.id.oatmealInc);
        decrementorForOatMeal =(Button) findViewById(R.id.oatmealDec);
        checkBoxForOatMeal =(CheckBox) findViewById(R.id.oatmealId);
        oatMealName=(TextView) findViewById(R.id.oatmealName);
        ///rice white
        riceWhiteShow =(TextView) findViewById(R.id.riceWhiteShow);
        incrementorForWhiteRice =(Button) findViewById(R.id.riceWhiteInc);
        decrementorForWhiteRice =(Button) findViewById(R.id.riceWhiteDec);
        checkBoxForWhiteRice =(CheckBox) findViewById(R.id.riceWhiteId);
        riceWhiteName=(TextView) findViewById(R.id.riceWhiteName);
        ///soya beans
        soyaBeansShow =(TextView) findViewById(R.id.soyaBeansIdShow);
        incrementorForSoyaBeans =(Button) findViewById(R.id.soyaBeansInc);
        decrementorForSoyaBeans =(Button) findViewById(R.id.soyaBeansIdDec);
        checkBoxForSoyaBeans =(CheckBox) findViewById(R.id.soyaBeansId);
        soyaBeansName=(TextView) findViewById(R.id.soyaBeansName);

        //Nuts
        ///Almonds
        almondsShow =(TextView) findViewById(R.id.almondShow);
        incrementorForAlmonds =(Button) findViewById(R.id.almondInc);
        decrementorForAlmonds =(Button) findViewById(R.id.almondDec);
        checkBoxForAlmonds =(CheckBox) findViewById(R.id.almondId);
        almondsName=(TextView) findViewById(R.id.almondName);
        ///cashew
        cashewShow =(TextView) findViewById(R.id.cashewShow);
        incrementorForCashew =(Button) findViewById(R.id.cashewInc);
        decrementorForCashew =(Button) findViewById(R.id.cashewDec);
        checkBoxForCashew =(CheckBox) findViewById(R.id.cashewId);
        cashewName=(TextView) findViewById(R.id.cashewName);
        ///walnut
        walnutShow =(TextView) findViewById(R.id.walnutShow);
        incrementorForWalnut =(Button) findViewById(R.id.walnutInc);
        decrementorForWalnut =(Button) findViewById(R.id.walnutDec);
        checkBoxForWalnut =(CheckBox) findViewById(R.id.walnutId);
        walnutName=(TextView) findViewById(R.id.walnutName);
        ///groundnut
        groundnutShow =(TextView) findViewById(R.id.groundnutShow);
        incrementorForGroundNut =(Button) findViewById(R.id.groundnutInc);
        decrementorForGroundNut =(Button) findViewById(R.id.groundnutDec);
        checkBoxForGroundNut =(CheckBox) findViewById(R.id.groundnutId);
        groundnutName=(TextView) findViewById(R.id.groundnutName);
        ///coco
        cocoShow =(TextView) findViewById(R.id.cocoShow);
        incrementorForCoco =(Button) findViewById(R.id.cocoInc);
        decrementorForCoco =(Button) findViewById(R.id.cocoDec);
        checkBoxForCoco =(CheckBox) findViewById(R.id.coco1);
        cocoName=(TextView) findViewById(R.id.cocoName);

        //spreads
        ///butter
        butterShow =(TextView) findViewById(R.id.butterShow);
        incrementorForButter =(Button) findViewById(R.id.butterInc);
        decrementorForButter =(Button) findViewById(R.id.butterDec);
        checkBoxForButter =(CheckBox) findViewById(R.id.butterId);
        butterName=(TextView) findViewById(R.id.butterName);
        ///ketchup
        ketchupShow =(TextView) findViewById(R.id.KetchupShow);
        incrementorForKetchup =(Button) findViewById(R.id.KetchupInc);
        decrementorForKetchup =(Button) findViewById(R.id.KetchupDec);
        checkBoxForKetchup =(CheckBox) findViewById(R.id.KetchupId);
        ketchupName=(TextView) findViewById(R.id.KetchupName);
        ///Margirine
        margirineShow =(TextView) findViewById(R.id.MargirineShow);
        incrementorForMargirine =(Button) findViewById(R.id.MargirineInc);
        decrementorForMargirine =(Button) findViewById(R.id.MargirineDec);
        checkBoxForMargirine =(CheckBox) findViewById(R.id.MargirineId);
        margirineName=(TextView) findViewById(R.id.MargirineName);
        ///Mayoniase
        mayoniaseShow =(TextView) findViewById(R.id.MayonaiseShow);
        incrementorForMayoniase =(Button) findViewById(R.id.MayonaiseInc);
        decrementorForMayoniase =(Button) findViewById(R.id.MayonaiseDec);
        checkBoxForMayoniase =(CheckBox) findViewById(R.id.MayonaiseId);
        mayoniaseName=(TextView) findViewById(R.id.MayonaiseName);
        ///peanut
        PeanutShow =(TextView) findViewById(R.id.PeanutShow);
        incrementorForPeanut =(Button) findViewById(R.id.PeanutInc);
        decrementorForPeanut =(Button) findViewById(R.id.PeanutDec);
        checkBoxForPeanut =(CheckBox) findViewById(R.id.PeanutId);
        PeanutName=(TextView) findViewById(R.id.PeanutName);

        //sweeterners
        ///honey
        honeyShow =(TextView) findViewById(R.id.honeyShow);
        incrementorForHoney =(Button) findViewById(R.id.honeyInc);
        decrementorForHoney =(Button) findViewById(R.id.honeyDec);
        checkBoxForHoney =(CheckBox) findViewById(R.id.honeyId);
        honeyName=(TextView) findViewById(R.id.honeyName);
        ///raisin
        raisinShow =(TextView) findViewById(R.id.RaisinShow);
        incrementorForRaisin =(Button) findViewById(R.id.RaisinInc);
        decrementorForRaisin =(Button) findViewById(R.id.RaisinDec);
        checkBoxForRaisin =(CheckBox) findViewById(R.id.RaisinId);
        raisinName=(TextView) findViewById(R.id.RaisinName);
        ///sugar
        sugarShow =(TextView) findViewById(R.id.SugarShow);
        incrementorForSugar =(Button) findViewById(R.id.SugarInc);
        decrementorForSugar =(Button) findViewById(R.id.SugarDec);
        checkBoxForSugar =(CheckBox) findViewById(R.id.SugarId);
        sugarName=(TextView) findViewById(R.id.SugarName);
        ///sugarGranulated
        sugarGranulatedShow =(TextView) findViewById(R.id.GranulatedShow);
        incrementorForSugarGranulated =(Button) findViewById(R.id.GranulatedInc);
        decrementorForSugarGranulated =(Button) findViewById(R.id.GranulatedDec);
        checkBoxForSugarGranulated =(CheckBox) findViewById(R.id.GranulatedId);
        sugarGranulatedName=(TextView) findViewById(R.id.GranulatedName);

        //snacks
        ///cupCake
        cupCakeShow =(TextView) findViewById(R.id.CupcakeShow);
        incrementorForcupCake =(Button) findViewById(R.id.CupcakeInc);
        decrementorForcupCake =(Button) findViewById(R.id.CupcakeDec);
        checkBoxForcupCake =(CheckBox) findViewById(R.id.CupcakeId);
        cupCakeName=(TextView) findViewById(R.id.CupcakeName);
        ///gala
        GalaShow =(TextView) findViewById(R.id.GalaShow);
        incrementorForGala =(Button) findViewById(R.id.GalaInc);
        decrementorForGala =(Button) findViewById(R.id.GalaDec);
        checkBoxForGala =(CheckBox) findViewById(R.id.GalaId);
        GalaName=(TextView) findViewById(R.id.GalaName);
        ///iceCream
        iceCreamShow =(TextView) findViewById(R.id.ConeShow);
        incrementorForIceCream =(Button) findViewById(R.id.ConeInc);
        decrementorForIceCream1 =(Button) findViewById(R.id.ConeDec);
        checkBoxForIceCream1 =(CheckBox) findViewById(R.id.ConeId);
        iceCreamName=(TextView) findViewById(R.id.ConeName);
        ///iceCream
        iceCream2Show =(TextView) findViewById(R.id.VanillaShow);
        incrementorForiceCream2 =(Button) findViewById(R.id.VanillaInc);
        decrementorForiceCream2 =(Button) findViewById(R.id.VanillaDec);
        checkBoxForiceCream2 =(CheckBox) findViewById(R.id.VanillaId);
        iceCream2Name=(TextView) findViewById(R.id.VanillaName);
        ///samosa
        samosaShow =(TextView) findViewById(R.id.SamosaShow);
        incrementorForSamosa =(Button) findViewById(R.id.SamosaInc);
        decrementorForSamosa =(Button) findViewById(R.id.SamosaDec);
        checkBoxForSamosa =(CheckBox) findViewById(R.id.SamosaId);
        samosaName=(TextView) findViewById(R.id.SamosaName);

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

        //Alcoholic Beverages
        ///beer
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
        ///redwine
        incrementorForRedWine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                redWineShow.setText(""+i);
            }
        });
        decrementorForRedWine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                redWineShow.setText(""+i);
            }
        });
        ///spirit
        incrementorForSpirit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                spiritShow.setText(""+i);
            }
        });
        decrementorForSpirit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                spiritShow.setText(""+i);
            }
        });
        ///whitewine
        incrementorForWhiteWine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                whitewineShow.setText(""+i);
            }
        });
        decrementorForWhiteWine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                whitewineShow.setText(""+i);
            }
        });

        //Drinks and Juice
        ///7up50
        incrementorFor7up50.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                up50beerShow.setText(""+i);
            }
        });
        decrementorFor7up50.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                up50beerShow.setText(""+i);
            }
        });
        ///7up35
        incrementorFor7up35.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                up35Show.setText(""+i);
            }
        });
        decrementorFor7up35.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                up35Show.setText(""+i);
            }
        });
        ///Apple Juice
        incrementorForAppleJuice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                appleJuiceShow.setText(""+i);
            }
        });
        decrementorForAppleJuice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                appleJuiceShow.setText(""+i);
            }
        });
        ///Grape Juice
        incrementorForGrapeJuice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                grapeJuiceShow.setText(""+i);
            }
        });
        decrementorForGrapeJuice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                grapeJuiceShow.setText(""+i);
            }
        });


        //fruits
        ///apple
        incrementorForApple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                appleShow.setText(""+i);
            }
        });

        decrementorForApple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                appleShow.setText(""+i);
            }
        });
        ///avacardo
        incrementorForAvacardo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                avacardoShow.setText(""+i);
            }
        });
        decrementorForAvacardo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                avacardoShow.setText(""+i);
            }
        });
        ///banana
        incrementorForBanana.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                bananaShow.setText(""+i);
            }
        });
        decrementorForBanana.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                bananaShow.setText(""+i);
            }
        });
        ///cherry
        incrementorForCherry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cherryShow.setText(""+i);
            }
        });
        decrementorForCherry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cherryShow.setText(""+i);
            }
        });
        ///coconut
        incrementorForCoconut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                coconutShow.setText(""+i);
            }
        });
        decrementorForCoconut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                coconutShow.setText(""+i);
            }
        });


        //vegetables
        ///broccoli
        incrementorForBroccoli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                broccoliShow.setText(""+i);
            }
        });

        decrementorForBroccoli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                broccoliShow.setText(""+i);
            }
        });
        ///cabbage
        incrementorForCabbage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cabbageShow.setText(""+i);
            }
        });
        decrementorForCabbage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cabbageShow.setText(""+i);
            }
        });
        ///carrot
        incrementorForCarrot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                carrotShow.setText(""+i);
            }
        });
        decrementorForCarrot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                carrotShow.setText(""+i);
            }
        });
        ///cassava
        incrementorForCassava.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cassavaShow.setText(""+i);
            }
        });
        decrementorForCassava.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cassavaShow.setText(""+i);
            }
        });
        ///garden egg
        incrementorForGardenEgg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                gardenEggShow.setText(""+i);
            }
        });
        decrementorForGardenEgg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                gardenEggShow.setText(""+i);
            }
        });


        //Grains and legumes
        ///whiteBeans
        incrementorForWhiteBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                whiteBeansShow.setText(""+i);
            }
        });
        decrementorForWhiteBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                whiteBeansShow.setText(""+i);
            }
        });
        ///brown beans
        incrementorForBrownBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                brownBeansShow.setText(""+i);
            }
        });
        decrementorForBrownBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                brownBeansShow.setText(""+i);
            }
        });
        ///Oat meal
        incrementorForOatMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                oatMealShow.setText(""+i);
            }
        });
        decrementorForOatMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                oatMealShow.setText(""+i);
            }
        });
        ///rice white
        incrementorForWhiteRice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                riceWhiteShow.setText(""+i);
            }
        });
        decrementorForWhiteRice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                riceWhiteShow.setText(""+i);
            }
        });
        ///soya beans
        incrementorForSoyaBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                soyaBeansShow.setText(""+i);
            }
        });
        decrementorForSoyaBeans.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                soyaBeansShow.setText(""+i);
            }
        });


        //Nuts
        ///Almonds
        incrementorForAlmonds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                almondsShow.setText(""+i);
            }
        });

        decrementorForAlmonds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                almondsShow.setText(""+i);
            }
        });
        ///cashew
        incrementorForCashew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cashewShow.setText(""+i);
            }
        });
        decrementorForCashew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cashewShow.setText(""+i);
            }
        });
        ///walnut
        incrementorForWalnut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                walnutShow.setText(""+i);
            }
        });
        decrementorForWalnut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                walnutShow.setText(""+i);
            }
        });
        ///groundnut
        incrementorForGroundNut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                groundnutShow.setText(""+i);
            }
        });
        decrementorForGroundNut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                groundnutShow.setText(""+i);
            }
        });
        ///coco
        incrementorForCoco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cocoShow.setText(""+i);
            }
        });
        decrementorForCoco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cocoShow.setText(""+i);
            }
        });

        //spreads
        ///butter
        incrementorForButter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                butterShow.setText(""+i);
            }
        });
        decrementorForButter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                butterShow.setText(""+i);
            }
        });
        ///ketchup
        incrementorForKetchup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                ketchupShow.setText(""+i);
            }
        });
        decrementorForKetchup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                ketchupShow.setText(""+i);
            }
        });
        ///Margirine
        incrementorForMargirine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                margirineShow.setText(""+i);
            }
        });
        decrementorForMargirine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                margirineShow.setText(""+i);
            }
        });
        ///Mayoniase
        incrementorForMayoniase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                mayoniaseShow.setText(""+i);
            }
        });
        decrementorForMayoniase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                mayoniaseShow.setText(""+i);
            }
        });
        ///peanut
        incrementorForPeanut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                PeanutShow.setText(""+i);
            }
        });
        decrementorForPeanut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                PeanutShow.setText(""+i);
            }
        });


        //sweeterners
        ///honey
        incrementorForHoney.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                honeyShow.setText(""+i);
            }
        });
        decrementorForHoney.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                honeyShow.setText(""+i);
            }
        });
        ///raisin
        incrementorForRaisin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                raisinShow.setText(""+i);
            }
        });
        decrementorForRaisin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                raisinShow.setText(""+i);
            }
        });
        ///sugar
        incrementorForSugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                sugarShow.setText(""+i);
            }
        });
        decrementorForSugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                sugarShow.setText(""+i);
            }
        });
        ///sugarGranulated
        incrementorForSugarGranulated.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                sugarGranulatedShow.setText(""+i);
            }
        });
        decrementorForSugarGranulated.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                sugarGranulatedShow.setText(""+i);
            }
        });


        //snacks
        ///cupCake
        incrementorForcupCake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                cupCakeShow.setText(""+i);
            }
        });
        decrementorForcupCake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                cupCakeShow.setText(""+i);
            }
        });
        ///gala
        incrementorForGala.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                GalaShow.setText(""+i);
            }
        });
        decrementorForGala.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                GalaShow.setText(""+i);
            }
        });
        ///iceCream
        incrementorForIceCream.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                iceCreamShow.setText(""+i);
            }
        });
        decrementorForIceCream1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                iceCreamShow.setText(""+i);
            }
        });
        ///iceCream
        incrementorForiceCream2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                iceCream2Show.setText(""+i);
            }
        });
        decrementorForiceCream2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                iceCream2Show.setText(""+i);
            }
        });
        ///samosa
        incrementorForSamosa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i++;
                samosaShow.setText(""+i);
            }
        });
        decrementorForSamosa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(i>0){
                    i--;
                }else {
                }
                samosaShow.setText(""+i);
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

                if(checkBoxForRedWine.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(redWineShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+redWineName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForSpirit.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(spiritShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+spiritName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForWhiteWine.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(whitewineShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+whitewineName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxFor7up50.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(up50beerShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+up50Name.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxFor7up35.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(up35Show.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+up35Name.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForAppleJuice.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(appleJuiceShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+appleJuiceName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForGrapeJuice.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(grapeJuiceShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+grapeJuiceName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForApple.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(appleShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+appleName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForAvacardo.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(avacardoShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+avacardoName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForBanana.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(bananaShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+bananaName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCherry.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cherryShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cherryName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCoconut.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(coconutShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+coconutName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForBroccoli.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(broccoliShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+broccoliName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCabbage.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cabbageShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cabbageName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCarrot.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(carrotShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+carrotName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCassava.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cassavaShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cassavaName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForGardenEgg.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(gardenEggShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+gardenEggName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForWhiteBeans.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(whiteBeansShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+whiteBeansName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForBrownBeans.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(brownBeansShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+brownBeansName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForOatMeal.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(oatMealShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+oatMealName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForWhiteRice.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(riceWhiteShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+riceWhiteName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForSoyaBeans.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(soyaBeansShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+soyaBeansName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForAlmonds.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(almondsShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+almondsName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCashew.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cashewShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cashewName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForWalnut.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(walnutShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+walnutName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForGroundNut.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(groundnutShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+groundnutName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForCoco.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cocoShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cocoName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForKetchup.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(ketchupShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+ketchupName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForMargirine.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(margirineShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+margirineName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForMayoniase.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(mayoniaseShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+mayoniaseName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForPeanut.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(PeanutShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+PeanutName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForHoney.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(honeyShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+honeyName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForRaisin.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(raisinShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+raisinName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForSugar.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(sugarShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+sugarName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForSugarGranulated.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(sugarGranulatedShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+sugarGranulatedName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForcupCake.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(cupCakeShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+cupCakeName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForGala.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(GalaShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+GalaName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForIceCream1.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(iceCreamShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+iceCreamName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForiceCream2.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(iceCream2Show.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+iceCream2Name.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                if(checkBoxForSamosa.isChecked()){
                    //get amount from the counter
                    int amount=Integer.parseInt(samosaShow.getText().toString());
                    //multiply amount by the calories
                    calorieAmount+=amount*100;
                    //get the name of the component associated with the calories
                    comp1.append("Name:"+samosaName.getText().toString());
                    comp1.append(",Amount:" + amount + ",");
                    comp1.append("Calories:" + calorieAmount + ".");
                }

                Calorie calorie = new Calorie();
                calorie.setTime(time.getText().toString());
                calorie.setDate(date.getText().toString());
                calorie.setFoodName(foodName.getText().toString());
                calorie.setFoodType(foodType.getText().toString());
                calorie.setCalorieUnits(calorieAmount);
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
