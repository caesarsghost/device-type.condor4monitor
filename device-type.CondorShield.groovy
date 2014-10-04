/**
 *  CondorShield
 *
 *  Copyright 2014 CaesarsGhost
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
	// Automatically generated. Make future change here.
	definition (name: "CondorShield", author: "CaesarsGhost", namespace: "CaesarsGhost") {
		capability "Contact Sensor"
		capability "Alarm"
		capability "Switch"
        capability "Polling"
        command "armstay"
        command "Disarm"
        command "displayrequest"
        command "displaystop"
        command "keyStar"
        command "keyOne"
        command "keyTwo"
        command "keyThree"
        command "keyFour"
        command "keyFive"
        command "keySix"
        command "keySeven"
        command "keyEight"
        command "keyNine"
        command "keyZero"
        command "keyA"
        command "keyB"
        command "keyPound"
        command "armSilent"
        command "armLoud"
        
        attribute "paneldisplay", "string"        
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
        standardTile("ArmTile", "device.CondorShield", canChangeIcon: true, inactiveLabel: false) {
            state "Arm", label: 'Arm', action: "armstay", icon: "st.Home.home3", backgroundColor: "#ffa81e", nextState: "sendingArm"
            state "sendingArm", label: 'Arming', action: "armstay", icon: "st.Weather.weather1", backgroundColor: "#cccccc"
            state "DisArm", label: 'Disarm', action: "Disarm", icon: "st.Home.home3", backgroundColor: "#79b821", nextState: "sendingDisarm"
            state "sendingDisarm", label: 'Disarming', action: "Disarm", icon: "st.Weather.weather1", backgroundColor: "#cccccc"
        }
        standardTile("silent", "device.silent", inactiveLabel: false) {        						
			state "loud", label: 'loud', action: "armSilent", icon: "st.Outdoor.outdoor10", backgroundColor: "#79b821"            
			state "silent", label: 'silent', action:"armLoud", icon: "st.Outdoor.outdoor9", backgroundColor: "#ffa81e"	            
		}        
        standardTile("Zone 1", "device.zone1", inactiveLabel: false) {
        	state "closed", label: 'Zone 1', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
			state "open", label: 'Zone 1', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"			
		}  
        standardTile("Zone 2", "device.zone2", inactiveLabel: false) {			
			state "closed", label: 'Zone 2', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
            state "open", label: 'Zone 2', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
		}  
        standardTile("Zone 3", "device.zone3", inactiveLabel: false) {
        	state "closed", label: 'Zone 3', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
			state "open", label: 'Zone 3', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"			
		}  
        standardTile("Zone 4", "device.zone4", inactiveLabel: false) {			
			state "closed", label: 'Zone 4', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
            state "open", label: 'Zone 4', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
		}  
        standardTile("Zone 5", "device.zone5", inactiveLabel: false) {			
			state "closed", label: 'Zone 5', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
            state "open", label: 'Zone 5', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
		}  
        standardTile("Zone 6", "device.zone6", inactiveLabel: false) {			
			state "closed", label: 'Zone 6', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
            state "open", label: 'Zone 6', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
		}  
		standardTile("refresh", "device.alarmMode", inactiveLabel: false, decoration: "flat") {
			state "default", action:"polling.poll", icon:"st.secondary.refresh"
		} 
		standardTile("displayupdate", "device.displayupdate", inactiveLabel: false) { 
			state "inactive", label: "Display", action:"displayrequest", icon: "st.Electronics.electronics18", backgroundColor: "#ffa81e"
            state "active", label: "Display", action:"displaystop", icon: "st.Electronics.electronics18", backgroundColor: "#79b821"
		}        
        valueTile("paneldisplay", "device.paneldisplay", width: 2, height: 1, decoration: "flat") {
            state "default", label:'${currentValue}'
        }        
	    standardTile("keyStar", "device.keyStar", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyStar", label: '*'
		}  
		standardTile("keyOne", "device.keyOne", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyOne", label: '1'
		}  
		standardTile("keyTwo", "device.keyTwo", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyTwo", label: '2'
		}  
		standardTile("keyThree", "device.keyThree", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyThree", label: '3'
		}  
		standardTile("keyFour", "device.keyFour", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyFour", label: '4'
		}  
		standardTile("keyFive", "device.keyFive", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyFive", label: '5'
		}  
		standardTile("keySix", "device.keySix", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keySix", label: '6'
		}  
		standardTile("keySeven", "device.keySeven", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keySeven", label: '7'
		}  
		standardTile("keyEight", "device.keyEight", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyEight", label: '8'
		}  
		standardTile("keyNine", "device.keyNine", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyNine", label: '9'
		}  
		standardTile("keyZero", "device.keyZero", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyZero", label: '0'
		} 
		standardTile("keyA", "device.keyA", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyA", label: 'Up'
		} 
		standardTile("keyB", "device.keyB", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyB", label: 'Down'
		}
		standardTile("keyPound", "device.keyPound", width: 1, height: 1, inactiveLabel: false, decoration: "flat") {
			state "default", action:"keyB", label: '#'
		}         
	}
    
    main "ArmTile"
    details(["ArmTile", "silent", "Zone 1", "Zone 2", "Zone 3", "Zone 4", "Zone 5", "Zone 6","refresh", "displayupdate",
    "paneldisplay"])
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
    
    def value = zigbee.parse(description)?.text
    
    log.debug "Zigbee '${value}' '${value.length()}'"    
    
    
    if( value == "ping" || value == null || value.length() < 2 )
    {
    	log.debug "Skipping Value"
   		return
    }
    
	//sendEvent( name:"paneldisplay", value: value )    
    
	if( value.contains("Dupdate") )
    {
    	log.debug "Setting Display Active"
        sendEvent( name:"paneldisplay", value: "WAITING..." )
    	return createEvent(name: "displayupdate", value:"active", displayed: true, isStateChange: true )
    }
    else if( value == "Dstop" )
    {
    	log.debug "Setting Display Inactive"        
        sendEvent( name:"paneldisplay", value: "READY" )
    	return createEvent(name: "displayupdate", value:"inactive", displayed: true, isStateChange: true )
    }
    else if( value.contains("zone") )
    {
    	String delims = ","
        String[] tokens = value.split(delims)
        log.debug tokens[0]
        log.debug tokens[1]
        return createEvent(name: tokens[0], value: tokens[1], displayed: true, isStateChange: true)
    }    
    else
    {
    	//This is raw condor data        
	 	def decodedValue = value.decodeHex();
        
        switch( decodedValue[1] )
        {
        case 0x21:
        	def zoneNumber = decodedValue[4]<<8 | decodedValue[5]
            return createEvent( name: "zone"+ zoneNumber.toString(), value: decodedValue[6] == 0 ? "closed" : "open" )
        	break
        case 0x22:
        	switch( decodedValue[2] )
            {
            case 0x01: //Arming level
                if( state.bRefresh == "True" )
                {
                    sendEvent( name: "paneldisplay", value: "Done" )
                    state.bRefresh = "False"
                }
               
                if( (decodedValue[7]&0x0E) != 0 )
                {
                    //We are armed allow the system to be disarmed
                    return createEvent(name: "CondorShield", value: "DisArm", displayed: true, isStateChange: true)
                }
                else
                {   
                    //We are disarmed allow the system to be armed again
                    return createEvent(name: "CondorShield", value: "Arm", displayed: true, isStateChange: true)            	
                }
                break;
            case 0x09:
                def outputString = ''
                def loopSize = decodedValue.size() - 1
                for( int i = 6; i < loopSize; i++ )
                {
                    outputString = outputString + DisplayNames( decodedValue[i] )
                }
                log.debug outputString
                sendEvent( name:"paneldisplay", value: outputString )
                sendEvent( name:"displayupdate", value: "active", displayed: true, isStateChange: true  )
            }
        }
    }
}


def installed()
{
  resetState()
}

def updated()
{
  resetState()
}


// handle commands
def poll() {
	log.debug "Executing 'poll'"

	resetState();

	state.bRefresh = "True"

    zigbee.smartShield(text: "Refresh").format()
}

def resetState()
{
	for(int i = 1; i<7; i++ )
    {
        sendEvent( name: ("zone"+ i.toString()), value: "open" )
        sendEvent( name: ("zone"+ i.toString()), value: "closed" )
    }
                
	displaystop()
    
    sendEvent( name:"paneldisplay", value: "REFRESH" )
    
    armLoud()
}

def armSilent()
{
	state.bLoud = "False"
    sendEvent( name:"silent", value: "silent" )
}

def armLoud()
{
	state.bLoud = "True"
    sendEvent( name:"silent", value: "loud" )
}

// handle commands
def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
    
    log.debug device.latestValue("paneldisplay")
}

def strobe() {
	log.debug "Executing 'strobe'"
	// TODO: handle 'strobe' command   	
}

def siren() {
	log.debug "Executing 'siren'"
	// TODO: handle 'siren' command
}

def armstay() {
	log.debug "Executing 'ArmStay'"
    if( state.bLoud == "False" )
    {
		sendKeypressArray( [ (byte)0x05, (byte)0x28 ] as byte[] )
    }
    else
    {
    	sendKeypress( 0x28 )
    }
}


def Disarm() {
	log.debug "Executing 'Disarm'"
    if( state.bLoud == "False" )
    {
		sendKeypressArray( [ (byte)0x05, (byte)0x20 ] as byte[] )
    }
    else
    {
    	sendKeypress( 0x20 )
    }
	// TODO: handle 'both' command
}

def both() {
	log.debug "Executing 'both'"
	// TODO: handle 'both' command
}

def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
}

def displayrequest()
{
	log.debug "Display request"
    zigbee.smartShield( text: "Dupdate" ).format()
}

def displaystop()
{	
	zigbee.smartShield( text: "Dstop" ).format()
} 

def keyStar()
{
	sendKeypress( 0x0A );
}

def keyThree()
{
	sendKeypress( 0x03 );
}

def keyOne()
{
	sendKeypress( 0x01 );
}

def keyTwo()
{
	sendKeypress( 0x02 );
}

def keyFour()
{
	sendKeypress( 0x04 );
}

def keyFive()
{
	sendKeypress( 0x05 );
}

def keySix()
{
	sendKeypress( 0x06 );
}

def keySeven()
{
	sendKeypress( 0x07 );
}

def keyEight()
{
	sendKeypress( 0x08 );
}

def keyNine()
{
	sendKeypress( 0x09 );
}

def keyZero()
{
	sendKeypress( 0x00 );
}

def keyPound()
{
	sendKeypress( 0x0B );
}

def keyA()
{
	sendKeypress( 0x2C );
}

def keyB()
{
	sendKeypress( 0x30 );
}

def sendKeypress( int key )
{
	def OutArray = new byte[1]
	OutArray = (byte)key
	sendKeypressArray( OutArray )
}

def sendKeypressArray( byte[] keys )
{
	def OutArray = new byte[ keys.size() + 5 ]
    
    OutArray[0] = OutArray.size() - 1
    OutArray[1] = 0x40
    OutArray[2] = 0x01
    OutArray[3] = 0x00
    
    for(int i=0; i<keys.size(); i++)
    {
    	OutArray[ i + 4 ] = keys[i]
    }    
    
    def checksum = 0
    for( int i = 0; i<OutArray.size()-1; i++ )
    {
    	checksum = checksum + OutArray[i]
    }
    
    OutArray[ OutArray.size()-1 ] = checksum%256    
    
    def keyPress = "TX " + OutArray.encodeHex()
    
    log.debug "Sending Keypress: " + keyPress.toUpperCase()
    
    zigbee.smartShield( text: keyPress.toUpperCase() ).format()
}

def DisplayNames( Integer Index )
{
	switch( Index&0xFF )
    {
    case 0x00:
    	return '0'
    case 0x01:
    	return '1'
    case 0x02:
    	return '2'
    case 0x03:
    	return '3'
    case 0x04:
    	return '4'
    case 0x05:
    	return '5'
    case 0x06:
    	return '6'        
    case 0x07:
    	return '7'
    case 0x08:
    	return '8'
    case 0x09:
    	return '9'
    case 0x0C:
    	return '#'
    case 0x0D:
    	return ':'        
    case 0x0E:
    	return '/'
    case 0x0F:
    	return '?'
    case 0x10:
    	return '.'
    case 0x11:
    	return 'A'
    case 0x12:
    	return 'B'
    case 0x13:
    	return 'C'
    case 0x14:
    	return 'D'
    case 0x15:
    	return 'E'
    case 0x16:
    	return 'F'
    case 0x17:
    	return 'G'
    case 0x18:
    	return 'H'
    case 0x19:
    	return 'I'
    case 0x1A:
    	return 'J'
    case 0x1B:
    	return 'K'
    case 0x1C:
    	return 'L'
    case 0x1D:
    	return 'M'
    case 0x1E:
    	return 'N'
    case 0x1F:
    	return 'O'
    case 0x20:
    	return 'P'
    case 0x21:
    	return 'Q'
    case 0x22:
    	return 'R'
    case 0x23:
    	return 'S'
    case 0x24:
    	return 'T'
    case 0x25:
    	return 'U'
    case 0x26:
    	return 'V'
    case 0x27:
    	return 'W'
    case 0x28:
    	return 'X' 
    case 0x29:
    	return 'Y' 
    case 0x2A:
    	return 'Z' 
    case 0x2B:
    	return ' ' 
    case 0x2C:
    	return '\''     
    case 0x2D:
    	return '-'
    case 0x2E:
    	return '_'
    case 0x2F:
    	return '*'     
    case 0xC0:
    	return 'STAY'
    case 0xFB:
    	return "\r\n"
    case 0xF9:
    	return "\r\n"      
    }
    return '@'
}