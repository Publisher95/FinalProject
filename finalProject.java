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

public static int heal(int health, int potionStat){
    
    return 0;
}

public static void checkBag(String[] bagItemNames, int[] bagItemStat){
    int i = 0;
    
    while(bagItemNames[i] != null){
        System.out.println(i+1 + ". " + bagItemNames[i] + "\t" + bagItemStat[i]);
        i += 1;
    }
}


public static void encounter(int steps){
    for(int i = 0; i < steps; i++){
     
        
    }
}
    
    
    
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
    
    while(userPath.charAt(0) != pathChoices[0] && userPath.charAt(0) != pathChoices[1] && userPath.charAt(0) != pathChoices[2]){
        System.out.println("That is not one of the path options, try again");
        userPath = scnr.next();
    }
    System.out.println("You have chosen the " + userPath + " path, let us begin");
}

public static int campainStart(int bossKillCount){
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
    System.out.println("encounterTile: " + encounterTile);
    while(stepCount < roomLength){
        if (encounterTile == stepCount && encounterCount < maxEncounter){
            System.out.println("You travel " + stepCount + " out of the " + roomLength + " you have been tasked with to");
            System.out.println("defeat the boss, when you encounter a random enemy in your way");
            encounterCount += 1;
            encounterTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
        }
        stepCount += 1;
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
   
    String[] enemiesName = {"skeleton", "goblin", "orge"};
    int[][] enemiesStats = {1, 3} , {2, 1}, {3, 1}; // This is the stats of the enemies, which will go skeleton, goblin, and orge.
    
    FileInputStream potionFile = new FileInputStream("potionFile.txt");
    Scanner potionRead = new Scanner(potionFile);
    FileInputStream physicalFile = new FileInputStream("physicalFile.txt");
    Scanner physicalRead = new Scanner(physicalFile);
    FileInputStream manaFile = new FileInputStream("manaFile.txt");
    Scanner manaRead = new Scanner(manaFile);
    
    
    
    health = 50;
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
    
    while (bossKillCount < 3){
        
        bossKillCount += campainStart(bossKillCount);
        int healthIncrease = bossKillCount * health;
        health += healthIncrease;
    }
  }
}
