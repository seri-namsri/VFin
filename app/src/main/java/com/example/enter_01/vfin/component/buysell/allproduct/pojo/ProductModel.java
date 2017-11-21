package com.example.enter_01.vfin.component.buysell.allproduct.pojo;

import com.example.enter_01.vfin.component.profile.model.Member;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

@Parcel
public class ProductModel {
    public String name;

    public String getFull_detail() {
        return full_detail;
    }

    public String full_detail;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String image;

    public String getDetail() {
        return detail;
    }

    public String detail;

    public String getProduct_id() {
        return product_id;
    }

    public String product_id;
    public int time, price;

    public ArrayList<String> getImage_product() {
        return image_product;
    }

    public ArrayList<String> image_product;

    public ArrayList<Member> getBuy_member() {
        return buy_member;
    }

    public void setBuy_member(ArrayList<Member> buy_member) {
        this.buy_member = buy_member;
    }

    public ArrayList<Member> buy_member;


    public List<MemberBuy> getMember_buy() {

            return member_buy;
    }


    public int getPrice_market() {
        return price_market;
    }

    public int price_market ;

    public ArrayList<MemberBuy> member_buy;


}
