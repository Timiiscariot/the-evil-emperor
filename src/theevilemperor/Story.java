package theevilemperor;

public class Story {

    public static void printIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Story");
        GameLogic.printSeparator(2);
        System.out.println("You are the last man standing after your village got " +
                "raided by the henchmen of the evil Emperor.");
        System.out.println("Every single one of your friends got murdered." +
                "Take a revenge and defeat the evil Emperor! Start your journey through the haunted land of Mordor!");
        GameLogic.anythingToContinue();
    }

    public static void printFirstIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Act I Intro");
        GameLogic.printSeparator(2);
        System.out.println("You've managed to cross the haunted land of Mordor and " +
                "are near the evil Emperor's castle.");
        System.out.println("Keep going to take your revenge!");
        GameLogic.anythingToContinue();

    }

    public static void printSecondIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Act II Intro");
        GameLogic.printSeparator(2);
        System.out.println("You are in the Emperor's castle!");
        System.out.println("Open all the doors, to find the Emperor!");
        GameLogic.anythingToContinue();

    }

    public static void printThirdIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Act III Intro");
        GameLogic.printSeparator(2);
        System.out.println("You are standing in front of the Emperor's door. " +
                "Open it and enter if you dare!");
        GameLogic.anythingToContinue();

    }

    public static void printFourthIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Act IV Intro");
        GameLogic.printSeparator(2);
        System.out.println("You entered the Emperor's room, and are staring right into " +
                "his mean eyes.");
        System.out.println("You feel the darkness around you, as he raises his sword of " +
                "darkness, the mightiest weapon of all.");
        System.out.println("Fight and take revenge for your loved ones, kill the mean Emperor!");
        GameLogic.anythingToContinue();
    }

    public static void printEnd(Player player) {
        GameLogic.clearConsole();
        GameLogic.printSeparator(2);
        System.out.println("Act IV Intro");
        GameLogic.printSeparator(2);
        System.out.println("Congratulations " + player.name + "! You defeated the " +
                "evil Emperor and saved the world!");
        GameLogic.printSeparator(2);
        System.out.println("This game was developed by Timi.");
    }

}

