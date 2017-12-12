package com.mvision.vfin.component.main.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 11/28/2017 AD.
 */

public class ModelCoinAndBit {
    @SerializedName("ability")
    public Ability ability;

    public ModelCoinAndBit(Ability ability, double wallet) {
        this.ability = ability;
        this.wallet = wallet;
    }

    public Ability getAbility() {
        return ability;
    }

    public double getWallet() {
        return wallet;
    }
    @SerializedName("wallet")
    public double wallet;

    public class Ability{
        @SerializedName("current")
        public int current;

        public Ability (int current,int max){
            this.current = current ;
            this.max = max ;
        }

        public int getCurrent() {
            return current;
        }

        public int getMax() {
            return max;
        }

        @SerializedName("max")
        public int max ;
    }
}
