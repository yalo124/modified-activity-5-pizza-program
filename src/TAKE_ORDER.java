import javax.swing.*;

public class TAKE_ORDER {
    public static void main(String[] args){
        PIZZA p = new PIZZA();

        p.setToppings("Pepperoni", 100);
        p.setToppings("Ham and Cheese", 150);
        p.setToppings("Hawaiian", 200);

        p.setDiscount("N/A", 0);
        p.setDiscount("Senior-PWD", 0.2);
        p.setDiscount("VIP", 0.12);

        MENU(p);
        SIZE_and_QUANTITY(p);
        DISCOUNT_TYPE(p);
        RECEIPT(p);
    }

    static void confirmCancel(){
        int r = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?", "Confirm Question", JOptionPane.YES_NO_OPTION);
        if(r == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Thank you!", "Message", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    // Keeps asking until the user enters a valid positive whole number (try...catch).
    static int askPositiveInt(String message, String initial){
        while(true){
            String input = JOptionPane.showInputDialog(null, message, initial);
            if(input == null){
                confirmCancel();
                continue;
            }
            try {
                int value = Integer.parseInt(input.trim());
                if(value <= 0) throw new NumberFormatException();
                return value;
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid input. Enter a positive whole number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static void MENU(PIZZA p){
        Object[] toppings = p.getToppings().toArray();
        Object[] options = new Object[toppings.length + 1];
        options[0] = "";
        System.arraycopy(toppings, 0, options, 1, toppings.length);

        while(true){
            String choose = (String) JOptionPane.showInputDialog(null, "Choose Toppings", "Pizza Program",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if(choose == null){
                confirmCancel();
                continue;
            }
            if(choose.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please choose a topping.", "Message", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            JOptionPane.showMessageDialog(null, "You chose " + choose + "\nwhich cost additional " + p.getToppingsPrice(choose).intValue() + "php!",
                    "Message", JOptionPane.INFORMATION_MESSAGE);

            int sure = JOptionPane.showConfirmDialog(null, "Are you sure?\nYou want " + choose + "?", "Confirm Question", JOptionPane.YES_NO_OPTION);
            if(sure == JOptionPane.YES_OPTION){
                p.setSelectedTopping(choose);
                return;
            }
        }
    }

    static void SIZE_and_QUANTITY(PIZZA p){
        p.setDiameter(askPositiveInt("Enter Diameter in inches", "10"));
        p.setHowMany(askPositiveInt("Enter How Many?", "1"));
    }

    static void DISCOUNT_TYPE(PIZZA p){
        Object[] cards = p.getDiscount().toArray();

        while(true){
            int choose = JOptionPane.showOptionDialog(null, "Choose Discount:", "Pizza Program",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cards, cards[0]);

            if(choose == JOptionPane.CLOSED_OPTION){
                confirmCancel();
                continue;
            }

            p.setDiscountCard((String) cards[choose]);
            if(choose == 0) return;

            while(true){
                String cardNumber = JOptionPane.showInputDialog(null, "Enter " + p.getDiscountcard() + " Card Number:");
                if(cardNumber == null){
                    confirmCancel();
                    continue;
                }
                try {
                    Long.parseLong(cardNumber.trim());     
                    return;
                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Invalid card number. Digits only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    static void showReceipt(PIZZA p, double total, double pay){
        StringBuilder sb = new StringBuilder();
        sb.append("Toppings: ").append(p.getSelectedTopping()).append("\n");
        sb.append("Size: ").append(p.getDiameter()).append(" inches\n");
        sb.append("Quantity: ").append(p.getHowMany()).append("\n");

        String discount = p.getDiscountcard();
        if(discount != null && !discount.isBlank() && !discount.equals("N/A")){
            sb.append("Discount Type: ").append(discount).append("\n");
        }

        sb.append("Amount Due: ").append(String.format("%.2f", total)).append("\n");
        sb.append("Amount Received: ").append(String.format("%.2f", pay)).append("\n");
        sb.append("Change: ").append(String.format("%.2f", pay - total));

        JOptionPane.showMessageDialog(null, sb.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }

    static void RECEIPT(PIZZA p){
        BILL_ORDER bo = new BILL_ORDER();
        double total = bo.OUT(p);

        JOptionPane.showMessageDialog(null, "You ordered for " + p.getSelectedTopping() + " Pizza\nwith the size of "
                        + (int) (p.getDiameter() * 2.54) + " centimeter\nfor the total price of PHP" + String.format("%.2f", total),
                "Message", JOptionPane.INFORMATION_MESSAGE);

        while(true){
            String input = JOptionPane.showInputDialog(null, "Enter Amount:");
            if(input == null){
                confirmCancel();
                continue;
            }
            try {
                double pay = Double.parseDouble(input.trim());
                if(pay < total){
                    JOptionPane.showMessageDialog(null, "Insufficient Amount", "Pizza Program", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                JOptionPane.showMessageDialog(null, "Your CHANGE is Php" + String.format("%.2f", pay - total), "Message", JOptionPane.INFORMATION_MESSAGE);
                showReceipt(p, total, pay);
                JOptionPane.showMessageDialog(null, "Thank you!", "Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid amount. Enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}