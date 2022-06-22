package ir.matiran.cryptocurrency.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Profile implements Serializable {

    @Expose
    @SerializedName("size")
    public String size;

    @Expose
    @SerializedName("price")
    public String price;

    @Expose
    @SerializedName("side")
    public String side;

    @Expose
    @SerializedName("timestamp")
    public String timestamp;

    public Profile(String size, String price, String side, String timestamp){
        this.side = side;
        this.price = price;
        this.side = side;
        this.timestamp = timestamp;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size = size;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public String getSide(){
        return side;
    }
    public void setSide(String side){
        this.side = side;
    }

    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }



}
