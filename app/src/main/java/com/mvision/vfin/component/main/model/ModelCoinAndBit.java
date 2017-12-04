package com.mvision.vfin.component.main.model;

/**
 * Created by enter_01 on 11/28/2017 AD.
 */

public class ModelCoinAndBit {
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

    public double wallet;

    public class Ability{
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

        public int max ;
    }
}
