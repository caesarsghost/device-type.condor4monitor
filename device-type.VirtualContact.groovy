/**
 *  Virtual Contact
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
	definition (name: "Virtual Contact", namespace: "CaesarsGhost", author: "CaesarsGhost") {
    capability "Contact Sensor"
    command "open"
    command "closed"    
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
        standardTile("contact", "device.contact", inactiveLabel: false) {
			state "open", label: '${name}', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
			state "closed", label: '${name}', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
		}  
	}
    
    main "contact"
}


def open( String name ) {
	log.debug "Open"   
    sendEvent (name: "contact", value: "open" )
}

def closed( String name ) {
	log.debug "Close"
    sendEvent (name: "contact", value: "closed" )
}
// parse events into attributes
def parse(String description) {
	return NULL

}
