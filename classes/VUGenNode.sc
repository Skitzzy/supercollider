ScinNode {
	classvar <addActions;

	var <>nodeID;
	var <>server;
	var <>group;

	*initClass {
		addActions = (
			addToHead: 0,
			addToTail: 1,
			addBefore: 2,
			addAfter: 3,
			addReplace: 4,
			0: 0, 1: 1, 2: 2, 3: 3, 4: 4
		);
	}

	*basicNew { |server, nodeID|
		server = server ? ScinServer.default;
		^super.newCopyArgs(nodeID ?? { UniqueID.next }, server);
	}

	*actionNumberFor { |addAction|
		^addActions[addAction];
	}

	free {
		server.sendMsg('/scin_n_free', nodeID);
	}

	run { |flag=true|
		server.sendMsg('/scin_n_run', nodeID, flag.binaryValue);
		^this;
	}

	set { |...args|

	}

	moveBefore { |targetNode|
		group = targetNode.group;
		server.sendMsg('/scin_n_before', nodeID, targetNode.nodeID);
	}

	moveAfter { |targetNode|
		group = targetNode.group;
		server.sendMsg('/scin_n_after', nodeID, targetNode.nodeID);
	}

	moveToTail { |targetGroup|
		(targetGroup ? server.defaultGroup).moveNodeToHead(this);
	}

	moveToHead { |targetGroup|
		(targetGroup ? server.defaultGroup).moveNodeToTail(this);
	}

	asScinTarget { ^this; }
}

ScinGroup : ScinNode {

}

VSynth : ScinNode {
	var <>defName;

}