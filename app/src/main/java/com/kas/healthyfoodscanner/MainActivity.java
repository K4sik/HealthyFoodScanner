package com.kas.healthyfoodscanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.kas.healthyfoodscanner.databinding.ActivityMainBinding;
import com.kas.healthyfoodscanner.ui.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("text/plain");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "roman.kasarab.pz.2018@lpnu.ua" });
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Application");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "Best app :)");

                startActivity(Intent.createChooser(mailIntent, "Send Email"));
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_write_to_us, R.id.nav_rate_us, R.id.nav_about_us)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
//        databaseHelper.addProduct(
//                "4823000913691",
//                "Світоч",
//                "Вафлі «Артек» класичний",
//                "Цукор білий, вафельні листи (борошно пшеничне в/г, сіль кухонна, емульгатор лецитин соєвий, сода харчова), жир кондитерський пальмовий, какао-порошок, молоко сухе знежирене, ароматизатор ванілін натурально-ідентичний.",
//                516,
//                50,
//                21,
//                5,
//                27,
//                65);
//        databaseHelper.addProduct(
//                "4823097405673",
//                "Щедро",
//                "Майонез Провансаль",
//                "Олія соняшникова рафінована дезодорована, вода питна, цукор, жовток яєчний ферментований рідкий (2,2%), оцет спиртовий, сіль кухонна, жовток яєчний сухий (0,5%), консервант: сорбат калію, ароматизатор «Гірчиця», стабілізатор: комплексна суміш (камідь гуарова та камідь ксантанова), олія гірчична нерафінована (0,01%), антиоксидант (Е385), барвник бета-каротин.",
//                616,
//                50,
//                21,
//                1,
//                67,
//                3);
//        databaseHelper.addProduct(
//                "4820001313741",
//                "Торчин",
//                "Кетчуп до шашлику",
//                "Вода питна, паста томатна 19%, цукор, крохмаль модифікований кукурудзяний, сіль кухонна, регулятор кислотності (кислота оцтова), містить суміш спецій або екстрактів карі, екстракт перцю чилі або перцю кайєнського.",
//                100,
//                50,
//                21,
//                1,
//                67,
//                24);
//        databaseHelper.addProduct(
//                "4820196521389",
//                "Хуторок",
//                "Пельмені, Равіолі «Застольні» заморожені",
//                "Борошно пшеничне вищого сорта, вода питна, м'со куряче (не менше 68%), цибуля ріпчаста свіжа, рослинний соєвий білок, сіль кухонна, яєчний порошок, пряно-ароматична суміш, перець чорний мелений.",
//                2645,
//                50,
//                21,
//                9,
//                8,
//                34);
//        databaseHelper.addProduct(
//                "4823077624285",
//                "Roshen",
//                "Набір цукерок шоколадних та пралінових «Roshen chocolateria»",
//                "Цукор, какао терте, еквівалент какао-масла (негідрогенізовані пальмова олія, олія ши, олія ілліпе), какао-масло, молоко сухе незбиране 4.3%, вершки сухі 4.1%, молоко сухе знежирене 2.7%, какао-порошок зі зниженим вмістом жиру, ядра мигдалю смажені терті 2.5%, жири рослинні (негідрогенізовані пальмоядрова, пальмова олії), патока, ядра горіхів фундука смажені терті 2%, сироватка молочна суха, наповнювач полідекстроза, ядра мигдалю смажені подрібнені 0.7%, кунжут 0.6%, жир молочний вологоутримуючий агент сорбітовий сироп, ром, емульгатори (соєвий лецитин, Е472с, Е476), вино десертне 0.3%, бренді 0.2%, спирт, ароматизатори (\"Ваніль\", \"Марципан\", \"Вино\", \"Лимон-свіжа м'ята\" (натуральний), ванілін, \"Ром\"), сік лаймовий концентрований, кориця, барвник бета-каротин.",
//                535,
//                50,
//                21,
//                9,
//                8,
//                34);
//        databaseHelper.addProduct(
//                "7622210832498",
//                "Milka",
//                "Шоколад молочний «Мілка»з круглим печивом «Орео» з начинкою зі смаком ванілі",
//                "цукор, борошно пшеничне, какао-масло, молоко сухе знежирене, жир рослинний (олія пальмова), какао терте, сироватка суха молочна, жир молочний, какао-порошок зі зниженим вмістом жиру, сироп глюкозно-фруктозний, крохмаль пшеничний, емульгатор (лецитин соєвий), сіль кухонна, розпушувачі (гідрокарбонати натрію та амонію), регулятори кислотності (гідрокарбонат калію, Е524), ароматизатори.",
//                519,
//                50,
//                21,
//                6,
//                27,
//                61);
//        databaseHelper.addProduct(
//                "4820077250087",
//                "Мівіна",
//                "Картопляне пюре зі смаком вершків",
//                "картопляні пластівці, замінник сухих врешків на основі пальмової/кокосової олії, сіль кухонна, підсилювач смаку (глутамат натрію, інозинат натрію та гуанілат натрію), цукор, рослинна олія на основі пальмової/кокосової олії, харчовий ароматизатор «вершки».",
//                519,
//                50,
//                21,
//                6,
//                27,
//                161);
//        databaseHelper.addProduct(
//                "4820077250001",
//                "McDonald's",
//                "Біг Мак",
//                "Булка Біг Мак: борошно пшеничне вищого сорту, вода, цукор, кунжут, олія соняшникова, дріжджі, сіль кухонна, клейковина пшенична, поліпшувачі хлібопекарські (борошно пшеничне, емульгатори: Е 472е, Е412, розпушувач: карбонат кальцію, технологічні допоміжні засоби: ферменти), антиоксидант: аскорбінова кислота. Гамбургер з яловичини: яловичина 100%. Cоус Біг Мак: вода питна, олія ріпакова, оцет спиртовий, огірки мариновані, глюкозно-фруктозний сироп, цукор, крохмаль кукурудзяний модифікований, жовток яєчний, прянощі (містять гірчицю), сіль кухонна, загущувач Е415, ароматизатори натуральні, екстракт дріжджів, стабілізатор Е509). Сир плавлений Чеддер: сир Чеддер (51%), вода питна, сир твердий (9%), масло вершкове, молоко сухе знежирене, емульгуюча сіль (Е331), ароматизатор натуральний «Сир», молочний білок, сіль кухонна, барвники (бета каротин, екстракт паприки), регулятор кислотності (Е330), антиспікаючий агент лецитин соняшниковий. Салат Айсберг. Мариновані огірки: огірки різані, вода питна, сіль кухонна, регулятор кислотності - оцтова кислота, стабілізатор - кальцій хлористий, консервант – сорбат калію, ароматизатор. Цубуля. Приправа до м'яса: сіль кухонна йодована, перець чорний молотий, екстракт чорного перцю.\n" +
//                        "Містить: Яйця, Злаки, що містять глютен, Молоко (включаючи лактозу), Гірчиця, Насіння кунжуту",
//                2012,
//                100,
//                85,
//                125,
//                100,
//                360);
//        databaseHelper.addProduct(
//                "4820077250002",
//                "McDonald's",
//                "Чікен МакНагетс",
//                "Кулінарний виріб з м`яса птиці \"Чікен МкНагетс\". філе куряче (46%), вода питна, рослинні олії (соняшникова, ріпакова), борошно кукурудзяне, борошно пшеничне, крохмаль картопляний, крупа манна, сухарі панірувальні (містять борошно), ароматизатори (містять пшеницю), клейковина пшенична, цукор, спеції (містять селеру), розпушувач (карбонат натрію), екстракт спецій (містить селеру).\n" +
//                        "Містить: Злаки, що містять глютен, селера",
//                1542,
//                321,
//                85,
//                140,
//                200,
//                120);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}