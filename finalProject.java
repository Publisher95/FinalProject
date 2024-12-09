import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Try and make dungeon crawling like game
 *  We are going make video game 
 * @author Talmage McGehee and Micheal Gavina
 */
public class finalProject{

public static void heal(int[] playerStat, String[] bagName, int[] bagStat, String userItem){
    Scanner inReader = new Scanner(System.in);
    
    
    int i = 0, playerHeal = 0;
    
    if (userItem.toLowerCase().equals("healingpotion")){
        playerHeal = 0;
    }
    else if (userItem.toLowerCase().equals("magicpotion")){
        playerHeal = 2;
    }
    else{
        playerHeal = -1;
    }
    while(bagName[i] != null){
        if(userItem.toLowerCase().equals(bagName[i].toLowerCase())){
            playerStat[playerHeal] += bagStat[i];
            break;            
        }
        i += 1;
    }
}

public static void looting(int[] playerStats, String phyName, String magName, ArrayList<String> potionName, ArrayList<Integer> potionStat, ArrayList<String> physName, ArrayList<Integer> physDam, ArrayList<String> magNames, ArrayList<Integer> magDam, ArrayList<Integer> magCost){
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    int randNum = rand.nextInt(2);
    String choice;
    
    if(randNum == 0){
        randNum = rand.nextInt(potionName.size());
    }else if(randNum == 1){
        randNum = rand.nextInt(physName.size());
        
        System.out.println("You have found a " + physName.get(randNum) + " on the ground, that does " + physDam.get(randNum) + ". Will you replace your current physical weapon with it?\n(Yes or No)");
        choice = input.next();
        
        if(choice.toLowerCase().charAt(0) == 'y'){
            playerStats[5] = magDam.get(randNum);
        }
    }else{
        randNum = rand.nextInt(magNames.size());
        
        System.out.println("You have found a " + magNames.get(randNum) + " on the ground, that does " + magDam.get(randNum) + " that costs " + magCost.get(randNum) + ". Will you replace your current magic weapon with it?\n(Yes or No)");
        choice = input.next();
        
        if(choice.toLowerCase().charAt(0) == 'y'){
            playerStats[6] = magDam.get(randNum);
            playerStats[7] = magCost.get(randNum);
        }
    }
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

public static int battle(int enemyHealth, int enemyAtk, int enemyDef, int[] playerStats, String phyName, String magName){
    Scanner inReader = new Scanner(System.in);

    String choice;
    int orgEnemyHealth = enemyHealth;
    int magicAtk = -(3 * enemyDef/4) + playerStats[6] ;
    int phyAtk =  -(enemyDef/2) + playerStats[5];
    
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
        System.out.printf("You currently have a %s, which will hit for, %d", phyName, playerStats[5]);
        enemyHealth -= phyAtk;
        
        if(enemyHealth >= orgEnemyHealth){
            enemyHealth = orgEnemyHealth;
            System.out.println("Your attack did no damage :(");
        }
    }
    else if (((choice.toUpperCase()).equals("MAGIC"))){
        System.out.printf("You currently have a %s, which will hit for, %d", magName, playerStats[6]);
        playerStats[2] -= 3;
        enemyHealth -= magicAtk;
        
        if(enemyHealth >= orgEnemyHealth){
              enemyHealth = orgEnemyHealth;
              System.out.println("Your attack did no damage :(");
        }
    }
    return enemyHealth;
}


public static void boss(int bossHealth, int bossDef, int[] playerStats, int[] bagStats, String[] bagName, String phyName, String magName){
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
            while(true){
                if ((bag.toUpperCase().equals("MAGICPOTION")) || (bag.toUpperCase().equals("HEALTHPOTION"))){
                    break;    
                }
                else{
                    System.out.println("That is not a vaild answer.");
                    bag = inReader.next();
                }
            }            
        }
        else if ((choice.toUpperCase()).equals("FIGHT")){
            bossHealth = battle(bossHealth, bossAtk, bossDef, playerStats, phyName, magName);
        }
    }
}





public static void encounter(int[][] enemies, String[] nameMonster, int[] playerStats, String[] bagName, int[] bagStats, String phyName, String magName){
    System.out.println("Encounter triggered");
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
            System.out.println("Would you like to use any items? (Type none for no items)");
            String userItem = inReader.next();
            while(true){
                if ((userItem.toUpperCase().equals("MAGICPOTION")) || (userItem.toUpperCase().equals("HEALTHPOTION")) || (userItem.toUpperCase().equals("NONE"))){
                    break;    
                }
                else{
                    System.out.println("That is not a vaild answer.");
                    userItem = inReader.next();
                }
            }      
            heal(playerStats, bagName, bagStats, userItem);
            System.out.println("\nWould you like to do?\nFight, Bag, or Run.");
            choice = inReader.next();
        }
        else if ((choice.toUpperCase()).equals("FIGHT")){
            monsterHealth = battle(monsterHealth, monsterAtk, monsterDef, playerStats, phyName, magName);
            System.out.println("The monster is down to " + monsterHealth);
            
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
                System.out.println("Would you like to do?\nFight, Bag, or Run.");
                choice = inReader.next();
            }
        }
        else{
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

public static int campainStart(int bossKillCount, int[][] enemies, String[] enemyName, int[] playerStats, int[] bagStats, String[] bagName, String phyName, String magName, ArrayList<String> potionName, ArrayList<Integer> potionStat, ArrayList<String> physName, ArrayList<Integer> physDam, ArrayList<String> magNames, ArrayList<Integer> magDam, ArrayList<Integer> magCost){
    int roomLength = 100;
    int maxEncounter = 10;
    int stepCount = 0;
    int encounterCount = 0;
    int encounterTile;
    Random rand = new Random();
    int bossHealth = 50;
    int bossDef = 3;
    int lootTile;
    int maxLoots = 7;
    int lootCount = 0;
    int maxRuns = playerStats[4];
    //Health, defense, mana, gold, runLimit, physical, magic damage, and magic cost
    

    
    if(bossKillCount != 0){
        roomLength += (roomLength * 3/4.0) * bossKillCount;
        maxEncounter += (maxEncounter * 3/4.0) * bossKillCount;
        maxLoots += (maxLoots * 3/4.0) * bossKillCount;
        bossHealth += (bossHealth * 1/2.0) * bossKillCount;
        bossDef += (bossDef * playerStats[5]/8.0);
        maxRuns += (maxRuns * 3/4.0) * bossKillCount; 
    }
    
    encounterTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
    lootTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
    System.out.println("Encounter tile: " + encounterTile);
    
    while(stepCount < roomLength){
        if (encounterTile == stepCount && encounterCount < maxEncounter){
            System.out.print("You travel " + stepCount + " out of the " + roomLength + " you have been tasked with to");
            System.out.println(" defeat the boss, when you encounter a random enemy in your way");
            System.out.println("Trigger the encounter");
            encounter(enemies, enemyName, playerStats, bagName, bagStats, phyName, magName);
            
            encounterCount += 1;
            encounterTile = rand.nextInt(roomLength - stepCount + 1) + stepCount;
            System.out.println("encounterTile: " + encounterTile);
        }
        
        if(lootTile == stepCount && lootCount < maxLoots){
            looting(playerStats, phyName, magName, potionName, potionStat, physName, physDam, magNames, magDam, magCost);
        }
        
        stepCount += 1;
        if(playerStats[0] <= 0 ){
            return 0;
        }  
        
        boss(bossHealth, bossDef, playerStats, bagStats, bagName, phyName, magName);
    }
    return 1;
}


public static void finalBoss(){
    System.out.println("Upon beating the three bosses, you have now been summoned to the Big Bad, the evil that rules over this land.\nHowever, in your travels you have learned a powerful spell that can destroy any evil.");
    System.out.println("The spell that builds upon it's self to vanquish any evil, The Light Spell.");
    System.out.println("This boss appears to have a lot of defense and a lot health, but it will be no match for The Light Spell.");
    
    int Health = 1000;
    
    Health -= recursionSpell(7, 14);
    System.out.printf("You raise up your hand, the energy builds from the base, and before the foe can strike, a powerful ray of pure light vaporizes the foe doing a total of %d damage", recursionSpell(7,14));
    
    
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
    
    
    int[] playerStats = new int[8]; 
    
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
    
    //Health, defense, mana, gold, runLimit, physical, magic damage, and magic cost
    playerStats[0] = 50;
    playerStats[1] = 0;
    playerStats[2] = 15;
    playerStats[3] = 0;
    playerStats[4] = 4;
    playerStats[5] = physicalStat.get(0);
    playerStats[6] = manaStat.get(0);
    playerStats[7] = manaCost.get(0);
    
    
    
    while (bossKillCount < 3){
        bossKillCount += campainStart(bossKillCount, enemiesStats, enemiesName, playerStats, bagItemStat, bagItemNames, currentPhyName, currentMagName, potionName, potionStat, physicalName, physicalStat, manaName, manaStat, manaCost);
        if (playerStats[0] <= 0){
            System.out.println("You have failed to kill all three bosses and the final boss.\n You have been slain.");
            break;
        }
        int healthIncrease = bossKillCount * playerStats[0];
        playerStats[0] += healthIncrease;
    }
    
    
    if(bossKillCount == 3){
        finalBoss();
        System.out.println("\nYou have succeded on your quest! I hope you had fun!\n");
    }
  }
}
