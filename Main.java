/*
1. Base code - Sarah
2. On beginning of program - allow user to choose shift. - Louise
3. Add optional difficulty levels for guessing game. - Ty
4. Beautification. - Rebecca


changes made 29/03 -
- swapped break statements from lines 71-81 to a boolean controller
- changed count (on line 47) to start at 1 instead of 0
- line 135 - changed guesses to 0 to leave the loop instead of using break statement

change made 30/03 -
- changed choice println statement to the same format of levelChosen
- changed format of new encrypted name, when choosing shift to same format as in guessing game
*/
import java.util.*;

class Main {
  public static void main(String[] args) {
    // create instance of scanner and random
    Scanner keyboard = new Scanner(System.in);
    Random randomNumber = new Random();

    // create array of alphabet
    char[] ALPHABET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z' };

    // variables
    String name, cipher = "", outcome = ""; // name = user input, cipher = encrypted input
    int shift, len, sum, guess, count, guesses; // shift, len = length of input, sum = index of array[i] + shift,
    // guesses = counts guesses,
    char nameCh, check, cont = 'n'; // nameCh = name.charAt[i] - gets each character of input individually, check =
                                    // alphabet[x] - gets current element of array to compare to current char from
                                    // input
    boolean levelChosen = false; // Makes sure they cant leave till they have chosen a level

    // print introduction
    System.out.println("Caesar Cipher");
    do {
      System.out.print(
          "\nWould you like to play the GUESSING GAME or choose the shift to encrypt your name?\n\nGuessing Game (Enter 1): \nChoose shift (Enter 2): \n");
      int choice = keyboard.nextInt();

      System.out.println(
          "\n\t=========================================== CAESAR CIPHER ==============================================");
      System.out.println(
          "\n\t| A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |");
      System.out.println(
          "\t| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11| 12| 13| 14| 15| 16| 17| 18| 19| 20| 21| 22| 23| 24| 25|");
      System.out.println(
          "\n\t========================================================================================================\n");

      if (choice == 1) {
        cipher = "";
        sum = 0;
        count = 1;
        guesses = 3;

        System.out.println("\n\t\t\t\t\t=======================================");
        System.out.println("\t\t\t\t\t     = = G U E S S I N G  G A M E = = ");
        System.out.println("\t\t\t\t\t=======================================\n");
        // Levels:
        System.out.println("\t\t\t\t\t\t= = CHOOSE YOUR LEVEL = =");
        System.out.println("\t\t\t\t\t=======================================");
        System.out.println("\t\t\t=======================================================================");
        System.out.println("\n\t\t\t|Level Easy: 5 Guesses| |Level Medium: 3 Guesses| |Level Hard: 1 Guess|");
        System.out.println("\n\t\t\t=======================================================================");
        System.out.println("\nLevel Easy (Enter 1):\nLevel Medium (Enter 2):\nLevel Hard (Enter 3):\n");
        levelChosen = false;
        while (levelChosen == false) {

          System.out.print("Enter your level: ");
          guesses = keyboard.nextInt();

          if (guesses > 3 || guesses < 0) {
            System.out.println("Invalid Level Try Again:\n");
          }

          else if (guesses == 1) {
            guesses = 5;
            levelChosen = true; // levelChosen turns to true - used to leave this loop whenever level is chosen
          }

          else if (guesses == 2) {
            guesses = 3;
            levelChosen = true;
          }

          else if (guesses == 3) {
            guesses = 1;
            levelChosen = true;
          }
        }

        // recieve input from user
        System.out.print("\nEnter your name: ");
        name = keyboard.next().toLowerCase();

        System.out.println(
            "\n\t\t========== YOUR NAME IS BEING ENCRYPTED USING A RANDOMLY GENERATED SHIFT FROM 1 TO 9 =========");

        // assign variables
        len = name.length();
        shift = randomNumber.nextInt(8) + 1; // generate random number from 1-9

        for (int i = 0; i < len; ++i) {
          nameCh = name.charAt(i);

          for (int x = 0; x < ALPHABET.length; ++x) {
            check = ALPHABET[x];

            if (nameCh == ALPHABET[x]) {
              // index = ALPHABET[i];

              sum = x + shift;

              if (sum > 25) {
                sum -= 26;
              } // if sum is greater than Z

              cipher += ALPHABET[sum];
            } // if current character = current array element - find index of array and add
              // the shift - this sum is the index of the encrypted character

          } // inner for loop - cycle through array

        } // outer for loop - cycle through 'name' characters

        // start game

        System.out.println("\n\t\t\t\t\t  Your new encrypted name is: \n");
        System.out.println("\t\t\t\t\t\t     " + cipher);
        //System.out.println("\t\t\t\t\t\t==============");

        System.out.println("\n");
        do {
          System.out.printf("You now have %d guesses to guess the shift! \n", guesses);
          System.out.printf("Guess %d (1 to 9): ", (count));
          guess = keyboard.nextInt();

          if (guess == shift) {
            System.out.println("That's correct! The shift was " + shift + " :)\n");
            outcome = "win";
            guesses = 0; // leaves once you get the correct shift
          } // if guessed correctly
          else if (guess > 0 && guess <= 9 && shift != guess) {
            System.out.println("That's incorrect..!\n");
            outcome = "lose";
          } // if guessed incorrectly
          else {
            System.out.println("\nInvalid guess, you've lost a turn.\n");
            outcome = "lose";
          }
          count++;
          guesses--;
        } while (guesses > 0);// leaves loop if you run out of guesses

        System.out.println("\t\t\t\t=============================================\n");

        // print outcome
        if (outcome.equals("win")) {
          System.out.println("\t\t\t\t\t\t  YOU WIN!");
          System.out.println("\n\t\t\t\t=============================================\n");
          System.out.println("CONGRATULATIONS! You won! Thanks for playing\n");
        } // if won game

        else if (outcome.equals("lose")) {
          System.out.println("\t\t\t\t\t\t  YOU LOSE!");
          System.out.println("\n\t\t\t\t=============================================\n");
          System.out.println("Ouch! You didn't guess in time... The shift was " + shift);
          System.out.println("Better luck next time!\n");
        } // else if lost game
      } // if choice 1

      else if (choice == 2) {
        cipher = "";
        sum = 0;
        count = 1;

        // recieve input from user
        System.out.print("\nEnter your name: ");
        name = keyboard.next().toLowerCase();
        System.out.print("\nWhat shift would you like to use (Enter a number between 1-9): ");
        shift = keyboard.nextInt();

        if (shift > 0 && shift < 10) {
          System.out.println("\nYour name is being encrypted using your chosen shift of " + shift);

          // assign variables
          len = name.length();

          for (int i = 0; i < len; ++i) {
            nameCh = name.charAt(i);

            for (int x = 0; x < ALPHABET.length; ++x) {
              check = ALPHABET[x];

              if (nameCh == ALPHABET[x]) {
                // index = ALPHABET[i];

                sum = x + shift;

                if (sum > 25) {
                  sum -= 26;
                } // if sum is greater than Z

                cipher += ALPHABET[sum];
              } // if current character = current array element - find index of array and add
                // the shift - this sum is the index of the encrypted character

            } // inner for loop - cycle through array

          } // outer for loop - cycle through 'name' character

          System.out.println("\n\t\t\t\t  Your new encrypted name is: ");
          System.out.println("\t\t\t\t\t   " + cipher + "\n");

        } // if

        else {
          System.out.println("Number entered is NOT between 1 - 9!\n");
        }
      } // if choice 2

      else {
        System.out.println("\nThat is not a valid option, please enter 1 or 2\n");
      } // else

      System.out.print("Would you like to play again? (Y to continue or any other key to quit): ");
      cont = keyboard.next().charAt(0);
    } while (cont == 'y' || cont == 'Y');

    System.out.println("\n");

    // end of program
    if (cont != 'y' || cont != 'Y') {
      System.out.println("\n\t\t\t\t\t\t================================");
      System.out.println("\n\t\t\t\t\t\t\t\tTHANKS FOR PLAYING!");
      System.out.println("\n\t\t\t\t\t\t================================");
    } // if continue

  }// main
}// class