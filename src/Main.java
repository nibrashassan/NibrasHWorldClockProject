//Console Version--NibrasNHWorldClockProject
// Simple application to check times in different countries





import java.time.*;
import java.time.format.*;
import java.util.*;


public class Main {


    
    // Timezone_abbrreviation
    static String getTimezoneAbbreviation(String timezoneId) {
        switch(timezoneId) {
            case "Asia/Kolkata": return "IST (India Standard Time)";
            case "Asia/Karachi": return "PKT (Pakistan Standard Time)";
            case "Europe/Berlin": return "CET (Central European Time)";
            case "America/New_York": return "EST (Eastern Standard Time)";
            case "Asia/Tokyo": return "JST (Japan Standard Time)";
            case "Europe/London": return "GMT (Greenwich Mean Time)";
            case "Australia/Sydney": return "AEST (Australian Eastern Time)";
            case "Europe/Paris": return "CET (Central European Time)";
            case "Asia/Shanghai": return "CST (China Standard Time)";
            case "Asia/Riyadh": return "AST (Arabia Standard Time)";
            default: return timezoneId;
                    }    }

    
    // NEW: Get GMT offset
    static String getGMTOffset(String timezoneId) {
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of(timezoneId));
        ZoneOffset offset = time.getOffset();
        int totalSeconds = offset.getTotalSeconds();
        int hours = Math.abs(totalSeconds / 3600);
        int minutes = Math.abs((totalSeconds % 3600) / 60);
        
        String sign = totalSeconds >= 0 ? "+" : "-";
        return String.format("GMT%s%02d:%02d", sign, hours, minutes);    }

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

        runProgram(countries);  }




    
    //Time_in_the_country
    static void showMyTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm:ss a");
        System.out.println("Your local time: " + now.format(f));       }

    //Loop
    static void runProgram(String[][] countries) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter country name (or 'quit' to exit): ");
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;  }



            
            //Find _country
            String[] found = null;
            for (String[] c : countries) {
                if (c[0].equalsIgnoreCase(input)) {
                    found = c;
                    break;
                }  }

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
                        break;
                    } }

                if (otherCountry != null) {
                    compareTwoCountries(found, otherCountry); }
                
                else {
                    System.out.println("Couldn't find that country."); } }

            System.out.println("\n" + "-".repeat(40));
        }

        scan.close();
        System.out.println("\nThanks for using World Clock!");    }






    

    // Show_time_for_one_country - MODIFIED to show timezone abbreviations
    static void showTimeForCountry(String[] country) {
        try {
            ZonedDateTime time = ZonedDateTime.now(ZoneId.of(country[1]));
            DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm:ss a");
            DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

            System.out.println("\n--- " + country[0] + " ---");
            System.out.println("Time: " + time.format(timeFmt));
            System.out.println("Date: " + time.format(dateFmt));
            System.out.println("Timezone: " + country[1]);




            
            //Showtimezone_fullform
            String tzAbbr = getTimezoneAbbreviation(country[1]);
            String gmtOffset = getGMTOffset(country[1]);
            System.out.println("Time Zone: " + tzAbbr);
            System.out.println("Offset: " + gmtOffset);
        }
        catch (Exception e) {
            System.out.println("Oops! Error getting time.");
        }
    }

    //compare_countries - MODIFIED to show timezone info
    static void compareTwoCountries(String[] c1, String[] c2) {
        try {
            ZonedDateTime t1 = ZonedDateTime.now(ZoneId.of(c1[1]));
            ZonedDateTime t2 = ZonedDateTime.now(ZoneId.of(c2[1]));

            System.out.println("\n=== COMPARISON ===");
            
            //timezone info for both countries
            String tz1Abbr = getTimezoneAbbreviation(c1[1]);
            String tz2Abbr = getTimezoneAbbreviation(c2[1]);
            String gmt1 = getGMTOffset(c1[1]);
            String gmt2 = getGMTOffset(c2[1]);
            
            System.out.println(c1[0] + ": " + t1.format(DateTimeFormatter.ofPattern("hh:mm a")) + 
                               " | " +tz1Abbr + " | " + gmt1);
            System.out.println(c2[0] + ": " + t2.format(DateTimeFormatter.ofPattern("hh:mm a")) + 
                               "| " + tz2Abbr + " |" + gmt2);

            int diff = t1.getHour() - t2.getHour();
            if (diff > 0) {
                System.out.println(c1[0] + " is " + diff + " hours AHEAD of " + c2[0]);
            } else if (diff < 0) {
                System.out.println(c1[0] + " is " + (-diff) + " hours BEHIND " + c2[0]);
            } else {
                System.out.println("Same time zone!"); }



            
            
            // NEW: Show timezone difference
            System.out.println("\nTime Zone Difference:");
            System.out.println(gmt1 + " vs " + gmt2);
        }
        catch (Exception e) {
            System.out.println("Couldn't compare times.");
        }
                        }}
//Completed_FINAL
