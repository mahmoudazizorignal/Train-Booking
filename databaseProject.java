import java.util.Scanner;
public class databaseProject {
	static boolean exit = false;
	public static void initializeSystem() {
		while(exit==false) {
			System.out.println("1- signIn.");
			System.out.println("2- signUp.");
			System.out.println("3- exit program.");
			
			switch(getIntUserInput()) {
			
			case 1:                                                      //case 1 for logging in
				System.out.println("1- signIn As A User.");
				System.out.println("1- signIn As A Admin.");
				int choice1 = getIntUserInput();
				if(choice1==1) {                                         //logging in as a user
					System.out.println("Enter Your User Name, ");
					String name = getStringUserInput();
					System.out.println("Enter Your User Password, ");
					String password = getStringUserInput();
				}
				else if(choice1 ==2) {                                   //logging in an admin
					System.out.println("Enter Your Admin User Name, ");
					String name = getStringUserInput();
					System.out.println("Enter Your Admin Password, ");
					String password = getStringUserInput();
				}else { System.out.println("Invalid Choice...");}
				break;
			
			case 2:                                                      //case 2 for signing up
				System.out.println("1- signUp As A User.");
				System.out.println("2- signUp As A Admin.");
				int choice2 = getIntUserInput();
				if(choice2==1) {                                         //signing up as a user
					System.out.println("Choose A User Name, ");
					String name = getStringUserInput();
					System.out.println("Choose A User Password, ");
					String password = getStringUserInput();
				}
				else if(choice2 ==2) {                                   //signing up as an admin 
					System.out.println("Choose An Admin User Name, ");
					String name = getStringUserInput();
					System.out.println("Choose An Admin User Password, ");
					String password = getStringUserInput();
				}else { System.out.println("Invalid Choice...");}
				break;
				
			case 3:                                                      //case 3 for exiting the program
				System.out.println("Terminating The System..");
				exit =true;
				break;
			default:                                                     //default for invalid choices
				System.out.println("Invalid choice...");
				break;
			}		
		}
	}
	public static int getIntUserInput() {
		System.out.println("Enter Your Choice As An Integer Number: ");
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	public static String getStringUserInput() {
		System.out.println("Enter Your Choice AS A String: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.next();
		return name;
	}
	public static void main(String[] args) {
		initializeSystem();
	}
}
