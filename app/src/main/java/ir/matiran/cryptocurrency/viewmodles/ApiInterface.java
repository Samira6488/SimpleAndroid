package ir.matiran.cryptocurrency.viewmodles;

import ir.matiran.cryptocurrency.modle.ProfileListInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    static final String BASE_URL = "https://api.exir.io/v1/trades/";

    @GET(BASE_URL)
    Call<ProfileListInfo> getCurrencyApi();
}
