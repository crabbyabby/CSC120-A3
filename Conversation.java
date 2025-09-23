import java.util.Scanner;
class Conversation implements ConversationRequirements {

  // Attributes 

  /**
   * Constructor 
   */
  Conversation() {

    

  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    System.out.println("How many rounds?");
    Scanner scan = new Scanner(System.in);
    int rounds = scan.nextInt();

    for (int i = 0; i < rounds; i++){
      String input = scan.nextLine();
      respond(input);
    }

    scan.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {

  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String returnString = ""; 
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
