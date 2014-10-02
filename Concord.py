import serial
import time
import sys

ser = serial.Serial( 'COM4', 9600, parity=serial.PARITY_ODD );

print "Sending Data Request"
time.sleep(1)
#request current state 02 20 22

ser.write( chr(0x0A) );
#time.sleep(0.1);
ser.write( chr(0x30) );
#time.sleep(0.1)
ser.write( chr(0x32) );
#time.sleep(0.1)
ser.write( chr(0x32) );
#time.sleep(0.1)
ser.write( chr(0x30) );
#time.sleep(0.1)
ser.write( chr(0x32) );
#time.sleep(0.1)
ser.write( chr(0x32) );
#time.sleep(0.1)

while True:
    bytesToRead = ser.inWaiting();
    if( bytesToRead > 0 ):
        #Read the number of bytes in the message
        inputBuffer = ser.read();
        if( ord(inputBuffer) == 0xA):
#            print "\nStart"
#            #//Start of a new message
#            #//Get the firts two bytes to determine message size
            sys.stdout.write(inputBuffer) 
            inputBuffer = ser.read(2);
            sys.stdout.write(inputBuffer) 
            messageSize = int( inputBuffer, 16 );
            #print "Size: " + str(messageSize);
            inputBuffer = ser.read( messageSize * 2 );
            sys.stdout.write(inputBuffer) 
            #print "Read " + str(len(inputBuffer));
            if( len(inputBuffer) == messageSize * 2 ):
                #print hex(messageSize) + " " + inputBuffer
                ser.write( chr(0x6) );
            else:
                ser.write( chr(0x15) );                
        elif( ord(inputBuffer) == 0x6 ):
            print "\nGot ACK"
        elif( ord( inputBuffer) == 0x15 ):
            print "\nGot NAK"          
    #time.sleep(1);
