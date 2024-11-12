import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Write a description of class finalProject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class finalProject{

public static int heal(){
    
    return 0;
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
        FileInputStream itemFile = new FileInputStream("itemFile.txt");
        
        
        
        
        
        
    }
}
