package org.tacocloud;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {
    private  String deliveryName;
    private  String deliveryStreet;
    private  String deliveryCity;
    private  String deliveryState;
    private  String deliveryZip;
    private  String ccNumber;
    private  String ccExpiration;
    private  String ccCVV;

    public List<Taco> orderedTacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        orderedTacos.add(taco);
    }
}
