//Console Version--NibrasNHWorldClockProjectt
// Simple app to check times in different countries

import java.time.*;
import java.time.format.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       WORLD CLOCK CONSOLE      ");
        System.out.println("================================");
        System.out.println();




        showMyTime();
        System.out.println();

        //Country_list
        String[][] countries =
                {
                        {"India", "Asia/Kolkata"},
                        {"Pakistan", "Asia/Karachi"},
                        {"Germany", "Europe/Berlin"},
                        {"USA-NewYork", "America/New_York"},
                        {"Japan", "Asia/Tokyo"},
                        {"UK", "Europe/London"},
                        {"Australia-Sydney", "Australia/Sydney"},
                        {"France", "Europe/Paris"},
                        {"China", "Asia/Shanghai"},
                        {"SaudiArabia", "Asia/Riyadh"}     };

        //printcountry
        System.out.println("Available countries:");
        for (int i = 0; i < countries.length; i++) {
            System.out.print((i+1) + ". " + countries[i][0] + "  ");
            if ((i+1) % 3 == 0) System.out.println();
        }
        System.out.println("\n");

        runProgram(countries);       }

    //Time_in_the_country
    static void showMyTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm:ss a");
        System.out.println("Your local time: " + now.format(f));}




    //Loop
    static void runProgram(String[][] countries) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter country name (or 'quit' to exit): ");
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }





            // Find _country
            String[] found = null;
            for (String[] c : countries) {
                if (c[0].equalsIgnoreCase(input)) {
                    found = c;
                    break;
                }   }

            if (found == null) {
                System.out.println("Sorry, didn't find '" + input + "'");
                continue;  }

            // Displaytime
            showTimeForCountry(found);



            //comparing
            System.out.print("\nCompare with another country? (y/n): ");
            String ans = scan.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter other country: ");
                String other = scan.nextLine();

                String[] otherCountry = null;
                for (String[] c : countries) {
                    if (c[0].equalsIgnoreCase(other)) {
                        otherCountry = c;
                        break;}                         }

                if (otherCountry != null) {
                    compareTwoCountries(found, otherCountry);
                } else {
                    System.out.println("Couldn't find that country.");  }       }

            System.out.println("\n" + "-".repeat(40));
        }

        scan.close();
        System.out.println("\nThanks for using World Clock!");
    }

    // Show_time_for_one_country
    static void showTimeForCountry(String[] country) {
        try {
            ZonedDateTime time = ZonedDateTime.now(ZoneId.of(country[1]));
            DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm:ss a");
            DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

            System.out.println("\n--- " + country[0] + " ---");
            System.out.println("Time: " + time.format(timeFmt));
            System.out.println("Date: " + time.format(dateFmt));
            System.out.println("Timezone: " + country[1]); }

        catch (Exception e) {
            System.out.println("Oops! Error getting time.");   }   }

    //compare_countries
    static void compareTwoCountries(String[] c1, String[] c2) {
        try {
            ZonedDateTime t1 = ZonedDateTime.now(ZoneId.of(c1[1]));
            ZonedDateTime t2 = ZonedDateTime.now(ZoneId.of(c2[1]));

            System.out.println("\n=== COMPARISON ===");
            System.out.println(c1[0] + ": " + t1.format(DateTimeFormatter.ofPattern("hh:mm a")));
            System.out.println(c2[0] + ": " + t2.format(DateTimeFormatter.ofPattern("hh:mm a")));

            int diff = t1.getHour() - t2.getHour();
            if (diff > 0) {
                System.out.println(c1[0] + " is " + diff + " hours AHEAD");
            } else if (diff < 0) {
                System.out.println(c1[0] + " is " + (-diff) + " hours BEHIND");
            } else {
                System.out.println("Same time zone!");
            }}

        catch (Exception e) {
            System.out.println("Couldn't compare times.");
        } } }


//Completed_FINAL