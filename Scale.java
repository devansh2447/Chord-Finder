
/**
 * Write a description of class Scale here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Scale
{
    String root;
    String mode;
    String[] notes;
    
    public Scale(String root, String mode){
        this.root = root;
        this.mode = mode;
        int[] steps;
        if(mode.equals("min")){
            steps = getMinor();
        }
        else{
            int start = interpretMode(mode);
            steps = cropArray(getReference(), start);
        }
        String[] notesReference = getNotesReference();
        String[] notes = cropStringArray(notesReference, getStartPosition(notesReference, root));
        this.notes = interpretSteps(steps, notes);
    }
    
    public static String[] interpretSteps(int[] steps, String[] reference){
        String[] forReturn = new String[9];
        int position = 1;
        for(int iter = 1; iter < 9; iter++){
            position = position + steps[iter - 1];
            forReturn[iter] = reference[position];
        }
        forReturn[8] = forReturn[8] + "+1oct";
        return forReturn;
    }
    
    public static int getStartPosition(String[] reference, String check){
        for(int iter = 1; iter < reference.length; iter++){
            if(check.equals(reference[iter])){
                return iter;
            }
        }
        return 0;
    }
    
    public static String[] cropStringArray(String[] toCrop, int start){
        String[] forReturn = new String[14];
        for(int iter = 0; iter < 13; iter++){
            forReturn[iter + 1] = toCrop[iter + start];
        }
        return forReturn;
    }
    
    public static int[] cropArray(int[] toCrop, int start){
        int[] forReturn = new int[8];
        for(int iter = 0; iter < 7; iter++){
            forReturn[iter + 1] = toCrop[iter + start];
        }
        return forReturn;
    }
    
    public static int interpretMode(String mode){
        //gives starting element for reference
        if(mode.equals("maj")){
            return 1;
        }
        else if(mode.equals("dor")){
            return 2;
        }
        else if(mode.equals("phr")){
            return 3;
        }
        else if(mode.equals("lyd")){
            return 4;
        }
        else if(mode.equals("mix")){
            return 5;
        }
        else if(mode.equals("aeo")){
            return 6;
        }
        else if(mode.equals("loc")){
            return 7;
        }
        else{
            System.out.println("Invalid mode, switching to major...");
            return 1;
        }
    }
    
    public static String[] getNotesReference(){
        String[] forReturn = new String[26];
        forReturn[0] = "B";
        forReturn[1] = "C";
        forReturn[2] = "C#";
        forReturn[3] = "D";
        forReturn[4] = "D#";
        forReturn[5] = "E";
        forReturn[6] = "F";
        forReturn[7] = "F#";
        forReturn[8] = "G";
        forReturn[9] = "G#";
        forReturn[10] = "A";
        forReturn[11] = "A#";
        forReturn[12] = "B";
        forReturn[13] = "C";
        forReturn[14] = "C#";
        forReturn[15] = "D";
        forReturn[16] = "D#";
        forReturn[17] = "E";
        forReturn[18] = "F";
        forReturn[19] = "F#";
        forReturn[20] = "G";
        forReturn[21] = "G#";
        forReturn[22] = "A";
        forReturn[23] = "A#";
        forReturn[24] = "B";
        forReturn[25] = "C";
        return forReturn;
    }
    
    public static int[] getReference(){
        int[] forReturn = new int[15];
        forReturn[1] = 2;
        forReturn[2] = 2;
        forReturn[3] = 1;
        forReturn[4] = 2;
        forReturn[5] = 2;
        forReturn[6] = 2;
        forReturn[7] = 1;
        forReturn[8] = 2;
        forReturn[9] = 2;
        forReturn[10] = 1;
        forReturn[11] = 2;
        forReturn[12] = 2;
        forReturn[13] = 2;
        forReturn[14] = 1;
        return forReturn;
    }
    
    public static int[] getMinor(){
        int[] forReturn = new int[8];
        forReturn[1] = 2;
        forReturn[2] = 1;
        forReturn[3] = 2;
        forReturn[4] = 2;
        forReturn[5] = 1;
        forReturn[6] = 3;
        forReturn[7] = 1;
        return forReturn;
    }
}
