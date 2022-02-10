import java.util.Scanner;

public class Airplanes {
	
	static Scanner input = new Scanner(System.in); //We're just setting up the input for now;
	static String[][] seat = new String[100][29];//Seat array
	static int j=0,k=0,p=0,i=0,mainSeatNumber=0,classRead;//Seat number and seat class, both inputs from the user
	static String personName;// name, destination and city, will be printed on the boarding pass
	static String destination;
	static String currentCity;
	static String[] planeName = new String[28];
	static int bookAgain = 1;// variable to check if the user wants to book another seat
	static int isPlaneFull = 0;//variable to check whether the selected plane is full
	static int time = 0;//variable for the time (0/6/12/18/0 again)
	static String planeClass;

	public static void main(String[] args) {
		
		for(i = 0;i<90;i++)
		{
			for(p=0;p<28;p++)
			{
				seat[i][p] = String.valueOf(i+1);
			    if(i<9)
			    {
				    seat[i][p] = "0" + String.valueOf(i+1);
			    } 
			}
		}//here we set up the numbers for the seats
		
		p=0;
		
		/*for(i = 0; i<90; i++)
		{
			seat[i][0] = "XX";
			seat[i][1] = "XX";
		}*/ //uncomment this for to fill 2 planes and see the system assigning seats to the 3rd plane- FL3

		GiveInfo();//here we ask the user for his info
		
		while(true)
		{
			
			planeName[p] = "FL" + (p+1);//assign the plane's name in relation to p-the filled plane variable
			
			if(p>27)//if there are 28 filled planes, the program exits
			{
				System.out.println("There are no available flights for this week");
				break;
			}
			else//otherwise it checks whether all the seats of a plane are filled
			{
				for(i=0;i<90;i++)
				{
					if(seat[i][p] != "XX")//if it didn't find any XX seats the plane isn't full
					{
						isPlaneFull=0;
					}
					else isPlaneFull = 1;//otherwise it is
				}
				if(isPlaneFull == 1)//if it is full, p-the filled plane variable gains 1 and the name changes from for example FL1 to FL2 and so on
				{
					p++;
					isPlaneFull = 0;
					time+=6;
					planeName[p] = "FL" + (p+1);
				}
			}
			
			if(time == 24)//if the time reaches 24, reset it to 0, which is midnight
			{
				time = 0;
			}
			
			if(bookAgain == 1)//if the user wishes to book
		    {
				System.out.println("");
		        System.out.println("For First Class please input 1");
		        System.out.println("For Business Class please input 2");
		        System.out.println("For Economy Class please input 3");
		        classRead = input.nextInt();//we ask for his class 
		        BookSeats(classRead);//and pass it over to booking the respective seat
		
		        System.out.println("");
		        System.out.println("Would you like to book another seat? 0 = no / 1 = yes");
		        bookAgain = input.nextInt();//ask the user if he wishes to book again
		    }
			else if(bookAgain == 0)//if not, we exit
			{
				System.out.println("Have a good day!");
				break;
			}
		}
	}

	public static void BookSeats(int mainClass) //Method for asking the user for his seat number and give planeClass the designated class
	{                                           //to be printed on the Boarding Pass
		
		if(mainClass == 1)
		{
			System.out.println("Please select a seat from 1 to 18");
			{
				planeClass = "First";
				OccupySeats();
			}
		}
		else if(mainClass == 2)
		{
			System.out.println("Please select a seat from 19 to 45");
			{
				planeClass = "Business";
				OccupySeats();
			}
		}
		else if(mainClass == 3)
		{
			System.out.println("Please select a seat from 46 to 90");
			{
				planeClass = "Economy";
				OccupySeats();
			}
		}
	} 
	
	public static void AssignSeats(int start, int finish)//method for assigning seats and adding the changes to 
	{                                                    //them (if they're occupied)
		for(i = start;i<finish;i++)//start and finish are assigned in the ShowSeats method
		{
			if(k<9)
			{
			   if(j<3)
			   {
				   System.out.print("[" + seat[i][p] + "]");
				   j++;
			   }
			   if(j==3) 
			   {
				    System.out.print("   ");
				    j=0;
			   }
			   k++;
			}
			
			if(k==9)
			{
				System.out.println("");
				k=0;
			}
				
		}
	}
	
	public static void ShowSeats()
	{
		System.out.println("");
		System.out.println("---------------FIRST CLASS----------------");
		System.out.println("");
		
		AssignSeats(0,18);//for efficiency we only modify the seats in the respective class, instead of rewriting all of them

		System.out.println("");
		System.out.println("--------------BUSINESS CLASS--------------");
		System.out.println("");
		
		AssignSeats(18,45);

		System.out.println("");
		System.out.println("---------------ECONOMY CLASS--------------");
		System.out.println("");
		
		AssignSeats(45,90);
	}
	
	public static void OccupySeats()//method for displaying the occupied seats 
	{
		mainSeatNumber = input.nextInt();
		if(seat[mainSeatNumber - 1][p] !="XX")//we verify if the seat is already taken
		{
		    seat[mainSeatNumber - 1][p] = "XX";//if not, occupy it and show the seats
            ShowSeats();
            ShowBoardingPass();
		}
		else 
		{
			System.out.println("Seat is already occupied");//if it is, tell the user the seat is occupied
		}
	}
	
	public static void GiveInfo()//method for taking the user's info and displaying the seats for the first time
	{
		System.out.println("Welcome to our online booking system");
		System.out.print("Please enter your name: ");
		personName = input.nextLine();
		System.out.print("Please enter your city: ");
		currentCity = input.next();
		System.out.print("Please enter your destination: ");
		destination = input.next();
		System.out.print("Please enter your desired time: ");
		time = input.nextInt();
	}
	
	public static void ShowBoardingPass()//method for displaying the boarding pass
	{
		System.out.println("");
		System.out.println("                -------------BOARDING PASS-----------------");
		System.out.println("                Name:---------- " + personName );
		System.out.println("                Current City:-- " + currentCity);
		System.out.println("                Destination:--- " + destination );
		System.out.println("                Seat:---------- " + mainSeatNumber);
		System.out.println("                Flight:-------- " + planeName[p]);
		System.out.println("                Time:---------- " + time);
		System.out.println("                Class:--------- " + planeClass);
		System.out.println("                -------------------------------------------");
		System.out.println("");
	}
	

}

//-----------------------------------------------------------------------------------------------------------ALGORITHM-----------------------------------------------------------------------------------------------------
/* Use a two-dimensional array with i seats up to 90, while p, the 2nd dimension, is the plane number, up to 28
 * Initialize the array elements as normal seat numbers
 * Ask the user for his info (name, city, hour etc)
 * Check whether we have airplanes left
 *    If yes then continue on with the program 
 *    or else break and exit
 * Ask the user for the class (1,2,3) and for the seat, and assign it, if possible
 *     If not possible then ask if the user would like another seat
 *        If not, then exit
 *        If yes. then the program will loop and ask the same questions, until either a seat is available or the user exits
 * During this time, if a plane is full, the system automatically assigns the user a seat on the next flight
 * Lastly, add a method for the boarding pass and call it each time the user books a good seat (not occupied)
 * The user will be able to book more seats and each one will have a different boarding pass
 * 
 * MISCELLANEOUS:
 * 
 * Add a method for the first input of the user (name etc.)
 * Add a method for checking occupied seats and assigning them
 * Add a method for showing seats 
*/
