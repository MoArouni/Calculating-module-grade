import java.util.Scanner; // Needed to make Scanner available

class Grade // change the name to something appropriate
{
    public static void main (String [] a)
    {
        String finalgrade; 
        int[] grades = input_2_array();
        finalgrade = finalgrade(grades);
        System.out.println("You consistently gained a "  + finalgrade + " grade or better.");
        System.out.println("Therefore you gained a " + finalgrade + " overall for your portfolio of work.");
        return;
    } // END main]

//checks if it is a positive integer, returns true if it is, false if it isn't
    public static boolean Valid(String str) {
        // Check if the string is not null , not empty, and contains only digits (meaning no negative integers), returns a boolean true or false.
        return str != null & !str.isEmpty() & str.matches("\\d+"); 
    }


//takes an argument corresponding to the mark we re looking to ask the user about and returns the ammount of times the user got that mark
//handles bad input by telling the user it is invalid and looping until the user inputs a valid input
//two conditions for the input to be valid
    public static int input(String grade)
    {
        String input_grade; 
        int int_grade = 0; 
        Scanner key = new Scanner(System.in); 
        boolean valid = false; 
        while (!valid)
        {
            print_questions(grade);
            input_grade = key.nextLine(); 
            if (Valid(input_grade)) //condition 1, it is a positive integer
            {
                int_grade = Integer.parseInt(input_grade); //converts the string into an int.
                if (int_grade >= 0 && int_grade <= 8) //condition 2, it is a digit between 0 and 8 inclusively.
                { 
                    valid = true;
                }
                else 
                {
                    System.out.println("Invalid, it has to be a number between 0 and 8.");
                }
            }
            else 
            {
                System.out.println("Invalid, it has to be a positive integer between 0 and 8.");
            }
        }
        return int_grade;
    }

    
//abstraction method of printing the questions easier
    public static void print_questions(String grade) 
    {
        System.out.println("How many " + grade + " did you get?"); 
    }

//verify total method 
    public static boolean verify(int[] grades)
    {
        int total = 0;
        for (int i = 0; i<7; i+=1)
        {
            total += grades[i];
        }
        if (total != 8)
        { 
            return false;
        }
        else
        {
            return true;
        }
    }

//input_2_array
//asks the user how many grades he got for each mark; and returns an array that stores the ammount for each mark by index position 
//for example [0, 0, 3, 3, 0, 0, 1] --> 3 B, 3 C, 1 G.
    public static int[] input_2_array() //returns an array full of integers
    {
        boolean valid = false;
        boolean verify = false;
        int[] grades = new int[7]; 
        while (!valid)
        {
            grades[0] = input("A+");
            grades[1] = input("A");
            grades[2] = input("B");
            grades[3] = input("C");
            grades[4] = input("D");
            grades[5] = input("F");
            grades[6] = input("G");
            verify = verify(grades);
            if (verify == true)
            { 
                valid = true;
            }
            else
            {
                System.out.println("You have more or less than 8 marks. Please enter the correct ammount");
            }
        }
        return grades;
    }
        


//checks the number of marks of an array from a certain index range, returns true or false
    public static boolean range(int i1, int number, int[] grades)
    {
        int total = 0;
        for (int i = 0; i <= i1; i+=1) 
        {
            total += grades[i];
        }
        if (number <= total)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

// check_G, checks if all grades are G or better 
    public static boolean check_G(int[] grades)
    {
        return grades[6] <= 8 ;
    }

// check_F checks if all grades are at least F or better
    public static boolean check_F(int[] grades)
    {
        for (int i = 6; i>5; i-=1)
        {
            if (grades[i] > 0)
            {
                return false;
            }
        }
        return true;
    }

    
// check_D checks if all grades are at least D or better
    public static boolean check_D(int[] grades)
    {
        for (int i = 6; i>4; i-=1)
        {
            if (grades[i] > 0)
            {
                return false;
            }
        }
        return true;
    }

// check_C method checks if all grades are at least C or better (none below C)
    public static boolean check_C(int[] grades)
    {
        for (int i = 6; i>3; i-=1)
        {
            if (grades[i] > 0) //while going from the end of the array to the position right before C,
                //if we encounter a value bigger than 0, we stop and return false since not all grades are C or better than C
            {
                return false;
            }
        }
        return true;
    }

// check_B method checks if all grades are at least B or better (none below B)
    public static boolean check_B(int[] grades)
    {
        for (int i = 6; i>2; i-=1)
        {
            if (grades[i] > 0)
            {
                return false;
            }
        }
        return true;
    }

//check_A method checks if all grades are at least A or better (none below A)
    public static boolean check_A(int[] grades)
    {
        for (int i = 6; i>1; i-=1)
        {
            if (grades[i] > 0)
            {
                return false;
            }
        }
        return true;
    }

//get_G method evaluates if you didnt pass the rest of the conditions, you get G
    public static boolean get_G(int[] grades)
    {
        return check_G(grades) && range(0, (8-grades[6]) , grades);
    }

//checks if you got at least 6 Ds and at least all of your grades are G or better
    public static boolean get_F(int[] grades)
    {
        return (range(4, 6, grades) && check_G(grades));
    }
    
//checks if you got at least 6 Ds and at least all of your grades are F or better
    public static boolean get_D(int[] grades)
    {
        return range(4, 6, grades) && check_F(grades);
    }

//checks if you got at least 6 Cs and at least all of your grades are D or better
    public static boolean get_C(int[] grades)
    {
        return range(3, 6, grades) && check_D(grades);
    }

//checks if you got at least 6 Bs and at least all of your grades are C or better
    public static boolean get_B(int[] grades)
    {
        return range(2, 6, grades) && check_C(grades);
    }
    
// checks if you got at least 6 As and the rest are at least Bs
    public static boolean get_A(int[] grades)
    {
        return range(1, 6, grades) && check_B(grades);
    }

//checks if you got at least 6 A+ and at least all of your grades are A or better
    public static boolean get_AA(int[] grades)
    {
        return range(0, 6, grades) && check_A(grades);
    }


//checks if you got at least 7 A+ and at least all of your grades are A or better
    public static boolean get_AAA(int[] grades)
    {
        return range(0, 7, grades) && check_A(grades);
    }

//checks if you got 8 A+
    public static boolean get_AAAA (int[] grades)
    {
        return grades[0] == 8;
    }
    
//We step by step check if the marks fulfill the condition for each final grade, if it doesn't we move on to the next one
// till we finally reach Q where it only shows Q if all the other conditions weren't fulfilled.
    public static String finalgrade(int[] grades)
    {
        if (get_AAAA(grades))
        {
            return "A*";
        }
        else 
        {
            if (get_AAA(grades))
            {
                return "A++";
            }
            else
            {
                if (get_AA(grades))
                {
                    return "A+";
                }
                else
                {
                    if (get_A(grades))
                    {
                        return "A";
                    }
                    else
                    {
                        if (get_B(grades))
                        {
                            return "B";
                        }
                        else
                        {
                            if (get_C(grades))
                            {
                                return "C";
                            }
                            else
                            {
                                if (get_D(grades))
                                {
                                    return "D";
                                }
                                else
                                {
                                    if (get_F(grades))
                                    {
                                        return "F";
                                    }
                                    else
                                    {
                                        if (get_G(grades))
                                        {
                                            return "G";
                                        }
                                        else
                                        {
                                            return "Q";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
        }
    }

    
} // END class name




