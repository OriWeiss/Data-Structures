package Lab12;

import javax.xml.bind.annotation.XmlType;
import java.util.*;
import java.io.*;

/**
 * @author Ori Weiss
 * @version 4/17/2018
 * @modifiedBy Ori Weiss
 */
public class CheckInventory
{
    // TODO Project 2 Part1
    private DictionaryInterface<CarID, Integer> hashDictionary;
    private final int DEFAULT_CAPACITY = 5;
    private Random random;

    public CheckInventory(){
        this.random = new Random(101);
        createHashedDictionary();
    }
    public void createHashedDictionary(){
        this.hashDictionary = new HashedDictionary<>();

    }
    public boolean compareInventory(String file1, String file2) throws IOException{
        //TODO-NEEDTO
        File File1 = new File(file1);
        File File2 = new File (file2);
        Scanner file1Scanner = new Scanner(File1);
        Scanner file2Scanner = new Scanner(File2);
        while(file1Scanner.hasNextLine()){
            Scanner idScan =new Scanner(file1Scanner.nextLine()) ;
            String cSeq = idScan.next();
            long nSeq = idScan.nextLong();
            CarID ID = new CarID(cSeq,nSeq);
            hashDictionary.add(ID,1);
        }
        //hashDictionary.displayHashTable();
        while(file2Scanner.hasNextLine()){
            Scanner idScan =new Scanner(file2Scanner.nextLine()) ;
            String cSeq = idScan.next();
            long nSeq = idScan.nextLong();
            CarID currentKey = new CarID(cSeq,nSeq);
            Integer value = this.hashDictionary.getValue(currentKey);
            if(value != null){
                this.hashDictionary.add(currentKey,value -1);

            }
            else{
                this.hashDictionary.add(currentKey, -1);
            }
        }
       // hashDictionary.displayHashTable();
        file2Scanner.close();
        boolean zero = false;
        Iterator<Integer> iter = this.hashDictionary.getValueIterator();
        while(!zero && iter.hasNext()){
            Integer current = iter.next();
            if (current !=0){
                zero = true;
            }
        }
        return zero;
    }
    public TreeSet<CarID> generateContentAndSaveToRandomFile(int amount , String file) throws IOException{
        //TODO-NEEDTO
        PrintWriter printWriter = new PrintWriter(file);
        TreeSet <CarID> manifest = new TreeSet<>();
        for(int i = 0; i < amount; i++){
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder charSeq = new StringBuilder();
            //Random rnd = new Random();
            long numSeq= 1L;

            do {
                numSeq = random.nextLong();
            } while(numSeq < 0 || (int)Math.log10(numSeq) != CarID.NUMERIC_SEQUENCE_LENGTH);
            String charSeqString;
            for(int j = 0; j< CarID.CHARACTER_SEQUENCE_LENGTH; j++) {
                int index = (int) (this.random.nextInt(chars.length()));
                charSeq.append(chars.charAt(index));
            }
            charSeqString = charSeq.toString();
            CarID currentID = new CarID(charSeqString, numSeq );
            if(manifest.add(currentID)){
                printWriter.println(currentID);
            }
        }
        printWriter.close();

        return manifest;
    }
    public void saveSortedContentToSortedFile(TreeSet<CarID> tree, String file) throws IOException{
        PrintWriter printer = new PrintWriter(file);
        tree.forEach(CarID-> {
                printer.println(CarID);
        }) ;
    }
    public void createCorruptedFile(TreeSet<CarID> tree, String file) throws IOException{
        PrintWriter printer = new PrintWriter(file);
        tree.forEach(CarID-> {
            boolean randomRandy = this.random.nextBoolean();
            if(randomRandy = true){
                printer.println(CarID);
            }
        }) ;


    }
    // uncomment main when the class
    // skeleton is in place


    public static void main(String[] args)
    {
        String receivedFile = "randomFile.txt";
        String sentFile = "sortedFile.txt";
        String corruptedFile = "corruptedFile.txt";
        CheckInventory checker = new CheckInventory();

        try
        {
            System.out.println("How many CarIDs to generate?");
            Scanner keyboard = new Scanner(System.in);
            int amount = keyboard.nextInt();
            TreeSet<CarID> sortedSet = checker.generateContentAndSaveToRandomFile(amount, receivedFile);
            checker.saveSortedContentToSortedFile(sortedSet, sentFile);
            checker.createCorruptedFile(sortedSet, corruptedFile);
            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + receivedFile + "\" have the same elements ***");
            boolean same = checker.compareInventory(receivedFile, sentFile);
            System.out.println("--> the elements in files \"" + receivedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");


            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + corruptedFile + "\" have the same elements ***");
            checker.createHashedDictionary();
            same = checker.compareInventory(sentFile, corruptedFile);
            System.out.println("--> the elements in files \"" + corruptedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");

        } catch (IOException ioe)
        {
            System.out.println("There was an error in reading or opening the file: ");
            System.out.println(ioe.getMessage());
        } catch (InputMismatchException ime)
        {
            System.out.println(ime.getMessage());
        }
        System.out.println("\nBye!");
    }  // end main
}
