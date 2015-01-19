#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

unsigned long time = 0; // holds number of 'ticks' that the arduino has been on for - updated first in loop()
int tickLength = 100; // length of one tick in milliseconds

// messages for each state of the robot
String bootMessage = "Robot on... Waiting for communication";
String teleMessage = "Teleop Enabled";
String autoMessage = "Autonomous Enabled";
String testMessage = "Test Enabled";
String offMessage = "Robot Disabled";

// state bools
boolean isTeleop = false;
boolean isAuto = false;
boolean isTest = false;
boolean isDisabled = true;

// communications
boolean isComm = false;
int lastComm = 0; // last tick a message was received from the roboRIO, used to determin if communications are still up
int commTimeout = 100; // number of ticks without contact before communications are considered lost

// display settings
int backlightPercent = 0; // percentage brightness for backlight

// vars for printMess function
int startString = -1; //tick on which the program started printing the line. If -1, tells program to start printing again
int stopString = 0; //tick on which the program finished printing the line
int lastCharTime = 0; //tick on which the last character was printed
int messChanged = 0; //tick on which the message was changed last
int timeout = 0; //how long the current message should be displayed
int waitTime = 20; //how long to wait upon starting the text over, and finishing scrolling it.
int scrollSpeed = 1; // how fast to scroll the text (one char every x ticks). the smaller this number, the faster the text scrolls
int currentChar = 0; // used by program to keep track of its position in the string
boolean continuePrinting = true; // should the program scroll the text?
String last = bootMessage; // used to test if the text being printed has changed since the function was last called -- default is the bootMessage

//PWMs
int backlight = 9;

void setup() {
  //Set up communication with the roboRIO at 115200 baud
  Serial.begin(115200);
  
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  lcd.print(bootMessage);
  
  //debug LED
  pinMode(13, OUTPUT);
  pinMode(backlight, OUTPUT);
}

void loop() {
  //sets the time var each time the loop is called
  time = millis()/tickLength;
  
  if (backlightPercent >= 0 && backlightPercent <= 100){
    analogWrite(backlight, 2*backlightPercent); // if backlightPercent is in the rage 0-100, set the pwm to twice that (0-200/0v - ~4v)
  }
  
  if (isComm){
    if (isTeleop){
      printMess(teleMessage, 0);
    }else if (isAuto){
      printMess(autoMessage, 0);
    }else if (isTest){
      printMess(testMessage, 0);
    }else if (isDisabled){
      printMess(offMessage, 0);
    }
  }else{
    printMess(bootMessage, 0);
  }
  
  // gets data from comm channel if communications are up, or attempts to restore comms if they are down
  if (isComm){
    runComm();
  }else{
    testComm();
  }
  
  //prints the time the arduino has been running on the screen
  printTime();
}

// ran if not communicating with roboRIO - waits for serial activity and then updates comm status based on what is received
void testComm(){
  if (Serial.available() > 0){ //if data is in the serial bus, read it
    String in = Serial.readString(); //reads data as a string. This is slow, but gives us more data at once.
    if (in == "Comm:ping"){ //if the data is what is expected from the roboRIO to initiate communication, initiate communication, and acknowledge the link
      isComm = true;
      Serial.println("Comm:acknowledge");
      printMess("Link with roboRIO established", 35);
    }
  }
}

// ran every loop if comms are up. Allows roboRIO to set settings, and request data from arduino
void runComm(){
  if (Serial.available() > 0){ //if data is available, read it
    char in = Serial.read(); //reads data as a char, this is almost instant, but only lets us use one character at a time.
    Serial.println(in);
    lastComm = time;
    if (in == 'r'){ //the first byte will either be 'r' or 'w', as the roboRIO should only ever be reading or writing values
      serialFlush();
    }else if (in == 'w'){
      in = Serial.read();
      Serial.println(in);
      switch (in) {
        case 's':{ //modify state (teleop, autonomous, test, or disabled)
          in = Serial.read();
          Serial.println(in);
          switch (in){
            case 'T': //Teleop
              isTeleop = true;
              isAuto = false;
              isTest = false;
              isDisabled = false;
              serialFlush();
              break;
            case 'A': //Auto
              isTeleop = false;
              isAuto = true;
              isTest = false;
              isDisabled = false;
              serialFlush();
              break;
            case 't': //test
              isTeleop = false;
              isAuto = false;
              isTest = true;
              isDisabled = false;
              serialFlush();
              break;
            case 'D': //disabled
              isTeleop = false;
              isAuto = false;
              isTest = false;
              isDisabled = true;
              serialFlush();
              break;
            default:
              printMess("Bad Communications", 30);
              Serial.println("not valid state");
              serialFlush();
          }
          break;
        }
        case 'p':{ //modify vars for printMess function
          in = Serial.read();
          Serial.println(in);
          String inString = "";
          switch (in){ // change how long to wait before and after scroling
            case 'w':{ 
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              Serial.println(inString.toInt());
              int value = inString.toInt();
              waitTime = value;
              serialFlush();
              break;
            }
            case 's':{ // change how fast text scrolls
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              Serial.println(inString.toInt());
              int value = inString.toInt();
              waitTime = value;
              serialFlush();
              break;
            }
            default:
              printMess("Bad Communications", 30);
              Serial.println("not a valid setting");
              serialFlush();
          }
          break;
        }
        case 'D':{
          in = Serial.read();
          Serial.println(in);
          String inString = "";
          switch (in){
            case 'w':{ // write to display: expets three ints (000-999) for how long to display the message (in ticks) followed by a string of what will be displayed
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              Serial.println(inString.toInt());
              int duration = inString.toInt();
              String string = Serial.readString();
              Serial.println(string);
              printMess(string, duration);
              serialFlush();
              break;
            }
            case 'b':{ // change brightnes of the backlight
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              in = Serial.read();
              if (isDigit(in)){
                inString += in;
              }
              Serial.println(inString.toInt());
              int value = inString.toInt();
              if (value > 100){
                backlightPercent = 100;
              }else if (value < 0){
                backlightPercent = 0;
              }else{
                backlightPercent = value;
              }
              serialFlush();
              break;
            }
            default:
              printMess("Bad Communications", 30);
              Serial.println("not w or b");
              serialFlush();
          }
          break;
        }
        default:
          printMess("Bad Communications", 30);
          Serial.println("not s or p");
          serialFlush();
      }
      serialFlush();
    }else{
      printMess("Bad Communications", 30);
      Serial.println("not r/w");
      serialFlush();
    }
  }
  //if (time - lastComm > commTimeout)
}

void serialFlush() {
  while (Serial.available() > 0){
    Serial.read();
  }
}

//Prints a string to the top row of the 16x2 display, and scrolls it after (waitTime) ticks at a rate of one character per (scrollSpeed) ticks so that the entire string can be read
void printMess(String message, int timer){ //timeout var to specify the min amount of time a message should be shown. Useful for messages not declared in the main loop.
  lcd.setCursor(0, 0);
  
  if (message != last && time - messChanged > timeout){// if the message has changed (is different from tmp, is also be set off by the first call of this function), reset all values, and make tmp the new message.
    last = message; // the new tmp message is set to the message being printed
    startString = -1;
    stopString = 0;
    lastCharTime = 0;
    currentChar = 0; // all vars reset
    messChanged = time; //tell the program that the last time the message was changed was now
    timeout = timer;
  }else{
    message = last;
  }
  
  if (time - stopString > waitTime && !continuePrinting){ // if it has been at least 20 ticks since printing stopped, and we are not printing again
    startString = -1; //tell the function to start printing again (see next if statement)
  }
  
  if (startString == -1){ //if we have been told to start printing again (neg 1 will never be stored in this var normally, so we know the program has told us this)
    for (int i = 0; i < 16; i++){
      if (message.charAt(i) != NULL){ // if there is a character in the string, print it
        lcd.print(message.charAt(i));
      }else{
        lcd.print(" "); // if not, use a blank space (this is for strings that are less than 16 chars)
      }
    }
    currentChar = 1;
    startString = time; // tells program that the last time the string was started was now. The program will wait 2 seconds (default) until starting to scroll the text
    continuePrinting = true; // tells the program that it should try to scroll the text now
  }
  
  // if all criteria to scroll the text have been met (it's been at least 2 seconds, the string is long enough, and it's been a bit since the last time we scrolled the text) then scroll the text to the left
  if (time - startString > waitTime && time - lastCharTime > scrollSpeed && continuePrinting && message.length() > 16){
    lcd.clear(); // clears the screen, so that the new text can be printed
    for (int i = currentChar; i < currentChar + 16; i++){
      lcd.print(message.charAt(i)); //prints 16 chars of text from the string, offset by the value of currentChar, which is incremented every time this runns. This has the effect of scrolling the text by one each time
    }
    if (currentChar == (message.length() - 16)){ //if we are at the last character in the string
      continuePrinting = false; //tells the program to not scroll the text in the next loop
      stopString = time; //tells the program that we finished scrolling the text at this time
    }else{ //if we have not finished scrolling the text
      lastCharTime = time; //tell the program that the last time we scrolled the text was now
      currentChar++; //increment the currentChar var (see above for why)
    }
  }
}

// prints the ammount of time the arduino has been running on the bottom of the 16x2 screen in the format (m)m:ss:t where m is minutes, s is seconds, and t is ticks
void printTime(){
  int conversion = 1000/tickLength; // figures out how many ticks are in each second
  int sec = time/conversion; // uses above conversion factor to calculate seconds
  int mins = (int)sec/60; // gets minutes from seconds
  sec = sec - (60*((int)(sec/60))); //subtracts units of 60 seconds from sec so that it goes from 00-60, and then rolls over into mins 
  lcd.setCursor(0, 1);
  lcd.print(mins);
  lcd.print(":");
  if (sec < 10){ //prints an extra zero if seconds are in the ones (so that the seconds place always has two numbers)
    lcd.print("0");
  }
  lcd.print(sec);
  lcd.print(".");
  lcd.print(time - conversion*(int)(time/conversion)); // subtracts 100s of ticks off of the time in order to get ticks as a fraction of a second
}
