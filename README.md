# MPTCP-aware SDN controller

This implementation is based on Floodlight v1.1. Results with the use of this controller can be found in http://arxiv.org/pdf/1511.09295.pdf

#### Main Components/Files Modified from base Floodlight
- **Forwarding.java** :Provides random or deterministic assignment of subflows to paths. You can configure the forwarding by tweaking the following parameters that are in the Forwarding file.

  - ***randomForwarding*** : Set to false if you want to use the MPTCP-aware approach
  - ***topo***: Set to either "ft" or "jelly". Used in TopologyManager. The reason is that the filtering process of paths is different in these topologies. 
For instance in FatTree we retrieve from the shortest paths while in jelly
we retrieve from all the calculated paths
            
  - ***isDisjoint***: Set to true if you want to calculate disjoint paths

  - ***k***: Set the number of paths that you want to retrieve. By setting to zero the TopologyManager retrieves 
            all the shortest paths.
           
    Note that to use 8-edge-disjoint paths you have to set isDisjoint=true and k=8    
- **TopologyManager.java** : Extended so it finds all the available paths (*searchDFS* method). Then it filters all the available paths according to the request (*getRoutes* method ). Request might involve *shortest* paths, *k-shortest* paths and *k-edge-disjoint* paths
- **TCP.java (net.floodlightcontroller.packet)**: Added methods to extract MPTCP-options and data from the packets

- **Added helpful classes such as MptcpConnection,IpRoutes,ExpiringMap (net.floodlightcontroller.util)**

#### Our testbed setup

- Ubuntu 14.04.2 LTS 
- MPTCP Linux Kernel Implementation version 0.90
- Mininet version 2.2.1
- Open vSwitch version 2.0.2

##### Topologies Configuration

- 8-ary FatTree: 128 hosts with 80 switches.
- Jellyfish Topology: 120 hosts with 60 switches and 10 ports per switch. 
- DH-Jellyfish Topology: 120 hosts with 60 switches and 10 ports per switch

In all the above topologies all links are set to 1MB/s and have a maximum queue size of 100 packets. The capacities are scaled down to avoid the reception of erratic results due to host CPU bottleneck.

Note that in our experiments we computed the sets of paths and stored them to the Forwarding Module before the actual experiments. This decision was made to provide fairness between each experiment. Recall that upon the calculation of a set of paths we store the set to the Forwarding Module for future use. If we didn't computed the sets in advance then the first experiment would have been penalized by the extra overhead of calculating the paths while all subsequent experiments will not have that extra overhead. To achieve similar behavior you can send a "dummy" request for each pair of source/destination IP addresses pair and then start your actual experiments


Floodlight v1.1 can be found on GitHub at:  
http://github.com/floodlight/floodlight/tree/v1.1.




