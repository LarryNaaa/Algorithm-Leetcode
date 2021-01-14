public class Solution_labelingSystem {
    /*
    Amazon is looking to develop a new labeling system in the fulfillment centers.
    New labels will be devised from the original string labels.
    Given the original string label, construct a new string by rearranging the original string and deleting characters as needed.
    Return the alphabetically-largest string that can be constructed respecting the limit
    as to how many consecutive characters can be the same (represented by k).
    "Alphabetically-largest" is defined in reverse alphabetical order (e.g., b is "larger" than a, c is "larger" than b, etc.)
    from left to right (e.g., "ba" is larger than "ab").

    Write an algorithm to return the alphabetically-largest string that can be constructed respecting the above limits.
    Input
    The input to the function/method consists of two arguments:
    originalLabel, a string representing the original string label;
    charlimit, an integer representing the maximum number of identical consecutive characters the new string can have (k).
    Output
    Return a string representing the alphabetically largest string that can be constructed that has no more than k identical consecutive characters.
    Constraints
    1 <= len <= 10^5; where len represents the length of originalLabel
    1 <= charlimit <= 10^3
    Note
    The string originalLabel contains only lowercase English letters.
    Example
    Input:
    originalLabel = baccc
    charLimit = 2
    Output:
    ccbca
    Explanation:
    The largest string, alphabetically, is 'cccba' but it is not allowed because it uses the character's more than 2 times consecutively.
    Therefore, the answer is 'ccbca'.
    NB: I have assumed that if an invalid string is sent (no possible combination), then I return a blank string.
*/

//method to return alphabatically largest string that is constructed respecting the limit
//method accept two argument one is string(original string) and other is integer(limit of consecutive character)
    static String fxn(String originalLabel,int charlimit) {
// a array x is define which will store the frequency of character appear in string 'originalLabel'
// index 0 is for char 'a' and in a same sequence index 25 is for character 'z'
        int x[]=new int[26];
//a for loop is used to access character of string one by one
        for(int i = 0; i < originalLabel.length(); i++){
// according to ASCII system 'a' is repesented by 97 so minus char 'a' from each char of string
// to get index for array x and increment it by 1
            x[originalLabel.charAt(i)-'a']++;
        }
// 'ans' variable will be used to store answer
        String ans="";
        int p=26,q=25,flag=1,count=0;
// variable q will start iterating from last index of x so that we can get max char
// initially flag is 1 and count is 0
        while(q >= 0){ //this is main loop which will be true until q become equal to -1
            if(flag==1){
                while(true){//this loop will be terminated with the help of break statement
                    if(x[q]==0){ //if at q index of x there is no character left
                        q--;
                        count=0;
                        break;
                    }
                    ans = ans + (char)((int)'a'+q); //character is added to ans
                    count++; //count take care about the number of consecutive character
                    x[q]--; //character is taken so 1 is minus from qth index of array x
                    if(count==charlimit){
//if we reach limit then we have two condition
//first condition is that there is no character left at that index
                        if(x[q]==0){
                            q--;
                            count=0;
                            break;
                        }
//second condition is that there is atleast one character left at qth index of array x
                        else{
//now flag is set to 0 and here comes the role of p
//now character at qth index cann"t be used(because of consecutive limit)
                            flag=0;
                            count=0;
                            p=q-1;
                            break;
                        }
                    }
                }
            }
            else{
//p start from one less then q and find a character till index 0 so that qth index can again be started
                while(p>=0){
//if no character is there at p index then minus p by 1
                    if(x[p]==0)
                        p--;
//if character is present at pth index then add it to ans and reduce the frequency of that character from x array
                    else{
                        ans=ans+(char)(p+(int)'a');
                        x[p]--;
//set flag to 1 agian so that qth index operation can be continue
                        flag=1;
                        break;
                    }
                }
            }
        }
//finally return ans
        return ans;
    }

    public static void main(String[] args) {
        String originalLabel = "baccc";
        int charlimit = 2;
        System.out.println(fxn(originalLabel, charlimit));;
    }
}
