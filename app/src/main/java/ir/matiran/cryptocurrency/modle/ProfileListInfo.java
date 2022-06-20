package ir.matiran.cryptocurrency.modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class ProfileListInfo implements Serializable {

    @Expose
    @SerializedName("xrp-usdt")
    public ArrayList<Profile> xrp_usdt;

    @Expose
    @SerializedName("bnb-irt")
    public ArrayList<Profile> bnb_irt;

    @Expose
    @SerializedName("shib-irt")
    public ArrayList<Profile> shib_irt;

    @Expose
    @SerializedName("dot-irt")
    public ArrayList<Profile> dot_irt;

    @Expose
    @SerializedName("btc-usdt")
    public ArrayList<Profile> btc_usdt;

    @Expose
    @SerializedName("usdt-irt")
    public ArrayList<Profile> usdt_irt;

    @Expose
    @SerializedName("btc-irt")
    public ArrayList<Profile> btc_irt;

    @Expose
    @SerializedName("avax-irt")
    public ArrayList<Profile> avax_irt;

    @Expose
    @SerializedName("eth-usdt")
    public ArrayList<Profile> eth_usdt;

    @Expose
    @SerializedName("doge-irt")
    public ArrayList<Profile> doge_irt;

    @Expose
    @SerializedName("eth-irt")
    public ArrayList<Profile> eth_irt;

    @Expose
    @SerializedName("xtz-irt")
    public ArrayList<Profile> xtz_irt;

    @Expose
    @SerializedName("bch-irt")
    public ArrayList<Profile> bch_irt;

    @Expose
    @SerializedName("xlm-usdt")
    public ArrayList<Profile> xlm_usdt;

    @Expose
    @SerializedName("ltc-irt")
    public ArrayList<Profile> ltc_irt;

    @Expose
    @SerializedName("trx-usdt")
    public ArrayList<Profile> trx_usdt;

    @Expose
    @SerializedName("link-irt")
    public ArrayList<Profile> link_irt;

    @Expose
    @SerializedName("bnb-usdt")
    public ArrayList<Profile> bnb_usdt;

    @Expose
    @SerializedName("ada-usdt")
    public ArrayList<Profile> ada_usdt;

    @Expose
    @SerializedName("xrp-irt")
    public ArrayList<Profile> xrp_irt;

    @Expose
    @SerializedName("pmn-usdt")
    public ArrayList<Profile> pmn_usdt;

    @Expose
    @SerializedName("matic-irt")
    public ArrayList<Profile> matic_irt;

    @Expose
    @SerializedName("ada-irt")
    public ArrayList<Profile> ada_irt;

    @Expose
    @SerializedName("sushi-irt")
    public ArrayList<Profile> sushi_irt;

    @Expose
    @SerializedName("trx-irt")
    public ArrayList<Profile> trx_irt;

    @Expose
    @SerializedName("uni-irt")
    public ArrayList<Profile> uni_irt;

    @Expose
    @SerializedName("doge-usdt")
    public ArrayList<Profile> doge_usdt;

    @Expose
    @SerializedName("sol-irt")
    public ArrayList<Profile> sol_irt;

    @Expose
    @SerializedName("xlm-irt")
    public ArrayList<Profile> xlm_irt;

    @Expose
    @SerializedName("axs-irt")
    public ArrayList<Profile> axs_irt;

    @Expose
    @SerializedName("sol-usdt")
    public ArrayList<Profile> sol_usdt;

    @Expose
    @SerializedName("xmr-irt")
    public ArrayList<Profile> xmr_irt;

    @Expose
    @SerializedName("dai-irt")
    public ArrayList<Profile> dai_irt;

    public String GetCoins(int index){
        String[] coins = new String[]{"xrp_usdt", "bnb_irt", "shib_irt", "dot_irt", "btc_usdt",
                "usdt_irt", "btc_irt", "avax_irt", "eth_usdt", "doge_irt", "eth_irt", "xtz_irt", "bch_irt",
                "xlm_usdt", "ltc_irt", "trx_usdt", "link_irt", "bnb_usdt", "ada_usdt", "xrp_irt", "pmn_usdt",
                "matic_irt", "ada_irt", "sushi_irt", "trx_irt", "uni_irt", "doge_usdt", "sol_irt", "xlm_irt",
                "axs_irt", "sol_usdt", "xmr_irt", "dai_irt"};
        return coins[index];
    }


}
