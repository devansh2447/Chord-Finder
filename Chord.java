
/**
 * Write a description of class Chord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chord
{
    String type;//blank for root, sus2/sus4 for sustained, dim for diminished, oct for octave
    Scale scale;
    int numberOfNotes;
    String[] notes;

    public Chord(){

    }

    public Chord(String type, Scale scale){
        this.type = type;
        this.scale = scale;
    }

    public static boolean isInvalidType(String toCheck){
        return toCheck.equals("") == false && toCheck.equals("sus2") == false && toCheck.equals("sus4") == false && toCheck.equals("dim") == false && toCheck.equals("oct") == false;
    }

    public static Chord interpretChord(String interpret){
        Chord chord;
        String root = getKey(interpret);
        String mode = getMode(interpret);
        Scale scale = new Scale(root, mode);
        String type = getType(interpret);
        chord = new Chord(type, scale);
        int additional = 0;
        int inversion = 0;
        if(getAdditional(interpret) != ""){
            additional = Integer.parseInt(getAdditional(interpret));
        }
        if(getInversion(interpret) != ""){
            inversion = Integer.parseInt(getInversion(interpret));
        }
        int[] notes;
        String[] chordNotes = new String[4];
        if(type.equals("dim") == false){
            notes = getNotes(type);
            chordNotes = interpretNotes(chord.scale.notes, notes);
            chordNotes = interpretAdditional(chordNotes, chord.scale.notes, additional);
            chordNotes = eliminateDuplicate(chordNotes);
            chordNotes = invert(chordNotes, inversion);
        }
        else{
            if(isMinor(chord)){
                chordNotes[0] = chord.scale.notes[1];
                chordNotes[1] = chord.scale.notes[3];
                chordNotes[2] = getPrevious(chord.scale.notes[5]);
            }
            else{
                chordNotes[0] = chord.scale.notes[1];
                chordNotes[1] = getPrevious(chord.scale.notes[3]);
                chordNotes[2] = getPrevious(chord.scale.notes[5]);

            }
            chordNotes = interpretAdditional(chordNotes, chord.scale.notes, additional);
            chordNotes = eliminateDuplicate(chordNotes);
            chordNotes = invert(chordNotes, inversion);
        }
        chord.notes = chordNotes;
        return chord;
    }

    public static boolean isMinor(Chord check){
        return check.scale.mode.equals("dor") || check.scale.mode.equals("phr") || check.scale.mode.equals("aeo") || check.scale.mode.equals("loc") || check.scale.mode.equals("min");
    }

    public static String getPrevious(String note){
        String[] reference = Scale.getNotesReference();
        for(int iter = 1; iter < reference.length; iter++){
            if(note.equals(reference[iter])){
                return reference[iter - 1];
            }
        }
        return "";
    }

    public static String[] eliminateDuplicate(String[] check){
        int lastFilled;
        if(check[2] == null){
            lastFilled = 1;
        }
        else if(check[3] == null){
            lastFilled = 2;
        }
        else{
            lastFilled = 3;
        }
        if(contains(check, check[lastFilled], lastFilled)){
            check[lastFilled] = null;
        }
        return check;
    }

    public static boolean contains(String[] reference, String check, int stop){
        for(int iter = 0; iter < stop - 1; iter++){
            if(check.equals(reference[iter])){
                return true;
            }
        }
        return false;
    }

    public static String getKey(String reference){
        if(reference.charAt(1) == '#'){
            return reference.substring(0, 2);
        }
        else{
            return reference.substring(0, 1);
        }
    }

    public static String getMode(String reference){
        if(reference.charAt(1) == '#'){
            return reference.substring(2, 5);
        }
        else{
            return reference.substring(1, 4);
        }
    }

    public static String getType(String reference){
        int startPos;
        if(reference.charAt(1) == '#'){
            startPos = 5;
        }
        else{
            startPos = 4;
        }
        if(startPos == reference.length()){
            return "";
        }
        else if(isNumber(reference.charAt(startPos))){
            return "";
        }
        else{
            if(reference.contains("sus")){
                return reference.substring(startPos, startPos + 4);
            }
            else{
                return reference.substring(startPos, startPos + 3);
            }
        }
    }

    public static String getAdditional(String reference){
        if(isNumber(reference.charAt(reference.length() - 1)) && reference.charAt(reference.length() - 2) != 's' && reference.contains("inv") == false){
            return reference.charAt(reference.length() - 1) + "";
        }
        else if(reference.contains("inv") && reference.charAt(reference.length() - 6) != 's' && isNumber(reference.charAt(reference.length() - 5))){
            return reference.charAt(reference.length() - 5) + "";
        }
        return "";
    }

    public static String getInversion(String reference){
        if(reference.contains("inv")){
            return reference.charAt(reference.length() - 1) + "";
        }
        return "";
    }

    public static String[] invert(String[] toInvert, int inversionNumber){
        int firstNotFilled;
        if(toInvert[2] == null){
            firstNotFilled = 2;
        }
        else if(toInvert[3] == null){
            firstNotFilled = 2;
        }
        else{
            firstNotFilled = 3;
        }
        for(int iter = 0; iter < inversionNumber; iter++){
            String moveToEnd = toInvert[0];
            toInvert[0] = toInvert[1];
            toInvert[1] = toInvert[2];
            toInvert[firstNotFilled] = moveToEnd;
        }
        return toInvert;
    }

    public static String[] interpretNotes(String[] reference, int[] notes){
        String[] chord = new String[4];
        for(int iter = 0; iter < notes.length; iter++){
            chord[iter] = reference[notes[iter]];
        }
        return chord;
    }

    public static String[] interpretAdditional(String[] chord, String[] reference, int add){
        int assign;
        if(chord[2] == null){
            assign = 2;
        }
        else{
            assign = 3;
        }
        chord[assign] = reference[add];
        return chord;
    }

    public static int[] getNotes(String chordType){
        int[] forReturn;
        if(chordType.equals("")){
            forReturn = new int[] {1, 3, 5};
        }
        else if(chordType.equals("sus2")){
            forReturn = new int[] {1, 2, 5};
        }
        else if(chordType.equals("sus4")){
            forReturn = new int[] {1, 4, 5};
        }
        else{
            forReturn = new int[] {1, 8};
        }
        return forReturn;
    }

    public static boolean isValidDegree(char check){
        return check == '1' || check == '2' || check == '3' || check == '4' || check == '5' || check == '6' || check == '7' || check == '8';
    }

    public static boolean isNumber(char check){
        return check == '1' || check == '2' || check == '3' || check == '4' || check == '5' || check == '6' || check == '7' || check == '8' || check == '9' || check == '0';
    }
}
