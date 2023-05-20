import java.util.Scanner;
public class databaseProject {
	static boolean exit = false;
	public static void initializeSystem() {
		while(exit==false) {
			System.out.println("1- signIn.");
			System.out.println("2- signUp.");
			System.out.println("3- exit program.");
			switch(getUserInput()) {
			case 1:
				System.out.println("1- signIn As A User.");
				System.out.println("1- signIn As A Admin.");
				int choice1 = getUserInput();
				if(choice1==1) {
					
				}
				else if(choice1 ==2) {
					
				}else { System.out.println("Invalid Choice");}
			case 2:
				System.out.println("1- signup As A User.");
				System.out.println("1- signup As A Admin.");
				int choice2 = getUserInput();
				if(choice2==1) {
					
				}
				else if(choice2 ==2) {
					
				}else { System.out.println("Invalid Choice");}
			case 3:
				exit =true;
			default:
				System.out.println("Invalid choice...");
			}
		}
	}
	public static int getUserInput() {
		System.out.println("Enter Your Choice: ");
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	public static void main(String[] args) {
		initializeSystem();
	}
}
