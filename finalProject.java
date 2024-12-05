import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Try and make dungeon crawling like game
 *
 * @author Talmage McGehee and Micheal Gavina
 */
public class finalProject{

public static int heal(){
    
    return 0;
}

public static void checkBag(String[] bagItemNames, int[] bagItemStat){
    for(int i = 0; i < bagItemNames.length; i++){
        while(bagItemNames[i] != null){
            System.out.println(i+1 + ". " + bagItemNames[i] + "\t" + bagItemStat[i]);
        }
    }
}


public static void encounter(){
    
    
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

    
    
    
    
public static void main(String args[]) throws IOException{
        
    Scanner scnr = new Scanner(System.in);
    int i;
    final int BAG_SIZE = 10;
    String[] bagItemNames = new String[BAG_SIZE];
    int[] bagItemStat = new int[BAG_SIZE];
    int health, defense, mana, gold;
    int currentPhyDam, currentMagDam;
    String currentPhyName, currentMagName;
    
    
    ArrayList<String> potionName = new ArrayList<String>();
    ArrayList<Integer> potionStat = new ArrayList<Integer>();
    ArrayList<String> physicalName = new ArrayList<String>();
    ArrayList<Integer> physicalStat = new ArrayList<Integer>();
    ArrayList<String> manaName = new ArrayList<String>();
    ArrayList<Integer> manaStat = new ArrayList<Integer>();
    ArrayList<Integer> manaCost = new ArrayList<Integer>();
    
    String userPath;
    
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
    
    System.out.println("Hello adventurer! You are on a mightly quest to save the world! However, first you must pick a path; there are three diverging paths ahead, one forward, one to the right, and one to the left.");
    userPath = scnr.next();

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
    
}
}
