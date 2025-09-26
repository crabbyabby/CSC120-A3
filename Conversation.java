import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Conversation implements ConversationRequirements {

  // Attributes 
  String[] transcript;
  String[] response = new String[10];
  String[] original = new String[15];
  String[] replacement = new String[15];
  String[] punct = {".", "!", "?"};
  boolean detect;
  /**
   * Constructor 
   */

  Conversation() {
   /**
   * List of canned responses
   */
    response[0] = "Sorry to change the subject but i think that if I were a cat, I would be a cute cat.";
    response[1] = "Please continue talking.";
    response[2] = "That is so interesting.";
    response[3] = "Tell me more!";
    response[4] = "Could you elaborate on your thoughts?";
    response[5] = "What do you mean by this?";
    response[6] = "What if we changed the subject?";
    response[7] = "Why are you sighing like this...?";


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
    original[9] = "you're";           replacement[9] = "I'm";
    original[10] = "I've";            replacement[10] = "you've";
    original[11] = "you've";          replacement[11] = "I've";
    original[12] = "are";             replacement[12] = "am";
    original[13] = "you";             replacement[13] = "me";
    original[14] = "I'd";             replacement[14] = "you'd";

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
    transcript = new String[(rounds * 2) + 2];

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
    transcript[(rounds*2)+1] = "See ya!";

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
    int periods = 0;
    int question = 0;
    String add;
    String trimmed = inputString.trim();
    String[] words = trimmed.split(" ");

  /**
   * Checks if any words contains the to-be mirrored words (unpunctuated) 
   * and mirrors them!!
   */
    for (int i = 0; i < words.length; i++){
      for (int j = 0; j < original.length; j++){
        periods = 0;
        question = 0;
        if (words[i].equalsIgnoreCase(original[j])) {
            words[i] = replacement[j];
            detect = true;
            break; 
        }
          /**
           * I!!! --> length 4
           * original[j] --> (1)
           * charAt (1-3) are punctuation
           * 
           * for (int k = original[j].length(); k < words[i].length() - 1; k++ )
           * I! --> og[j].len = 1 --> k = 1
           * words[i] --> 2
           * wtf is this even for theree has to be an easier way to do ts
           */
        else if (words[i].contains(original[j])) {
          for (int k = original[j].length(); k < words[i].length(); k++) {
              if (words[i].charAt(k) == '.' || words[i].charAt(k) == '!') { 
                  periods += 1;
              }
              else if (words[i].charAt(k) == '?') {
                  question += 1;
              }
              else {
                  break;
              }
          }
          String base = replacement[j];
          if (periods > 0) {
              words[i] = base + "?".repeat(words[i].length() - original[j].length());
          }
          else if (question > 0) {
              words[i] = base + ".".repeat(words[i].length() - original[j].length());
          }
          else {
              words[i] = base;
          }
      }

            }
        /**
         * Checks if detected mirrored words --> no?
         */
            if (detect == false){
              int index = (int) (Math.random() * 7);
              return response[index];
            }

            if (words[i].contains("...")){
              return response[7];
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
