package ir.matiran.cryptocurrency.viewmodel;

import android.graphics.Color;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.matiran.cryptocurrency.MainActivity;
import ir.matiran.cryptocurrency.model.Profile;
import ir.matiran.cryptocurrency.model.ProfileListInfo;
import retrofit2.Call;
import retrofit2.Callback;

public class MoneyViewModel extends ViewModel {
    private MutableLiveData<LinkedList<String>> currency;
    private MutableLiveData<ProfileListInfo> transactions;
    private MutableLiveData<ArrayList<Profile>>  currencyprofiles;

    private ProfileListInfo moneyprofilelistinfo;
    private SweetAlertDialog pDialog;


    public MoneyViewModel() {
        currency = new MutableLiveData<>();
        LinkedList<String> list = new LinkedList<>() ;
        currency.setValue(list);

        transactions = new MutableLiveData<>();
        ProfileListInfo transList = new ProfileListInfo();
        transactions.setValue(transList);

        currencyprofiles = new MutableLiveData<>();
        ArrayList<Profile> listprofile = new ArrayList<>() ;
        currencyprofiles.setValue(listprofile);

        moneyprofilelistinfo = new ProfileListInfo();
    }

    public LiveData<LinkedList<String>> getCurrency() {
        return currency;
    }
    public LiveData<ProfileListInfo> getTransactions() { return transactions; }
    public LiveData<ArrayList<Profile>> getProfiles() { return currencyprofiles; }

    public void loadMoney() {
        LinkedList<String> moneyList = currency.getValue();

        // Put initial data into the word list.
        for (int i = 0; i < 33; i++) {
            moneyList.addLast((moneyprofilelistinfo.GetCoins(i)).toString());
        }

        currency.setValue(moneyList);
    }

    public void fetchTransactions(MainActivity context)
    {
        // Loading Dialog
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#dc86a5"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiInterface service = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ProfileListInfo> call = service.getCurrencyApi();
        call.enqueue(new Callback<ProfileListInfo>() {
            @Override
            public void onResponse(Call<ProfileListInfo> call, retrofit2.Response<ProfileListInfo> response) {
                pDialog.cancel();
                Log.wtf("all list", "Get all list: " + response);

                moneyprofilelistinfo = response.body();
                transactions.setValue(moneyprofilelistinfo);

            }
            @Override
            public void onFailure(Call<ProfileListInfo> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                pDialog.cancel();
            }
        });
    }


    public void fetchProfiles(String coinName) throws IllegalAccessException {
        Field[] fields = ProfileListInfo.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            Log.wtf("Extract", "Field Value: " + fields[i].getName());
            if (fields[i].getName().equals(coinName)) {
                currencyprofiles.setValue((ArrayList<Profile>) fields[i].get(moneyprofilelistinfo));
            }
        }

    }




}

