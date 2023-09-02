package com.pbft.tendermint.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsensusValue {
	private String value;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConsensusValue that = (ConsensusValue) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "ConsensusValue{" +
				"value='" + value + '\'' +
				'}';
	}
}
