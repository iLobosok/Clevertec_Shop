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
    public static void main(String[] args) throws InterruptedException {
    int sold; //
        //items from 1 to 8
        ArrayList<String> item = new ArrayList<>();
        item.add("Milk");
        item.add("Hamburger");
        item.add("Chicken");
        item.add("Crisps");
        item.add("Potato");
        item.add("Oil");
        item.add("Nuts");
        item.add("Beans");


        String[] itemArray = new String[item.size()];
        //акционные товары я сделаю 1,2,4,6,8 - можно было бы и другие указывать, но я сделаю проще через цикл

        // каждому товару добавляется собственный индекс

        for (int i = 0; i < item.size(); i++) {
            itemArray[i] = item.get(i);
        }

        Scanner in = new Scanner(System.in);


        int cart[] = new int[item.size()];
        int quantity[] = new int[item.size()];
        int price[] = new int[item.size()];
        int total_price[] = new int[item.size()];
        for (int i = 0; i < item.size(); i++) {
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
            int min = 2;
            int max = 15;
            int diff = max - min;
            Random random = new Random();
            for (int v=0;v<item.size();v++){
                price[v] = random.nextInt(diff + 1);
            }
            for (int k=0;k<item.size();k++){
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
                System.out.print("\n****** CARD ACTIVATED ******");
            }
            System.out.print("\n\n");
            System.out.printf("%-5s%-11s%-15s%-11s%n","QITY","PRODUCTS","PRICE","TOTAL");
            int price_max = 0; // создаём макс значение и суммируем каждый продукт
            for (int x = 0; x < 8; x++) {
                if (quantity[x] != 0 && cart[x] != 0) {
                    System.out.printf("%-5s%-11d%-1s%-25s%-3s%-1s%n",quantity[x], cart[x],"$",price[x],"$",total_price[x]);
                    //System.out.printf("\n%d", quantity[x], "         ",cart[x]);
                    price_max = price_max + total_price[x];

                }
            }
            if (card_tax_bool == false) {
                for (int i = 0; i < 50; i++) {
                    System.out.print("-");
                }
                System.out.printf("\nTOTAL = $ %s", price_max);
            }
        else if (card_tax_bool == true) {
            for (int i = 0; i < 50; i++) {
                System.out.print("-");
            }
            price_max = (price_max*(100-tax_card))/100;
            System.out.printf("\nTOTAL = $ %s", price_max);
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
        } else if (card_point.equals("n")) {

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

        // происходит скидка 3 процента, при вводе любой из карт
        //здесь можно было бы вставить цикл, но у нас всего 4 элемента
        if (card_number.equals("312346") || card_number.equals("778877") || card_number.equals("92929") || card_number.equals("321123")) {
            tax_card = 3;
            System.out.println("Accepted");
            card_tax_bool = true; // карта активирована
        } else {
            System.out.print("\n Card didn't found");
        }
    }



}
