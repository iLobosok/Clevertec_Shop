package com.company;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static boolean card_tax_bool = false;
    public static String card_number;
    public static int tax_card;
    public static int discount_to_print = 0;
    public static int col = 0;
    public static boolean akcion_items = false;
    public static void main(String[] args) throws InterruptedException {

        //items from 1 to 15
        String[] prod  = new String[16];
        prod[1] = "Milk";       // акционный
        prod[2] = "Hamburger";      // акционный
        prod[3] = "Chicken";        // акционный
        prod[4] = "Crisps";
        prod[5] = "Potato";     // акционный
        prod[6] = "Oil";
        prod[7] = "Nuts";   // акционный
        prod[8] = "Beans";
        prod[9] = "Butter";
        prod[10] = "Cucumber"; // акционный
        prod[11] = "Blazer";
        prod[12] = "Hubba-Bubba";
        prod[13] = "Cycle";
        prod[14] = "Orange";
        prod[15] = "Carrot"; // акционный



        Scanner in = new Scanner(System.in);


        int cart[] = new int[prod.length];
        int quantity[] = new int[prod.length];
        int price[] = new int[prod.length];
        int total_price[] = new int[prod.length];
        for (int i = 1; i < prod.length; i++) {
            System.out.print("Write product = ");
            cart[i] = in.nextInt();
            System.out.print("Write quantity = ");
            quantity[i] = in.nextInt();
            if (cart[i] == 0 || quantity[i] == 0) {
                System.out.print("Loading check");
                for (int load = 0; load < 3; load++) {
                    System.out.print(".");
                    Thread.sleep(500);
                }

                break;
            }

            for (int g=0; g< 16;g++){
                if (cart[g] == 1 || cart[g] == 2 || cart[g] == 3 || cart[g] == 5 || cart[g] == 7 || cart[g] == 10 || cart[g] == 15) {
                  col++;
                }
            }

            // тут можно было бы использовать массив, чтобы каждое значение продукта была n-цена, но мне почему-то захотелось рандомно выводить цену >_<
            int min = 1;
            int max = 13;
            int diff = max - min;
            Random random = new Random();
            for (int v=1;v<prod.length;v++){
                price[v] = random.nextInt(diff + 1);
                if(price[v]==0){
                    price[v] = random.nextInt(diff + 1);
                }
            }
            for (int k=1;k<prod.length;k++){
                total_price[k] = price[k]*quantity[k];

            }
        }

        card_decide();


            for (int load = 0; load < 3; load++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            System.out.print("\n");
                System.out.print("                  CASH RECEIPT                    \n");      //вывод верхней границы чека
            for (int i = 0; i < 50; i++) {
                System.out.print("-");
            }
             Date date = new Date();
            String dateCheck = String.format("Дата покупки CLEVERTEC SHOP: %tc", date);
            System.out.print("\n");
            System.out.printf(dateCheck);

            if (card_tax_bool == true)
            {
                System.out.print("\n            ****** CARD ACTIVATED ******            ");
            }
            System.out.print("\n\n");
            System.out.printf("%-5s%-11s%-15s%-11s%n","QITY","PRODUCTS","PRICE","TOTAL");
            int price_max = 0; // создаём макс значение и суммируем каждый продукт
            for (int x = 0; x < prod.length; x++) {
                if (quantity[x] != 0 && cart[x] != 0) {
                    System.out.printf("%-5s%-11s%-1s%-25s%-1s%-1s%n",quantity[x],prod[cart[x]],"$",price[x],"$",total_price[x]);
                    price_max = price_max + total_price[x];

                }
            }
            if (card_tax_bool == false && tax_card == 10)   //если карты нет, но есть акционные товары
        {
            discount_to_print = price_max - (price_max*(100-tax_card)/100);
            for (int i = 0; i < 50; i++) {
                System.out.print("-");
            }
            System.out.printf("\nDISCOUNT = $ %s", discount_to_print);
            System.out.printf("\nTOTAL = $ %s", price_max-discount_to_print);
        }
            else if (card_tax_bool == false) {
                    for (int i = 0; i < 50; i++) {
                        System.out.print("-");
                                                 }
                    System.out.printf("\nTOTAL = $ %s", price_max);
            }
        else if (card_tax_bool == true) {
            for (int i = 0; i < 50; i++) {
                System.out.print("-");
            }
            discount_to_print = price_max - (price_max*(100-tax_card)/100);
            price_max = (price_max*(100-tax_card))/100;
            System.out.printf("\nDISCOUNT = $ %s", discount_to_print);
            System.out.printf("\nTOTAL = $ %s", price_max-discount_to_print);
        }

        else {card_decide();}
    }

    public static void card_decide() {

        Scanner card = new Scanner(System.in);
        System.out.println("\nDo you have card y/n: ");
        String card_point = card.nextLine();
        if (card_point.equals("y")) {

            System.out.println("Write number of your card: ");
            card_number = card.nextLine();
            salecard();
        }
        else if (card_point.equals("n")) {
            if (col>5)     // если в корзинке более пяти акционных, то скидка 10 процентов
            {
                tax_card +=10;
            }
        }

        card.close();
    }

    private static void salecard() {
        //cards from 1 to 4
        ArrayList<String> cards = new ArrayList<>();
        cards.add("312346");
        cards.add("778877");
        cards.add("92929");
        cards.add("321123");

        // происходит скидка 5 процентов, при вводе любой из карт
        //здесь можно было бы вставить цикл, но у нас всего 4 элемента
        if (card_number.equals("312346") || card_number.equals("778877") || card_number.equals("92929") || card_number.equals("321123")) {
            tax_card += 5;
            System.out.println("Accepted");
            card_tax_bool = true; // карта активирована
            if (col>5)     // если в корзинке более пяти акционных, то скидка 10 процентов
            {
                tax_card +=10;
            }
        }
        else {
            System.out.print("\n Card didn't found");
        }
    }



}
