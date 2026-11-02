public class BILL_ORDER extends PIZZA {

    public double OUT(PIZZA p){
        int diameter = (int) (p.getDiameter() * 2.54);

        double price = diameter * 5 + p.getToppingsPrice(p.getSelectedTopping());
        price *= p.getHowMany();

        double discountPrice = price - (price * p.getDiscountPercentage(p.getDiscountcard()));
        p.setPrice(discountPrice);
        return p.getPrice();
    }
}