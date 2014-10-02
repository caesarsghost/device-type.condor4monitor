//*****************************************************************************
/// @file
/// @brief
///   Condor Monitor
///   Filters condor bus messages from the Condor 4 and sends a string representation
///   to ST.  Will also consume an ASCII Hex string to send on the condor bus.
///   This puts all the parsing and message format responsibilty on the ST Device Type.
//*****************************************************************************
#include <SoftwareSerial.h>   //TODO need to set due to some weird wire language linker, should we absorb this whole library into smartthings
#include <SmartThings.h>
 
#define PIN_THING_RX    3
#define PIN_THING_TX    2 
#define DISPLAY_PARTITIION 1

//Define Global Variables
SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor
 
//Buffer Used to Store RX data from the Panel
unsigned short CondorData[64];
char RawInputBuffer[128];
char CurrentSendCommand[128];

int ledPin = 13;
int stateLED;           // state to track last set value of LED
boolean isDebugEnabled;

int freeRam () {
  extern int __heap_start, *__brkval; 
  int v; 
  return (int) &v - (__brkval == 0 ? (int) &__heap_start : (int) __brkval); 
}
 
void setup()
{
  // setup default state of global variables
  stateLED = 0;                 // matches state of hardware pin set below
  isDebugEnabled = true;
  
  // setup hardware pins 
  pinMode(ledPin, OUTPUT);     // define PIN_LED as an output
  digitalWrite(ledPin, LOW);   // set value to LOW (off) to match stateLED=0
 
  Serial.begin(9600, SERIAL_8O1);         // setup serial with a baud rate of 9600
  
  //Send Startup Message
  //smartthing.send( "Condor SmartThings Interface v1.0" );
    
  digitalWrite(ledPin, HIGH);  // turn LED on  
  //smartthing.shieldSetLED(0, 0, 0);  
}

//---------------------------------------------------------------------//

unsigned short RawIndex = 0;
unsigned short CurMessageSize = 0;
boolean bMessage = false;
int bSendDisplayData = 0;
void loop()
{
  millis();
  
  // run smartthing logic
  smartthing.run();
  
  if( Serial.available() )
  {
    char tmpChar = Serial.read();
    if( tmpChar == 0xA )
    {
      //Reset our raw buffer and pull the size
      RawIndex = 0;
      CurMessageSize = 0;
      bMessage = true;
      //smartthing.shieldSetLED(0, 0, 1);  
    }
    else if( bMessage == true )
    {
      //Get the data byte
      RawInputBuffer[RawIndex] = tmpChar;
      RawIndex = RawIndex + 1;
      
      if( RawIndex == 2 )
      {
        //Pull the size information
        RawInputBuffer[RawIndex] = 0; //Null terminate our sting
        CurMessageSize = (strtol( RawInputBuffer, NULL, 16 ) * 2) + 2;
      }
      else if( RawIndex >= CurMessageSize && CurMessageSize > 1 )
      {
        //Show we are processing
        //smartthing.shieldSetLED(0, 0, 1);  
        
        //Null terminate our string
        RawInputBuffer[ RawIndex ] = 0;
        
        //Convert the string to integer values
        char tmpBuffer[3];
        tmpBuffer[2] = 0;
        for( int i = 0; i < (CurMessageSize / 2) + 1; i++ )
        {
          tmpBuffer[0] = RawInputBuffer[i*2];
          tmpBuffer[1] = RawInputBuffer[(i*2)+1];
          CondorData[i] = strtol( tmpBuffer, NULL, 16 ); 
        }
        
        //verify the checksum
        unsigned int checksum = 0;        
        for(int i=0; i<CondorData[0]; i++ )
        {
          checksum = (checksum + CondorData[i])%256;
        }
        
        checksum = checksum%256;
        
        if( checksum == (CondorData[CondorData[0]]&0xFF) )
        {
          //We have a full message process it
          Serial.write( 0x6 );

          //PrintCondorPacket();
          //Filter out all the messages we don't care about
          switch( CondorData[1] )
          {
            case 0x02: //Automation event Lost Message
            case 0x20: //Clear automation dynamic image            
              //Need to perform system resync
              messageCallout( "Refresh" );
              break;
            case 0x21: //Zone Status
              smartthing.send( RawInputBuffer );
              break;
            case 0x22:
              switch( CondorData[2] )
              {
                case 0x01: //Arming level
                case 0x02: //Alarm trouble code
                  smartthing.send( RawInputBuffer );
                  break;
                case 0x09: //Display data
                  if( bSendDisplayData > 0 && (CondorData[3] == DISPLAY_PARTITIION) )
                  {
                    bSendDisplayData = bSendDisplayData - 1;
                    if( bSendDisplayData <= 0 )
                    {
                      //Stop the message updates
                      messageCallout( "Dstop");
                    }
                    smartthing.send( RawInputBuffer );
                  }                
                  break;
              };
          };

          //Green show we got a valid message 
          //smartthing.shieldSetLED(0, 1, 0); 
        }
        else
        { 
          //Don't see anyting, it actually seems to work better if you just don't respond.
          Serial.write( 0x15 );
          
          //PrintCondorPacket();
          
          //Set led red for invalid message
          //smartthing.shieldSetLED(1, 0, 0);           
        }
        
        //Reset our receive string
        RawIndex = 0;
        CurMessageSize = 0;
        bMessage = false;
      }
    }
  }
}

//---------------------------------------------------------------------//

void PrintCondorPacket( void )
{
  for(int i=0; i<=CondorData[0]; i++ )
  {
    Serial.print( CondorData[i], HEX );
  }
  Serial.println("");
}

//---------------------------------------------------------------------//

void messageCallout(String message)
{
  // if message contents equals to 'on' then call on() function
  // else if message contents equals to 'off' then call off() function
  if ( message.substring(0,2) == "TX" && message.length() >= 9 )
  {
    //Send the start character 0xA
    Serial.write( 0xA );
    for( int i=3; i<message.length(); i++ )
    {      
      CurrentSendCommand[ i-3 ] = message[i];
      Serial.write( CurrentSendCommand[ i - 3 ] );
    }   
  }
  else if ( message.equals("Refresh") )
  {
    //Newline to start transmission
    Serial.write( 0x0A );
    //0x02
    Serial.write( 0x30 );
    Serial.write( 0x32 );
    //0x20
    Serial.write( 0x32 );
    Serial.write( 0x30 );
    //0x22
    Serial.write( 0x32 );
    Serial.write( 0x32 );    
  }
  else if (message.equals("Disarm"))
  {
    //Disarm the system
    //TODO::encode the disarm command
    smartthing.send("Disarmed");  
  }
  else if( message.equals("Dupdate") )
  {
    bSendDisplayData = 50;
    smartthing.send("Dupdate");
  }
  else if( message.equals("Dstop") )
  {
    bSendDisplayData = 0;
    smartthing.send("Dstop");
  }  
}
