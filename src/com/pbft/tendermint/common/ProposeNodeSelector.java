package com.pbft.tendermint.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposeNodeSelector {
	private List<Integer> currentNodeIds = new ArrayList<>();

	public void addNewNodeId(int nodeId) {
		if (!currentNodeIds.contains(nodeId)) {
			this.currentNodeIds.add(nodeId);
		}
	}

	public int getProposeNodeId(int height, int round) {
		int size = currentNodeIds.size();
		int idx = height % size;
		return currentNodeIds.get(idx);
	}
}
