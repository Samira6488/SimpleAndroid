package ir.matiran.cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(mainBinding.getRoot());
		//mainBinding.

        setContentView(R.layout.activity_main);

        Fragment fragment = ListFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "listFragment");
        //transaction.replace(    , fragment, "listFragment");
        transaction.addToBackStack(null);
        transaction.commit();



    }



}