package ir.matiran.cryptocurrency;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import ir.matiran.cryptocurrency.databinding.FragmentListBinding;
import ir.matiran.cryptocurrency.model.ProfileListInfo;
import ir.matiran.cryptocurrency.viewmodel.CurrencyListAdapter;
import ir.matiran.cryptocurrency.viewmodel.MoneyViewModel;


public class ListFragment extends Fragment implements CurrencyListAdapter.ItemClickListener{

    FragmentListBinding binding;
    private LinkedList<String> CurrencyList = new LinkedList<>();
    private ProfileListInfo moneyprofilelistinfo;
    private CurrencyListAdapter mAdapter;
    NavController navController = null;
    MoneyViewModel model;


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater,container,false);
        binding.refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchServer();
            }
        });

        model = new ViewModelProvider(getActivity()).get(MoneyViewModel.class);
        FillRecycleView();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        navController = Navigation.findNavController(v);
    }

    private void FillRecycleView() {

        model.getCurrency().observe(this, currency -> {
            if (currency == null)
                return;
            CurrencyList = model.getCurrency().getValue();
            mAdapter = new CurrencyListAdapter((MainActivity)requireContext(), CurrencyList, this);
            binding.currencyrecviewRv.setAdapter(mAdapter);
            binding.currencyrecviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        model.loadMoney();
        FetchServer();
    }


    public void FetchServer()
    {
        model.getTransactions().observe(this, transactions -> {
            if (transactions == null)
                return;
            moneyprofilelistinfo = model.getTransactions().getValue();
            mAdapter.setProfileList(moneyprofilelistinfo);

            mAdapter.notifyDataSetChanged();
        });

        model.fetchTransactions((MainActivity)requireContext());
    }


    @Override
    public void onItemClick(String CurrencyList) {

        Bundle bundle = new Bundle();
        bundle.putString("CurrencyName", CurrencyList.toString());
        navController.navigate(R.id.action_mainFragment_to_fragment_second, bundle);
    }
}