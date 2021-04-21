package onlineShop.models.products.components;

import static onlineShop.common.constants.OutputMessages.PRODUCT_TO_STRING;

public class CentralProcessingUnit extends BaseComponent {
    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance*1.25, generation);
    }

}
