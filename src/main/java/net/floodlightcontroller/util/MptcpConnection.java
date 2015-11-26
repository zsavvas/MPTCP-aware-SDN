package net.floodlightcontroller.util;

import java.util.ArrayList;

import net.floodlightcontroller.routing.Route;

import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.TransportPort;

public class MptcpConnection {
	private IPv4Address primarySourceIP;
	private IPv4Address primaryDestinationIP;
	private int primarySourcePort;
	private int primaryDestinationPort;
	private byte[] token;
	private byte[] senderKey;
	private byte[] receiverKey;
	ArrayList<IpRoutes> iproutes;
	
	public MptcpConnection(IPv4Address sourceIP,IPv4Address destinationIP,int sourcePort,
			int destinationPort, byte [] senderKey){
		this.primarySourceIP = sourceIP;
		this.primaryDestinationIP = destinationIP;
		this.primarySourcePort = sourcePort;
		this.senderKey = senderKey;
		iproutes = new ArrayList<IpRoutes>();
	}
	public void addRoutes(IPv4Address source, IPv4Address destination,ArrayList<Route> v,int subNum){
		IpRoutes n = new IpRoutes(source,destination,v,subNum);
		this.iproutes.add(n);
	}
	public ArrayList<Route> getAllRoutes(IPv4Address source,IPv4Address destination){
		
		ArrayList<Route> n = new ArrayList<Route>();
		for(int i=0;i<iproutes.size();i++){
			if(source.equals(iproutes.get(i).getSourceIp()) && destination.equals(iproutes.get(i).getDestinationIp())){
				return iproutes.get(i).getAvailableRoutes();
			}
		}
			return n;
	}
	
	public void setRoutes(IPv4Address source,IPv4Address destination, ArrayList<Route> v){
		for(int i=0;i<iproutes.size();i++){
			if(source.equals(iproutes.get(i).getSourceIp()) && destination.equals(iproutes.get(i).getDestinationIp())){
				 iproutes.get(i).setAvailableRoutes(v);
			}
		}
	}
	public boolean ipsAlreadySeen(IPv4Address source,IPv4Address destination){
		for(int i=0;i<iproutes.size();i++){
			if(source.equals(iproutes.get(i).getSourceIp()) && destination.equals(iproutes.get(i).getDestinationIp())){
				return true;
			}
		}
			return false;
	}
	public Route getNextRoute(IPv4Address source,IPv4Address destination){
		Route newRoute=null;
		for(int i=0;i<iproutes.size();i++){
			if(source.equals(iproutes.get(i).getSourceIp()) && destination.equals(iproutes.get(i).getDestinationIp())){
				IpRoutes ir = iproutes.get(i);
				newRoute=ir.getNextRoute();
				ir.increaseCurrentSubflowNumber();
			}
		}
		return newRoute;
	}
	

	
	public IPv4Address getPrimarySourceIP() {
		return primarySourceIP;
	}

	public void setPrimarySourceIP(IPv4Address primarySourceIP) {
		this.primarySourceIP = primarySourceIP;
	}

	public IPv4Address getPrimaryDestinationIP() {
		return primaryDestinationIP;
	}

	public void setPrimaryDestinationIP(IPv4Address primaryDestinationIP) {
		this.primaryDestinationIP = primaryDestinationIP;
	}

	public int getPrimarySourcePort() {
		return primarySourcePort;
	}

	public void setPrimarySourcePort(int primarySourcePort) {
		this.primarySourcePort = primarySourcePort;
	}

	public int getPrimaryDestinationPort() {
		return primaryDestinationPort;
	}

	public void setPrimaryDestinationPort(int primaryDestinationPort) {
		this.primaryDestinationPort = primaryDestinationPort;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public byte[] getSenderKey() {
		return senderKey;
	}

	public void setSenderKey(byte[] senderKey) {
		this.senderKey = senderKey;
	}

	public byte[] getReceiverKey() {
		return receiverKey;
	}

	public void setReceiverKey(byte[] receiverKey) {
		this.receiverKey = receiverKey;
	}
}
