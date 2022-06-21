package ir.matiran.cryptocurrency;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

import ir.matiran.cryptocurrency.databinding.FragmentSecondBinding;
import ir.matiran.cryptocurrency.modle.Profile;
import ir.matiran.cryptocurrency.modle.ProfileListInfo;
import ir.matiran.cryptocurrency.viewmodles.TransactionListAdapter;


public class fragment_second extends Fragment {

    private static final String Sel_PARAM= "selparam";
    FragmentSecondBinding bindingfragsecond;

    //hint
    TransactionListAdapter detAdapter;
    private String selCurrency;
    private Serializable InfoCurrency;


    public fragment_second() {
        // Required empty public constructor
    }

    public static fragment_second newInstance(String selectedCurrency, ProfileListInfo currencyProfile) {

        fragment_second fragment = new fragment_second();
        Bundle args = new Bundle();

        args.putString(Sel_PARAM, selectedCurrency);
        args.putSerializable("CoinInfo", currencyProfile);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bindingfragsecond = FragmentSecondBinding.inflate(inflater,container,false);
        bindingfragsecond.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = ListFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment, "listFragment");

                //transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("second_fragment"));
                //transaction.add(R.id.fragment_container, fragment,"main_fragment");

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        if (getArguments() != null) {
            selCurrency = getArguments().getString(Sel_PARAM);
            InfoCurrency = getArguments().getSerializable("CoinInfo");
        }

        bindingfragsecond.detcurrencyTv.setText(selCurrency);
        // Give the RecyclerView a default layout manager.
        bindingfragsecond.detailrecviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Profile> OneIfo = null;
        try {
            OneIfo = getMoneyTransactions(selCurrency);
            // Create an adapter and supply the data to be displayed.
            detAdapter = new TransactionListAdapter(getContext(), OneIfo);
            // Connect the adapter with the RecyclerView.
            bindingfragsecond.detailrecviewRv.setAdapter(detAdapter);
            detAdapter.notifyDataSetChanged();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return bindingfragsecond.getRoot();
    }


    public ArrayList<Profile> getMoneyTransactions(String coinName) throws IllegalAccessException {
        Field[] fields = ProfileListInfo.class.getFields();

        for (int i = 0; i < fields.length; i++) {
            Log.wtf("Extract", "Field Value: " + fields[i].getName());
            if (fields[i].getName().equals(coinName)) {
                ArrayList<Profile> value = (ArrayList<Profile>) fields[i].get(InfoCurrency);
                return value;
            }
        }
        return null;
    }


}