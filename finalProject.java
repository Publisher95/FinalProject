import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Try and make dungeon crawling like game
 *
 * @author Talmage McGehee and Micheal Gavina
 */
public class finalProject{

public static void heal(int[] playerStat, String[] bagName, int[] bagStat){

    

//Todo, fix this

}

public static void checkBag(String[] bagItemNames, int[] bagItemStat){
    int i = 0;
    System.out.println();
    
    for(i = 0; i < bagItemNames.length; i++){
        if(bagItemNames[i] != null){
            System.out.println(i+1 + "." + bagItemNames[i] + "\t" + bagItemStat[i]);
        }
    }
}


public static int battle(int enemyHealth, int enemyAtk, int enemyDef, int[] playerStats){
    Scanner inReader = new Scanner(System.in);

    String choice;

    System.out.print("Would you like physical or magic?");
    
    choice = inReader.next();
    boolean validChoice = false;
    
    if((choice.toUpperCase()).equals("PHYSICAL") || (choice.toUpperCase().equals("MAGIC"))){
        validChoice = true;
    }
    
    while (validChoice == false){
        System.out.println("That is not a vaild choice");
        choice = inReader.next();
        if((choice.toUpperCase()).equals("PHYSICAL") || (choice.toUpperCase().equals("MAGIC"))){
            validChoice = true;
        }
    }
 
    if (((choice.toUpperCase()).equals("PHYSICAL"))){
        System.out.println("Physical used");
        
        
    }
    
    else if (((choice.toUpperCase()).equals("MAGIC"))){
        System.out.println("Magic used");
        
        //enemyHealth -= magic;
        
    }



    return enemyHealth;

    
    
}
public static void finalBoss(int bossHealth, int bossDef, int[] playerStats, int[] bagStats, String[] bagName){
    Random rand = new Random();
    
    int bossAtk = rand.nextInt(24,30);
    Scanner inReader = new Scanner(System.in);
    
    String choice;
    choice = inReader.next(); //not sure why choice needs to be here, might be a bug later on
    boolean validChoice = false;
    while (validChoice == false){
        if((choice.toUpperCase()).equals("FIGHT") || (choice.toUpperCase()).equals("BAG") || (choice.toUpperCase()).equals("RUN")){
            validChoice = true;
        }
        else{
            System.out.println("This is not a vaild choice.");
            choice = inReader.next();
        }
    }

    while(playerStats[0] > 0 && bossHealth > 0){
        if((choice.toUpperCase()).equals("BAG")){
            checkBag(bagName, bagStats); 
            System.out.println("Would you like to use anything? (type none for nothing.)");
            String bag = inReader.next();
            //heal(playerStats);
            
            
        }
        else if ((choice.toUpperCase()).equals("FIGHT")){
            bossHealth = battle(bossHealth, bossAtk, bossDef, playerStats);
            //finish


        }
    }
}





public static void encounter(int[][] enemies, String[] nameMonster, int[] playerStats, String[] bagName, int[] bagStats){
    Random rand = new Random();
    Scanner inReader = new Scanner(System.in);
    int randomNum = rand.nextInt(0,2); // selects on of the three enemies
    String monsterName = nameMonster[randomNum];
    int monsterAtk = enemies[randomNum][0];
    int monsterDef = enemies[randomNum][1]; //selecting one of the three enemies
    int monsterHealth = rand.nextInt(5, 25); // randomly assigns health
   
    
    
    
    
    System.out.printf("You have encountered a %s with %d attack and %d defense\n", monsterName, monsterAtk, monsterDef);
    System.out.println("Would you like to do?\nFight, Bag, or Run.");
    String choice;
    choice = inReader.next(); //not sure why choice needs to be here, might be a bug later on
    boolean validChoice = false;
    while (validChoice == false){
        if((choice.toUpperCase()).equals("FIGHT") || (choice.toUpperCase()).equals("BAG") || (choice.toUpperCase()).equals("RUN")){
            validChoice = true;
        }
        else{
            System.out.println("This is not a vaild choice.");
            choice = inReader.next();
        }
    }
    while(playerStats[0] > 0 && monsterHealth > 0){
        if((choice.toUpperCase()).equals("BAG")){
            checkBag(bagName, bagStats);
            
            System.out.println("\nWould you like to do?\nFight, Bag, or Run.");
            choice = inReader.next();
        }
        else if ((choice.toUpperCase()).equals("FIGHT")){
            monsterHealth = battle(monsterHealth, monsterAtk, monsterDef, playerStats);
            
            System.out.println("\nWould you like to do?\nFight, Bag, or Run.");
            choice = inReader.next();
        }
        else if ((choice.toUpperCase()).equals("RUN")){
            int dice = rand.nextInt(1,6);
            if (dice == 1 || dice == 3 || dice == 6){
                System.out.println("You got away.");
                break;
            }
            else{
                System.out.println("You didn't get away.\n");
                System.out.println("Would you like to do?\nFight, Bag, or Run.");
                choice = inReader.next();

            }
        }else{
            System.out.println("This is not a vaild choice.");
            choice = inReader.next();
        }
    }
}
    
    // This is going to be a bitch to debug, RIP sleep
    
public static int recursionSpell(int damageLevel, int baseDamage){
    int recDam = baseDamage;

    recDam *= 2;
    if (damageLevel > 0){
        recDam += recursionSpell(damageLevel-1, recDam);
    }    
    if (damageLevel == 0){
        return recDam;
    }
    return recDam;

}

public static void pathChoosing(char[] pathChoices, Scanner scnr){
    String userPath;
    
    System.out.println("Hello adventurer! You are on a mightly quest to save the world!");
    System.out.println("However, first you must pick a path; There are three diverging");
    System.out.println("paths ahead, one forward, one to the right, and one to the left");
    System.out.println("  (Answer: Forward, Right, or Left(only first letter matters))");
    userPath = scnr.next();
    
    while(userPath.toLowerCase().charAt(0) != pathChoices[0] && userPath.toLowerCase().charAt(0) != pathChoices[1] && userPath.toLowerCase().charAt(0) != pathChoices[2]){
        System.out.println("That is not one of the path options, try again");
        userPath = scnr.next();
    }
    System.out.println("You have chosen the " + userPath + " path, let us begin");
}

public static int campainStart(int bossKillCount, int[][] enemies, String[] enemyName, int[] playerStats, int[] bagStats, String[] bagName, String currentPhyName, String currentMagName, int currentPhyDam, int currentMagDam){
    int roomLength = 100;
    int maxEncounter = 10;
    int stepCount = 0;
    int encounterCount = 0;
    int encounterTile;
    Random rand = new Random();
    
    
    
    if(bossKillCount != 0){
        roomLength += (roomLength * 3/4) * bossKillCount;
        maxEncounter += (maxEncounter * 3/4.0) * bossKillCount;
    }
    encounterTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
    
    while(stepCount < roomLength){
        if (encounterTile == stepCount && encounterCount < maxEncounter){
            System.out.print("You travel " + stepCount + " out of the " + roomLength + " you have been tasked with to");
            System.out.println(" defeat the boss, when you encounter a random enemy in your way");
            encounter(enemies, enemyName, playerStats, bagName, bagStats);
            
            encounterCount += 1;
            encounterTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
            System.out.println("encounterTile: " + encounterTile);
        }
        stepCount += 1;
        if(playerStats[0] <= 0 ){
            return 0;
        }
    }
    return 1;
}

    
    
    
public static void main(String args[]) throws IOException{
        
    Scanner scnr = new Scanner(System.in);
    int i;
    final int BAG_SIZE = 10;
    String[] bagItemNames = new String[BAG_SIZE];
    int[] bagItemStat = new int[BAG_SIZE];
    int health, defense, mana, gold, bossKillCount;
    int currentPhyDam, currentMagDam;
    String currentPhyName, currentMagName;
    char[] pathChoices = new char[3];
    String userPath;
    
    ArrayList<String> potionName = new ArrayList<String>();
    ArrayList<Integer> potionStat = new ArrayList<Integer>();
    ArrayList<String> physicalName = new ArrayList<String>();
    ArrayList<Integer> physicalStat = new ArrayList<Integer>();
    ArrayList<String> manaName = new ArrayList<String>();
    ArrayList<Integer> manaStat = new ArrayList<Integer>();
    ArrayList<Integer> manaCost = new ArrayList<Integer>();
   
    String[] enemiesName = {"skeleton", "goblin", "ogre"};
    int[][] enemiesStats = new int[3][2]; // This is the stats of the enemies, which will go skeleton, goblin, and orge. Read like row for each enemy and coulum for stats: 1st for damage, 2nd for defense.
    currentPhyName = "Knife";
    currentMagName = "wand";
    currentPhyDam = 2;
    currentMagDam = 5;
    


    enemiesStats[0][0] = 1;
    enemiesStats[0][1] = 3;
    enemiesStats[1][0] = 1;
    enemiesStats[1][1] = 2;
    enemiesStats[2][0] = 4;
    enemiesStats[2][1] = 0;
    
    FileInputStream potionFile = new FileInputStream("potionFile.txt");
    Scanner potionRead = new Scanner(potionFile);
    FileInputStream physicalFile = new FileInputStream("physicalFile.txt");
    Scanner physicalRead = new Scanner(physicalFile);
    FileInputStream manaFile = new FileInputStream("manaFile.txt");
    Scanner manaRead = new Scanner(manaFile);
    
    
    int[] playerStats = new int[5]; //Health, defense, mana, and gold, level 
    playerStats[0] = 50;
    playerStats[1] = 0;
    playerStats[2] = 15;
    playerStats[3] = 0;
    playerStats[4] = 4;
    
    
    
    
    health = 50; // these are redunate but had them for referencing
    defense = 0;
    mana = 15;
    gold = 0;
    bossKillCount = 0;
    
    pathChoices[0] = 'f';
    pathChoices[1] = 'r';
    pathChoices[2] = 'l';
    
    pathChoosing(pathChoices, scnr);
    

    while (potionRead.hasNext()){
        potionName.add(potionRead.next());
        potionStat.add(potionRead.nextInt());
    }
    while (physicalRead.hasNext()){
        physicalName.add(physicalRead.next());
        physicalStat.add(physicalRead.nextInt());
    }
    while (manaRead.hasNext()){
        manaName.add(manaRead.next());
        manaStat.add(manaRead.nextInt());
        manaCost.add(manaRead.nextInt());
    }
    
    System.out.println("As you travel this path, you might run into many things");
    System.out.printf("On your person you have a %s for physical damage, and %s for magic damage.\nPhysical damage is weaker overall, but hits more in defense, Magic damage does more overall, but hits weaker in defense.\n",currentPhyName, currentMagName);
    
    bagItemNames[0] = potionName.get(0);
    bagItemNames[1] = potionName.get(1);
    
    bagItemStat[0] = potionStat.get(0);
    bagItemStat[1] = potionStat.get(1);
    
    while (bossKillCount < 3){
        
        bossKillCount += campainStart(bossKillCount, enemiesStats, enemiesName, playerStats, bagItemStat, bagItemNames, currentPhyName, currentMagName, currentPhyDam, currentMagDam);
        if (playerStats[0] <= 0){
            System.out.println("You have failed to kill all three bosses and the final boss.\n You have been slain.");
            break;
        }
        int healthIncrease = bossKillCount * health;
        health += healthIncrease;
    }
    if(bossKillCount == 3){
        System.out.println("You have succeded on your quest! I hope you had fun!");
    }
  }
}
