import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files; //Imports file. Uses built in methods for working the actual files and directories and creating input and output streams
import java.nio.file.Path; //Imports the path. Needed for creation of hierarchy of file path. Provides info about file location, size, type and permissions etc.
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Lab_12_File_Away
{
    public static void main(String[] args)
    {

        JFileChooser fileChooser = new JFileChooser(); //Built in class method used to show dialog box of a directory with files to choose from
        File chosenFile; //Variable used for the selected file to be read
        String readLine = ""; //Empty variable to store  the lines of the file into
        ArrayList<String> lines = new ArrayList<>(); //Array used to put lines in that are read from the chosen file
        int wordCounter = 0; //Initializing word counting variable
        int charCount = 0; //Initializing character counting variable
        int wordCount = 0; //Initializing word counting variable
        int lineCount = 0;  // Initializing line counter variable

        try //Start of Try block with program in it
            {
                File workingDirectory = new File(System.getProperty("user.dir")); //Use file class and built in method to set as current working directory
                fileChooser.setCurrentDirectory(workingDirectory); //Force user to choose file from working directory initially.
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) //This block executes if user picks a file. If not then goes to Else block
                    {
                        chosenFile = fileChooser.getSelectedFile(); //Call method to get the file that users chooses
                        Path file = chosenFile.toPath(); //Build the path to the chosen file
                        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE)); //Create stream with Files class using variable "in" to send it through the pipe
                        BufferedReader fileReader = new BufferedReader(new InputStreamReader(in)); //Create bufferedReader object used to read the lines of the file using InputStream variable as parameter
                        final String fileName = chosenFile.getName(); //Call method to get name of chosen file and assign to variable
                        while(fileReader.ready()) //Start of loop to read the file. If buffer is not empty or not at end of file, then this keeps executing
                            {
                                readLine = fileReader.readLine(); //Line is read and stored in variable
                                lines.add(readLine);  //read the line from the variable and write into the array
                                lineCount++; //This is the line counter, increments by 1 per line read
                                System.out.printf("\nlineCount %4d %-60s ", lineCount, readLine); //Output the line read to the screen. New line is added per line read and formats the line number and the record/data in line
                            }
                        fileReader.close(); // Closes the file after it has been read and flush the buffer. Prevents file from being locked
                        System.out.println("\n\nData file read!"); //Output to user to notify that their file has been read completely

                        for(String l:lines) //This loop processes the report by reading the lines stored in the array to the end of the array
                            {
                                wordCounter = l.split(",").length; // Split the record into the fields and count the separators in the line
                                wordCount = wordCount + wordCounter; //Get total word count by multiplying words per line by lines in the file

                                for(int i = 0; i < readLine.length(); i++) //Loop to the end of the line for each line in file
                                    {
                                        charCount++; //Counter for characters
                                    }
                            }
                        System.out.println("Name of file is " + fileName); //Output file name to user
                        System.out.println("Word count is " + wordCount); //Output total word count in the file to the user
                        System.out.println("Total lines read is " + lineCount); //Output of total lines the file has to user
                        System.out.println("Total character count in the file is " + charCount); //Output the total character count in all lines of the file to user
                    }
                    else  // user closed the file dialog without choosing a file
                        {
                            System.out.println("Failed to choose a file to process"); //Output to user that they did not pick a file
                            System.out.println("Run the program again!");//Output to user
                            System.exit(0); //Exit program with no error
                        }
            }  // End of the TRY Block
        //Catch blocks only run if Try block has an exception
        catch (FileNotFoundException e) //Catch block if file pick was not available. This really isn't necessary if user is choosing files from dialog box that show that they exists. This may be helpful if someone else removes file during the choosing process
            {
                System.out.println("File was not found!"); //Output to user that the file they chose does not exists
                e.printStackTrace(); //Return error message
            }
        catch (IOException e) //Catch block
            {
                e.printStackTrace(); //Return error message
            }

    }
}