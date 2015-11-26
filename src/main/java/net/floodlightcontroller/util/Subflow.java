package net.floodlightcontroller.util;

import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.TransportPort;

public class Subflow {
	private IPv4Address sourceIP;
	private IPv4Address destinationIP;
	private TransportPort sourcePort;
	private TransportPort destinationPort;
	
	public Subflow(IPv4Address sourceIP,IPv4Address destinationIP, TransportPort sourcePort, TransportPort destinationPort ){
		this.sourceIP=sourceIP;
		this.destinationIP = destinationIP;
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
	}
	
	public IPv4Address getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(IPv4Address sourceIP) {
		this.sourceIP = sourceIP;
	}

	public IPv4Address getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(IPv4Address destinationIP) {
		this.destinationIP = destinationIP;
	}

	public TransportPort getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(TransportPort sourcePort) {
		this.sourcePort = sourcePort;
	}

	public TransportPort getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(TransportPort destinationPort) {
		this.destinationPort = destinationPort;
	}
}
