## Gotten for cloud simple project

### Gotten for cloud simulators running example

The goal here is to test cloud simulators that reproduce the behavior of data centres upon certain workload. Then, the input of these simulators are models of data centres along with models of workloads.

The following figure shows a meta-model to represent data centres, where a DataCentre is made of Network and any number of Racks, and each Rack contains several Boards. Boards are connected via Switches and have computing nodes with characteristics described by NodeTypes:

![Data centre meta-model](https://raw.githubusercontent.com/g0tten/images/main/model/data_centre_mm.png)

### The mrDSL program for data centres

Since it is difficult to establish an oracle to test if a data centre simulator S performs as expected, we use MT. The following listing shows the mrDSL program created with the Gotten framework to apply MT to this data centre simulators domain: 

```
metamodel datacentre "/sample.gotten/model/datac.ecore" with m1, m2
models "/sample.gotten/model/dcmodels"
metamodel workload "/sample.gotten/model/workload.ecore" with w1, w2
models "/sample.gotten/model/workloads"

datacentre input Features {
	context DataCentre def: NNodes: Int = racks->collect(numBoards*board.nodesPerBoard)->sum()
	context DataCentre def: CPU: Int = racks->collect(
            numBoards*board.nodesPerBoard*board.nodeType.CPUCores*board.nodeType.CPUSpeed)->sum()
}
output Features {
	Time : Long
	Energy : Long
}
Processor {
	Name: String
	Version: String
}
MetamorphicRelations {
	MR1 = [(( NNodes(m1) > NNodes(m2) ) and ((w1) == (w2)) ) implies ((Time(m1) <= Time(m2)))]
	MR2 = [(( CPU(m1) > CPU(m2) ) and ((w1) == (w2)) ) implies ((Energy(m1) <= Energy(m2)))]
}

```

### MRs for data centres brief description

Below we provide a brief description of these 2 MR for cloud simulators:

Relation | Description |
--- | :--- | 
MR1 | The cloud m1 contains more nodes than the cloud m2. The workloads w1 and w2 are equal. |
&nbsp; | MR1i = [( NNodes(m1) > NNodes(m2) ) and ((w1) == (w2)) ] |
&nbsp; | The time required to execute w1 over m1 should be less than or equal to the time required to exeute w2 over m2. |
&nbsp; | MR1o = [ (Time(m1) <= Time(m2)) ] |
MR2 | The cloud m1 contains more physical machines than the cloud m2. The workloads w1 and w2 are equal. |
&nbsp; | MR2i = [( CPU(m1) > CPU(m2) ) and ((w1) == (w2)) ] |
&nbsp; | The energy required to execute w1 over m1 should be less than or equal to the energy required to execute w2 over m2. |
&nbsp; | MR2o = [ (Energy(m1) <= Energy(m2))] |

### Gotten for cloud projects download links

- [Gotten for cloud simple project](https://github.com/g0tten/sample/zipball/main)
- [Gotten for cloud evaluation project](https://github.com/g0tten/evaluation/zipball/main)

### Acknowledgements

This work has been funded by the Spanish Ministry of Science (RTI2018-095255-B-I00, project "MASSIVE") and the R&D programme of Madrid (P2018/TCS-4314, project "[FORTE](https://antares.sip.ucm.es/forte-cm/)").

