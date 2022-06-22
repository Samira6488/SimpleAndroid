package ir.matiran.cryptocurrency;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import ir.matiran.cryptocurrency.databinding.FragmentDetailBinding;
import ir.matiran.cryptocurrency.model.Profile;
import ir.matiran.cryptocurrency.model.ProfileListInfo;
import ir.matiran.cryptocurrency.viewmodel.TransactionListAdapter;


public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    TransactionListAdapter detAdapter;
    private String nameCurrency;
    private Serializable InfoCurrency;
    NavController navController = null;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater,container,false);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_fragment_second_to_mainFragment);
            }
        });


        if (getArguments() != null) {
            nameCurrency = getArguments().getString("CurrencyName");
            binding.detcurrencyTv.setText(nameCurrency);
            InfoCurrency = getArguments().getSerializable("CurrencyInfo");
        }

        // Give the RecyclerView a default layout manager.
        binding.detailrecviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Profile> OneIfo = null;
        try {
            OneIfo = getMoneyTransactions(nameCurrency);
            // Create an adapter and supply the data to be displayed.
            detAdapter = new TransactionListAdapter(requireContext(), OneIfo);
            // Connect the adapter with the RecyclerView.
            binding.detailrecviewRv.setAdapter(detAdapter);
            detAdapter.notifyDataSetChanged();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        navController = Navigation.findNavController(v);
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