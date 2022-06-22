package ir.matiran.cryptocurrency;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.matiran.cryptocurrency.databinding.FragmentListBinding;
import ir.matiran.cryptocurrency.model.ProfileListInfo;
import ir.matiran.cryptocurrency.viewmodel.ApiInterface;
import ir.matiran.cryptocurrency.viewmodel.CurrencyListAdapter;
import ir.matiran.cryptocurrency.viewmodel.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;


public class ListFragment extends Fragment implements CurrencyListAdapter.ItemClickListener{

    FragmentListBinding binding;
    private final LinkedList<String> CurrencyList = new LinkedList<>();
    private ProfileListInfo moneyprofilelistinfo;
    private SweetAlertDialog pDialog;
    private CurrencyListAdapter mAdapter;
    NavController navController = null;


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
                startTask();
            }
        });

        mAdapter = new CurrencyListAdapter((MainActivity)requireContext(), CurrencyList, this);
        binding.currencyrecviewRv.setAdapter(mAdapter);
        binding.currencyrecviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        startTask();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        navController = Navigation.findNavController(v);
    }

    public void startTask() {
        // Loading Dialog
        pDialog = new SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#dc86a5"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);

        pDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ProfileListInfo> call = service.getCurrencyApi();
        call.enqueue(new Callback<ProfileListInfo>() {
            @Override
            public void onResponse(Call<ProfileListInfo> call, retrofit2.Response<ProfileListInfo> response) {

                pDialog.cancel();
                Log.wtf("all list", "Get all list: " + response);
                moneyprofilelistinfo = response.body();
                mAdapter.setProfileList(moneyprofilelistinfo);

                // Put initial data into the word list.
                for (int i = 0; i < 33; i++) {
                    CurrencyList.addLast((moneyprofilelistinfo.GetCoins(i)).toString());
                }

                // for updating recycleview
                mAdapter.notifyDataSetChanged();
                Log.wtf("again", "ok again call " );
            }

            @Override
            public void onFailure(Call<ProfileListInfo> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                pDialog.cancel();

            }
        });
    }

    @Override
    public void onItemClick(String CurrencyList) {

        Bundle bundle = new Bundle();
        bundle.putString("CurrencyName", CurrencyList.toString());
        bundle.putSerializable("CurrencyInfo", moneyprofilelistinfo);

        navController.navigate(R.id.action_mainFragment_to_fragment_second, bundle);
    }
}