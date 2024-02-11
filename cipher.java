import java.util.*;

public class cipher
{
	public static void main(String[] args)
	{
		// create instance of scanner and random
		Scanner keyboard = new Scanner(System.in);
		Random randomNumber = new Random();

		//create array of alphabet + 9 extra characters - [34 total]
		char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

		//variables
		String name, cipher = "", outcome = ""; // name = user input, cipher = encrypted input
		int shift, len, sum, guess, count, decount; //shift, len = length of input, sum = index of array[i] + shift
		char nameCh, check, cont = 'n'; //nameCh = name.charAt[i] - gets each character of input individually, check = alphabet[x] - gets current element of array to compare to current char from input

		//print introduction
		System.out.println("Caesar Cipher");

		do
		{
			cipher = "";
			sum = 0;
			count = 1;
			decount = 3;


			//recieve input from user
			System.out.print("\nEnter your name: ");
			name = keyboard.next();

			System.out.println("\nYour name is being encrypted using a randomly generated shift from 1 to 9");

			// assign variables
			len = name.length();
			shift = randomNumber.nextInt(8) + 1; //generate random number from 1-9


			for(int i = 0; i < len; ++i)
			{
				nameCh = name.charAt(i);

				for(int x = 0; x < ALPHABET.length; ++x)
				{
					check = ALPHABET[x];

					if(nameCh == ALPHABET[x])
					{
						//index = ALPHABET[i];

						sum = x + shift;

						if(sum > 25)
						{
							sum -= 25;
						}//if sum is greater than Z

						cipher += ALPHABET[sum];
					}//if current character = current array element - find index of array and add the shift - this sum is the index of the encrypted character

				}//inner for loop - cycle through array

			}//outer for loop - cycle through 'name' characters


			System.out.println("\nYour new encrypted name is: " + cipher);

			System.out.println("\n");
			//start game
			System.out.println(" = = G U E S S I N G  G A M E = = ");
			System.out.println("=======================================\n");

			do
			{
				System.out.printf("\nYou now have %d guesses to guess the shift! \n", decount);
				System.out.printf("Guess %d (1 to 9): ", count);
				guess = keyboard.nextInt();

				if(guess == shift)
				{
					System.out.println("That's correct! The shift was " + shift + " :)");
					outcome = "win";
					count = 4;
				}//if guessed correctly
				else if(guess > 0 && guess < 9 && shift != guess)
				{
					System.out.println("That's incorrect..!");
					outcome = "lose";
				}//if guessed incorrectly
				else
				{
					System.out.println("Invalid guess, you've lost a turn.");
				}
				count++;
				decount--;
			}while(count < 4);

			System.out.println("\n=======================================\n");

			//print outcome
			if(outcome.equals("win"))
			{
				System.out.println("CONGRATULATIONS! You won! Thanks for playing");
			}//if won game

			else if(outcome.equals("lose"))
			{
				System.out.println("Ouch! You didn't guess in time.. The shift was " + shift);
				System.out.println("Better luck next time!");
			}//else if lost game

			System.out.print("Would you like to play again? (Y to continue or any other key to quit): ");
			cont = keyboard.next().charAt(0);
		}while(cont == 'y' || cont == 'Y');

		System.out.println("\n");

		//end of program
		if(cont != 'y' || cont != 'Y')
		{
			System.out.println("Thanks for playing!\n\n");
		}//if


	}//main
}//class