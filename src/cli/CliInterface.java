package cli;

import lib.Person;
import utils.DataFileNames;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static cli.CaseHandler.*;

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
            case 1 -> displayMembers(fh);
            // Register Person
            case 2 -> registerPerson(fh);
            // Membership payment pause
            case 3 -> paymentPause(fh);
           // Register trainingStats
            case 4 -> registerTrainingStats(fh);
            // Register compStats
            case 5 -> registerCompStats(fh);
            // Display top of 5 in every discipline
            case 6 -> topFive(fh);
            // Display membership fee income
            case 7 -> displayFeeIncome(fh);
            // Display delinquent members
            case 8 -> displayDelinquent(fh);
            case 0 -> {
                return false;
            }
        }
    return true;
    }

}
