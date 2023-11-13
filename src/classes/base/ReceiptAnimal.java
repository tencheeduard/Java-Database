package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class ReceiptAnimal extends Table{

    @PrimaryKey
    Integer idReceipt;
    @PrimaryKey
    Integer idAnimal;
    Integer price;
    
    public ReceiptAnimal(Integer idReceipt, Integer idAnimal, Integer price) throws Exception {
        this.idAnimal=idAnimal;
        this.idReceipt=idReceipt;
        this.price=price;

    }
}
