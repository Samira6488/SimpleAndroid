package ir.matiran.cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import ir.matiran.cryptocurrency.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(mainBinding.getRoot());
        //mainBinding.

        setContentView(R.layout.activity_main);


        Fragment fragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "mainFragment");
        //transaction.replace(    , fragment, "mainFragment");
        transaction.addToBackStack(null);
        transaction.commit();



    }



}