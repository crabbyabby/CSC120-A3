import java.util.Scanner;
import java.util.ArrayList;
class Conversation implements ConversationRequirements {

  // Attributes 
  String[] transcript;
  String[] response = new String[10];
  String[] original = new String[12];
  String[] replacement = new String[12];
  /**
   * Constructor 
   */

  Conversation() {
   /**
   * List of canned responses
   */
    response[0] = "If I were a cat, I would be a brown cat.";
    response[1] = "Please continue talking.";
    response[2] = "That is so interesting.";
    response[3] = "Tell me more!";
    response[4] = "Could you elaborate on your thoughts?";
    response[5] = "What do you mean by this?";
    response[6] = "What if we changed the subject?";


   /**
   * Lists of mirrors
   */
    original[0] = "I";                replacement[0] = "you";
    original[1] = "me";               replacement[1] = "you";               
    original[2] = "am";               replacement[2] = "are";
    original[3] = "you";              replacement[3] = "I";
    original[4] = "my";               replacement[4] = "your";
    original[5] = "your";             replacement[5] = "my";
    original[6] = "were";             replacement[6] = "was";
    original[7] = "was";              replacement[7] = "were";
    original[8] = "I'm";              replacement[8] = "you're";
    original[9] = "you're";           replacement[9] = 
    original[10] = "I've";            replacement[10] = "you've";
    original[11] = "are";             replacement[11] = "am";
    original[12] = "you";             replacement[12] = "me";


  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    
    System.out.println("How many rounds?");
    Scanner scan = new Scanner(System.in);
    int rounds = scan.nextInt();
    scan.nextLine();

    while (rounds <= 0){
      System.out.println("Please input a valid number above 0.");
      rounds = scan.nextInt();
    }
    transcript = new String[(rounds * 2) + 1];

    System.out.println("Hi there! What's on your mind?");

    transcript[0] = "Hi there! What's on your mind?";

    for (int i = 0; i < rounds; i++){
      String input = scan.nextLine();
      transcript[(i*2)+1] = input;
      String reply = respond(input);
      System.out.println(reply);
      transcript[(i*2)+2] = reply;
    }

    System.out.println("See ya!");
    transcript[rounds*2] = "See ya!";

    scan.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("-------------------------");
    System.out.println("TRANSCRIPT:");
    for (String line : transcript){
      System.out.println(line);
    }
    System.out.println("-------------------------");
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String trimmed = inputString.trim();

    String[] words = trimmed.split(" ");

    for (int i = 0; i < words.length; i++){
      for (int j = 0; j < original.length; j++){
        if (words[i].equalsIgnoreCase(original[j])) {
            words[i] = replacement[j];
            break; 
        }
      }
    }

    words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);
    String returnString = String.join(" ", words); 


    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
