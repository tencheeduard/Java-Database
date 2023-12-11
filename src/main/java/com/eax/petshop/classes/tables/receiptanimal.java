package com.eax.petshop.classes.tables;

import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

public class receiptanimal extends Table {

    @PrimaryKey
    public Integer idReceipt;
    @PrimaryKey
    public Integer idAnimal;
    public Integer price;


    public receiptanimal() throws Exception {}
    public receiptanimal(Integer idReceipt, Integer idAnimal, Integer price) throws Exception {
        this.idAnimal=idAnimal;
        this.idReceipt=idReceipt;
        this.price=price;

    }
}
