package ir.matiran.cryptocurrency;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.matiran.cryptocurrency.databinding.FragmentMainBinding;
import ir.matiran.cryptocurrency.modle.ProfileListInfo;
import ir.matiran.cryptocurrency.viewmodles.ApiInterface;
import ir.matiran.cryptocurrency.viewmodles.CurrencyListAdapter;
import ir.matiran.cryptocurrency.viewmodles.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;


public class MainFragment extends Fragment implements CurrencyListAdapter.ItemClickListener{

    FragmentMainBinding bindingfrag;
    private final LinkedList<String> CurrencyList = new LinkedList<>();
    private ProfileListInfo moneyprofilelistinfo;
    private SweetAlertDialog pDialog;
    private CurrencyListAdapter mAdapter;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bindingfrag = FragmentMainBinding.inflate(inflater,container,false);
        bindingfrag.refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask();
            }
        });

        mAdapter = new CurrencyListAdapter((MainActivity)getContext(), CurrencyList, this);
        bindingfrag.currencyrecviewRv.setAdapter(mAdapter);
        bindingfrag.currencyrecviewRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        startTask();

        return bindingfrag.getRoot();
    }


    public void startTask() {
        // Loading Dialog
        pDialog = new SweetAlertDialog((MainActivity)getContext(), SweetAlertDialog.PROGRESS_TYPE);
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

        Fragment fragment = fragment_second.newInstance(CurrencyList.toString(), moneyprofilelistinfo);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment, "fragment_second");
        //transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("mainFragment"));
        //transaction.add(R.id.fragment_container, fragment,"fragment_second");

        transaction.addToBackStack(null);
        transaction.commit();



    }
}