package cli;

import lib.Person;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CliInterface {
    public void greet(){
        System.out.println("\nVelkommen til Delfinen medlemsregister!");
    }
    private void menuPrompt() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);

        System.out.println("\nOversigt af medlemmer - tryk 1");
        System.out.println("Registrer medlem eller træner i klubben - tryk 2");
        System.out.println("Sæt eksisterende medlemskab i bero - tryk 3");
        System.out.println("Bogfør træningspræstation  - tryk 4");
        System.out.println("Bogfør konkurrencepræstation  - tryk 5");
        System.out.println("Vis top i hver disciplin  - tryk 6");
        System.out.println("Vis samlet forventet kontingentindbetaling  - tryk 7");
        System.out.println("Vis medlemmer i restance  - tryk 8");


        System.out.println("Luk program - tryk 0\n");
    }

    public boolean menuSelection() throws InterruptedException {
        menuPrompt();
        var scanner = new Scanner(System.in);
        var tokenInt = scanner.nextInt();
//        scanner.close();

        var fh = new FileHandler();
        switch (tokenInt){
            // Display Members
            case 1 -> {
                var membersData = fh.load("members.csv");
                System.out.println("Medlems ID | Hold | Disciplin");
                for (var member : membersData){
                    if (member.length == 1) continue;

                    var discipline = "";

                    if (Objects.equals(member[2], "1")){
                        discipline = "Butterfly";
                    }
                    if (Objects.equals(member[2], "2")) {
                        discipline = "Crawl";
                    }
                    if (Objects.equals(member[2], "3")){
                        discipline = "Rygcrawl";
                    }
                    if (Objects.equals(member[2], "4")){
                        discipline = "Brystsvømning";
                    }
                    System.out.println(member[0]+"\t"+member[1]+"\t"+discipline);
                }

            }
            // Register Person
            case 2 -> {
                System.out.println("Indtast fornavn");
                scanner = new Scanner(System.in);
                String tokenStr = scanner.nextLine();
                var firstName = tokenStr;

                System.out.println("Indtast efternavn");
                tokenStr = scanner.nextLine();
                var lastName = tokenStr;

                System.out.println("Indtast telefonnummer");
                tokenStr = scanner.nextLine();
                var phoneNumber = tokenStr;

                System.out.println("Indtast adresse");
                tokenStr = scanner.nextLine();
                var address = tokenStr;

                System.out.println("Indtast alder");
                tokenInt = scanner.nextInt();
                var age = tokenInt;

                var person = new Person(firstName,lastName,phoneNumber,address,age);

                System.out.println("Hvad ønskes person registrering til?");
                System.out.println("Træner - skriv 1");
                System.out.println("medlem - skriv 2");

                tokenInt = scanner.nextInt();
                switch (tokenInt){
                    case 1 -> {
                        System.out.println("Hvilket hold skal træner være for?");
                        System.out.println("Hold 1 - skriv 1");
                        System.out.println("Hold 2 - skriv 2");
                        System.out.println("Hold 3 - skriv 3");
                        System.out.println("Hold 4 - skriv 4");

                        tokenInt = scanner.nextInt();
                        var setTeam = tokenInt;
//                        scanner.close();
                        var coachRecord = new String[]{String.valueOf(person.id), String.valueOf(setTeam)};

                        fh.save(coachRecord,"coaches.csv",true);
                    }
                    case 2 -> {

                        System.out.println("Er medlemskabet passivt?");
                        System.out.println("Ja - skriv 1");
                        System.out.println("nej - skriv 2");
                        tokenInt = scanner.nextInt();
                        boolean isPassive = false;
                        if(tokenInt == 1){
                            isPassive = true;
                        }

                        System.out.println("udregner kontingent pris . . .\n");
                        int paymentAmt = 1800;
                        if (person.age < 18) paymentAmt = 1600;
                        if (isPassive) paymentAmt = 500;
                        if(person.age > 59)  paymentAmt = (int) (paymentAmt * 0.75);

                        for (int i = 0; i < 10; i++) {
                            if (i %  2 == 0) System.out.println(". . ");
                            if (i %  3 == 0) System.out.println(". . . .");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(". . .");
                        }
                        System.out.println("udregning afsluttet!");
                        System.out.println("kontingent pris for medlem: " +  paymentAmt);

                        System.out.println("Har medlem betalt");
                        System.out.println("Ja - skriv 1");
                        System.out.println("Nej - skriv 2");
                        tokenInt = scanner.nextInt();
                        var hasPaid = tokenInt;

                        var paymentRecord = new String[]{person.id, String.valueOf(hasPaid), String.valueOf(paymentAmt)};
                        fh.save(paymentRecord,"payments.csv",true );
//                        Early exit if passive
                        if (isPassive) break;

                        System.out.println("Hvilket hold skal medlem være en del af?");
                        System.out.println("Hold 1 - skriv 1");
                        System.out.println("Hold 2 - skriv 2");
                        System.out.println("Hold 3 - skriv 3");
                        System.out.println("Hold 4 - skriv 4");

                        tokenInt = scanner.nextInt();
                        var setTeam = tokenInt;

                        System.out.println("Hvilket disciplin skal medlem deltage i?");
                        System.out.println("Butterfly - skriv 1");
                        System.out.println("Crawl - skriv 2");
                        System.out.println("Rygcrawl - skriv 3");
                        System.out.println("Brystsvømning - skriv 4");

                        tokenInt = scanner.nextInt();
                        var setDiscipline = tokenInt;

                        var memberRecord = new String[]{person.id, String.valueOf(setDiscipline), String.valueOf(setTeam)};
                        fh.save(memberRecord,"members.csv",true);
//                        scanner.close();
                    }
                }
            }
            // Membership payment pause
            case 3 -> {
                System.out.println("Indtast medlemmets ID");
                scanner = new Scanner(System.in);
                String tokenStr = scanner.nextLine();
                var memberId = tokenStr;
                var paymentPause = new String[]{memberId};
                fh.save(paymentPause,"paymentPauses.csv", true);
                System.out.println("Medlemskab sat i bero");
            }
           // Register trainingStats
            case 4 -> {
                System.out.println("Indtast medlemmets ID");
                scanner = new Scanner(System.in);
                String tokenStr = scanner.nextLine();
                var memberId = tokenStr;

                System.out.println("Indtast svømmerens disciplin");
                System.out.println("Tast 1 - Butterfly");
                System.out.println("Tast 2 - Crawl");
                System.out.println("Tast 3 - Rygcrawl");
                System.out.println("Tast 4 - Brystsvømning");

                tokenStr = scanner.nextLine();
                var discipline = tokenStr;

                System.out.println("Indtast svømmerens tid i SEKUNDER");
                tokenStr = scanner.nextLine();
                var time = tokenStr;

                var compStatsRecord = new String[]{memberId,discipline, time};

                fh.save(compStatsRecord,"trainingStats.csv",true);
            }
            // Register compStats
            case 5 -> {
                System.out.println("Indtast medlemmets ID");
                scanner = new Scanner(System.in);
                String tokenStr = scanner.nextLine();
                var memberId = tokenStr;

                System.out.println("Indtast svømmerens disciplin");
                System.out.println("Tast 1 - Butterfly");
                System.out.println("Tast 2 - Crawl");
                System.out.println("Tast 3 - Rygcrawl");
                System.out.println("Tast 4 - Brystsvømning");

                tokenStr = scanner.nextLine();
                var discipline = tokenStr;

                System.out.println("Indtast Stævne navn");
                tokenStr = scanner.nextLine();
                var tournamentName = tokenStr;

                System.out.println("Indtast svømmerens placering");
                tokenStr = scanner.nextLine();
                var placement = tokenStr;

                System.out.println("Indtast svømmerens tid i SEKUNDER");
                tokenStr = scanner.nextLine();
                var time = tokenStr;

                var compStatsRecord = new String[]{memberId,discipline, placement, time, tournamentName};

                fh.save(compStatsRecord,"compStatsRecord.csv",true);
            }
            // Display top of 5 in every discipline
            case 6 -> {
                var dataOutput = fh.load("compStatsRecord.csv");
                var butterfly = new ArrayList<String[]>();
                var crawl = new ArrayList<String[]>();
                var backCrawl = new ArrayList<String[]>();
                var breastStroke = new ArrayList<String[]>();

                for (String[] record : dataOutput) {
                    if (record.length > 1) {
                        if(record[1].equals("1")) {
                            butterfly.add(record);
                        }
                        if(record[1].equals("2")) {
                            crawl.add(record);
                        }
                        if(record[1].equals("3")) {
                            backCrawl.add(record);
                        }
                        if(record[1].equals("4")) {
                            breastStroke.add(record);
                        }
                    }
                }

                butterfly.sort(Comparator.comparingInt(a -> Integer.parseInt(a[3])));
                System.out.println("Butterfly");
                System.out.println("Medlems ID | Disciplin |  Placering | Konkurrence tid | Turnering");
                for (int i = 0; i < 5 && i < butterfly.size(); i++) {
                    String[] arr = butterfly.get(i);
                    System.out.println(java.util.Arrays.toString(arr));
                }
                System.out.println("\nCrawl");
                System.out.println("Medlems ID | Disciplin |  Placering | Konkurrence tid | Turnering");
                crawl.sort(Comparator.comparingInt(a -> Integer.parseInt(a[3])));
                for (int i = 0; i < 5 && i < crawl.size(); i++) {
                    String[] arr = crawl.get(i);
                    System.out.println(java.util.Arrays.toString(arr));
                }
                System.out.println("\nRygcrawl");
                System.out.println("Medlems ID | Disciplin |  Placering | Konkurrence tid | Turnering");
                backCrawl.sort(Comparator.comparingInt(a -> Integer.parseInt(a[3])));
                for (int i = 0; i < 5 && i < backCrawl.size(); i++) {
                    String[] arr = backCrawl.get(i);
                    System.out.println(java.util.Arrays.toString(arr));
                }
                System.out.println("\nBrystsvømning");
                System.out.println("Medlems ID | Disciplin |  Placering | Konkurrence tid | Turnering");
                breastStroke.sort(Comparator.comparingInt(a -> Integer.parseInt(a[3])));
                for (int i = 0; i < 5 && i < breastStroke.size(); i++) {
                    String[] arr = breastStroke.get(i);
                    System.out.println(java.util.Arrays.toString(arr));
                }
            }
            // Display membership fee income
            case 7 -> {
                int totalIncome = 0;
                var paymentsData = fh.load("payments.csv");
                for (String[] record : paymentsData) {
                    if (record.length > 1) {
                        totalIncome += Integer.parseInt(record[2]);
                    }
                }
                System.out.println("\nTotal forventet kontingentindbetalings: " + totalIncome);
                System.out.println();
            }
            // Display delinquint members
            case 8 -> {
                var paymentsData = fh.load("payments.csv");
                for (String[] record : paymentsData) {
                    if (record.length > 1) {
                        if(record[1].equals("2")) {
                            System.out.println(record[0]+"\t"+record[1]+"\t"+record[2]);
                        }
                    }
                }
            }
            case 0 -> {
                return false;
            }
        }
    return true;
    }

}
