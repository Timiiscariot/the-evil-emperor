package theevilemperor;

import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;

    public static boolean isRunning;

    //random encounters
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};
    public static String[] enemies = {"Troll", "Troll", "Ogre", "Ogre", "Stone Elemental"};

    public static int place = 0, act = 1;
    public static String[] places = {"Everlasting journey in Mountains",
            "Haunted Landlines", "Castle of Evil Emperor", "Throne room of Evil Emperor"};


    // get user input
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    // method to simulate clearing out the console
    public static void clearConsole() {
        for (int i = 0; i < 5; i++)
            System.out.println();
    }

    // method to print a separator with length n
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++)
            System.out.println("*******************");
        System.out.println();
    }

    // method to print heading
    public static void printHeading(String title) {
        printSeparator(2);
        System.out.println(title);
        printSeparator(2);
    }

    // method to stop the game until user enters anything
    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    //method to start the game
    public static void startGame() {
        boolean nameSet = false;
        String name;
        //print title screen
        clearConsole();
        printSeparator(2);
        System.out.println("THE EVIL EMPEROR");
        System.out.println("A TEXT RPG BY TIMI");
        printSeparator(3);
        anythingToContinue();

        // getting the player's name
        do {
            clearConsole();
            printHeading("What's your name?");
            name = scanner.next();
            //asking the player if he wants to correct his choice
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = readInt("-> ", 2);
            if (input == 1)
                nameSet = true;

        } while (!nameSet);

        // print story here

        Story.printIntro();

        //create new player object with the name
        player = new Player(name);

        Story.printFirstIntro();

        isRunning = true;

        //start main game loop
        gameLoop();
    }

    public static void checkAct() {
        //change acts based on xp
        if (player.xp >= 10 && act == 1) {
            //increment act and place
            act = 2;
            place = 1;
            //story
            Story.printFirstIntro();
            //let the player level up
            player.chooseTrait();

            //assign new values to enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Troll";
            enemies[2] = "Wolves";
            enemies[3] = "Henchmen of the Evil Emperor";
            enemies[4] = "Donald Trump";
            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Shop";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";


        } else if (player.xp >= 15 && act == 2) {
            act = 3;
            place = 2;
            //story
            Story.printSecondIntro();
            //let the player level up
            player.chooseTrait();

            enemies[0] = "Zombie";
            enemies[1] = "Ghost";
            enemies[2] = "T-Rex";
            enemies[3] = "Necromorph";
            enemies[4] = "Bogeyman";
            //assign new values to encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Rest";
            encounters[3] = "Battle";
            encounters[4] = "Shop";
            //heal the player
            player.hp = player.maxHp;

        } else if (player.xp >= 20 && act == 3) {
            act = 4;
            place = 3;
            //story
            Story.printThirdIntro();
            //let the player level up
            player.chooseTrait();
            //story
            Story.printFourthIntro();
            //heal the player
            player.hp = player.maxHp;
            //final battle
            finalBattle();


        }
    }

    //calculate a random encounter
    public static void randomEncounter() {
        int encounter = (int) (Math.random() * encounters.length);
        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        } else if (encounters[encounter].equals("Rest")) {
            takeRest();
        } else {
            shop();
        }
    }

    public static void continueJourney() {
        checkAct();
        //check if game is not in the last act
        if (act != 4)
            randomEncounter();
    }

    public static void characterInfo() {
        clearConsole();
        printHeading("Character Info");
        System.out.println(player.name + "\tHP: " + player.hp + " / " + player.maxHp);
        printSeparator(2);
        System.out.println("XP " + player.xp + "\tGold: " + player.gold);
        printSeparator(2);
        System.out.println("# of Potions: " + player.pots);
        printSeparator(2);


        if (player.numAtkUpgrades > 0) {
            System.out.println("Offensive trait: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
            printSeparator(2);

        }
        if (player.numDefUpgrades > 0) {
            System.out.println("Defensive trait: " + player.defUpgrades[player.numDefUpgrades - 1]);
        }
        anythingToContinue();
    }

    public static void shop() {
        clearConsole();
        printHeading("You meet a mysterious stranger.\nHe offers you something:");
        int price = (int) (Math.random() * (10 + player.pots * 3) + 10 + player.pots);
        System.out.println(" - Magic Potion - " + price + " gold.");
        printSeparator(2);
        System.out.println("Do you want to buy one?\n1. Yes!\n2. No, thanks.");
        int input = readInt("->", 2);
        if (input == 1) {
            clearConsole();
            if (player.gold >= price) {
                printHeading("You bought a magical potion for " + price +
                        " gold.");
                player.pots++;
                player.gold -= price;
            } else
                printHeading("You don't have enough gold to buy this... ");
            anythingToContinue();

        }

    }

    public static void takeRest() {
        clearConsole();
        if (player.restsLeft >= 1) {
            printHeading("Do you want to take a rest? " + player.restsLeft + " rest(s) left.");
            System.out.println("1. Yes\n2. No, not now.");
            int input = readInt("->", 2);
            if (input == 1) {
                clearConsole();
                if (player.hp < player.maxHp) {
                    int hpRestored = (int) (Math.random() * (player.xp / 4 + 1) + 10);
                    player.hp += hpRestored;
                    if (player.hp > player.maxHp)
                        player.hp = player.maxHp;
                    System.out.println("You took a rest and restored up to "
                            + hpRestored + " health.");
                    System.out.println("You are now at " + player.hp
                            + " health.");
                    player.restsLeft--;
                }

            } else
                System.out.println("You are at full health. You don't need to rest now.");
            anythingToContinue();
        }

    }

    //random battle
    public static void randomBattle() {
        clearConsole();
        printHeading("You encountered an evil minded creature." +
                " You will have to fight it!");
        anythingToContinue();
        //creating new enemy
        battle(new Enemy(enemies[(int) (Math.random() * enemies.length)], player.xp));
    }

    public static void battle(Enemy enemy) {
        while (true) {
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp
                    + "/" + enemy.maxHp);
            printHeading(player.name + "\nHP " + player.hp
                    + "/" + player.maxHp);
            System.out.println("Choose an action:");
            printSeparator(2);
            System.out.println("1. Fight\n2. Use Potion\n3. Run Away");
            int input = readInt("->", 3);
            if (input == 1) {
                int dmg = player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - player.defend();
                if (dmgTook < 0) {//check if dmgTook is not negative
                    //add some dmg if player defends very well
                    dmg -= dmgTook / 2;
                    dmgTook = 0;
                }
                if (dmg < 0)
                    dmg = 0;
                player.hp -= dmgTook;
                enemy.hp -= dmg;


                clearConsole();
                printHeading("BATTLE");
                System.out.println("You dealt " + dmg + " damage to the "
                        + enemy.name + ".");
                printSeparator(2);
                System.out.println("The " + enemy.name + " dealt "
                        + dmgTook + " damage to you.");
                anythingToContinue();

                if (player.hp <= 0) {
                    playerDied();
                    break;
                } else if (enemy.hp <= 0) {
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");
                    player.xp += enemy.xp;
                    System.out.println("You have earned " + enemy.xp + " XP!");
                    boolean addRest = (Math.random() * 5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random() * enemy.xp);
                    if (addRest) {
                        player.restsLeft++;
                        System.out.println("You have earned the chance to get " +
                                "an additional rest.");
                    }
                    if (goldEarned > 0) {
                        player.gold += goldEarned;
                        System.out.println("You collect " + goldEarned + " gold " +
                                "from the " + enemy.name + "'s corpse!");
                    }

                    anythingToContinue();
                    break;
                }

            } else if (input == 2) {
                //drink potion
                clearConsole();
                if (player.pots > 0 && player.hp < player.maxHp) {
                    printHeading("Do you want to drink a potion? ("
                            + player.pots + " left.)");
                    System.out.println("1. Yes\n2. No, maybe later");
                    input = readInt("->", 2);
                    if (input == 1) {
                        player.hp = player.maxHp;
                        clearConsole();
                        printHeading("You drank a magic potion. It restored your health" +
                                " back to " + player.maxHp);
                        anythingToContinue();
                    }

                } else {
                    System.out.println("You don't have any potions to " +
                            "drink or you're at full health.");
                    anythingToContinue();

                }
            } else {
                //run away
                clearConsole();

                if (act != 4) {
                    // chance of 35% to escape:
                    if (Math.random() * 10 + 1 <= 3.5) {
                        printHeading("You ran away from the " + enemy.name
                                + "!");
                        anythingToContinue();
                        break;
                    } else {
                        printHeading("You did not managed to escape.");
                        //damage taken
                        int dmgTook = enemy.attack();
                        System.out.println("In your hurry you took "
                                + dmgTook + " damage!");
                        anythingToContinue();
                        if (player.hp <= 0)
                            playerDied();

                    }

                } else {
                    printHeading("YOU CANNOT ESCAPE THE EVIL EMPEROR!");
                    anythingToContinue();
                }

            }
        }
    }


    public static void printMenu() {
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action:");
        printSeparator(2);
        System.out.println("1. Continue on your journey");
        System.out.println("2. Character info");
        System.out.println("3. Exit Game");

    }

    public static void finalBattle() {
        battle(new Enemy("THE EVIL EMPEROR", 300));
        Story.printEnd(player);
        isRunning = false;
    }

    public static void playerDied() {
        clearConsole();
        printHeading("You died :(");
        printHeading("You earned " + player.xp + " XP on your journey." +
                " Try to earn more next time!");
        isRunning = false;
    }


    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = readInt("->", 3);
            if (input == 1) {
                continueJourney();
            } else if (input == 2) {
                characterInfo();
            } else
                isRunning = false;
        }

    }

}

