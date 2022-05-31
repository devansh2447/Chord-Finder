
/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Interface
{
    public static void main(String args[]){
        getInput();
    }
    
    public static void getInput(){
        System.out.println("Welcome to the chord note finder.");
        System.out.println("To get the notes, type out the chord in this format: root note + scale mode + chord type + additional degree + inversion number");
        System.out.println("For the root note, only natural and sharp notes are permitted.");
        System.out.println("For the mode, type maj for major, dor for dorian, phr for phrygian, lyd for lydian, mix for mixolydian, aeo for aeolian, loc for locrian and min for natural minor.");
        System.out.println("For the chord type, leave it blank for the root chord, enter sus2/ sus4 for a sustained chord, enter dim for a diminished chord and oct for an octave.");
        System.out.println("Only one additional degree is allowed, and it cannot be larger than 8 (duplicate degrees are eliminated)");
        System.out.println("For the inversion, enter inv + your inversion number, leave this blank if you don't want an inverted chord.");
        System.out.println("Enter your chord here.");
        Scanner input = new Scanner(System.in);
        String chordString = input.nextLine();
        Chord chord = Chord.interpretChord(chordString);
        System.out.print("Chord: ");
        printArray(chord.notes);
        checkIfContinue();
    }
    
    public static void printArray(String[] toPrint){
        for(int iter = 0; iter < toPrint.length; iter++){
            if(toPrint[iter] != null){
                System.out.print(toPrint[iter] + " ");
            }
        }
        System.out.println();
    }
    
    public static void checkIfContinue(){
        Scanner check = new Scanner(System.in);
        System.out.println("Press y to continue, and any other key to exit");
        String input = check.nextLine();
        if(input.equalsIgnoreCase("y")){
            getInput();
        }
        else{
            System.out.flush();
            System.exit(0);
        }
    }
}
