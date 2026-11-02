import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Collections;
public class PIZZA {
    final LinkedHashMap<String, Double> Toppings = new LinkedHashMap<>();
    final LinkedHashMap<String, Double> Discount = new LinkedHashMap<>();

    private String Discountcard = "N/A";
    private String selectedTopping;
    private int Diameter, HowMany;
    private double Price;

    public Set<String> getToppings() {
        return Collections.unmodifiableSet(Toppings.keySet());
    }

    public Double getToppingsPrice(String topping) {
        return Toppings.get(topping);
    }

    public Set<String> getDiscount() {
        return Collections.unmodifiableSet(Discount.keySet());
    }

    public void setSelectedTopping(String t) {
        this.selectedTopping = t;
    }

    public String getSelectedTopping() {
        return selectedTopping;
    }

    public void setDiscountCard(String card){
        this.Discountcard = card;
    }

    public String getDiscountcard(){
        return Discountcard;
    }

    public double getDiscountPercentage(String discountcard){
        return Discount.get(discountcard);
    }

    public int getDiameter() {
        return Diameter;
    }

    public int getHowMany() {
        return HowMany;
    }

    public double getPrice() {
        return Price;
    }

    public void setToppings(String toppings, double price) {
        Toppings.put(toppings, price);
    }

    public void setDiscount(String card, double percentage) {
        Discount.put(card, percentage);
    }

    public void setDiameter(int Diameter) {
        this.Diameter = Diameter;
    }

    public void setHowMany(int HowMany) {
        this.HowMany = HowMany;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
}