package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        //row
        System.out.println("Enter the number of rows:");
        int row=scanner.nextInt();
        //column
        System.out.println("Enter the number of seats in each row:");
        int column=scanner.nextInt();

        int currentIncome=0;
        int totalIncome=0;
        int tickets=0;

        String[][] seats=seat(row,column);//initial seat

        while (true){
            System.out.print("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit\n");
            int number=scanner.nextInt();

            if (number==1){
                display(seats,row,column);
            }else if(number==2){
                int x=0;
                int y=0;
                while (true){
                    System.out.println("\nEnter a row number:");
                    x=scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    y=scanner.nextInt();

                    if(x>0 && x<10 && y>0 && y<10){
                        if (seats[x-1][y-1]=="B"){
                            System.out.println("That ticket has already been purchased!");
                        }else {
                            break;
                        }
                    }else {
                        System.out.println("Wrong input!");
                    }
                }

                if (seats[x-1][y-1]=="B"){
                    System.out.println("That ticket has already been purchased!");
                    continue;
                }

                //income
                tickets+=1;
                int price=ticket(seats,x,currentIncome)[0];
                currentIncome=ticket(seats,x,currentIncome)[1];

                //update seat
                System.out.printf("Ticket price: $%d\n",price);
                updateSeat(seats,x,y);//update seat
            }else if(number==3){
                Statistics(seats,currentIncome,tickets);
            }else if(number==0){
                break;
            }else {
                System.out.println("Wrong input!");
            }
        }

    }

    public static void display(String[][] seat,int row,int column){
        System.out.print("Cinema:\n  ");
        for (int j=0;j<column;j++){
            System.out.printf("%d ",j+1);
        }
        System.out.println();

        for (int i=0;i<seat.length;i++){
            System.out.printf("%d ",i+1);
            for (int j=0;j<seat[i].length;j++){
                System.out.printf("%s ",seat[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] seat(int row,int column){
        String[][] seatOfCinema=new String[row][column];

        for (String[] strings : seatOfCinema) {
            Arrays.fill(strings, "S");
        }
        return seatOfCinema;
    }

    public static void updateSeat(String[][] seat,int row,int column){
        for (int i=0;i<seat.length;i++){
            for (int j=0;j<seat[i].length;j++){
                if(i+1==row && j+1==column)
                    seat[i][j]="B";
            }
        }
    }

    public static int[] ticket(String[][] seats,int x,int currentIncome){
        int row=seats.length;
        int column=seats[0].length;
        int price=0;

        if(row*column<=60){
            price=10;
        }else {
            if(x<=row/2){
                price=10;
            }else {
                price=8;
            }
        }

        return new int[]{price,currentIncome+price};
    }

    public static void Statistics(String[][] seats,int currentIncome,int tickets){
        double Percentage=0;
        int row=seats.length;
        int column=seats[0].length;
        int totalIncome=0;
        if (row*column<=60){
            totalIncome=row*column*10;
        }else {
            totalIncome=row/2*column*10+(row-row/2)*column*8;
        }

        Percentage=(double)tickets/(row*column)*100;
        System.out.printf("\nNumber of purchased tickets: %d\n",tickets);
        System.out.printf("Percentage: %.2f%%\n",Percentage);
        System.out.printf("Current income: $%d\n",currentIncome);
        System.out.printf("Total income: $%d\n",totalIncome);
    }
}