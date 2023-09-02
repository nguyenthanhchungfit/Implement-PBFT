package com.pbft.tendermint.common;

import com.google.protobuf.ByteString;

/**
 * @author chungnt
 * @version 1.0
 * @date 21/08/2023
 */
public class ConstantResult {
	public static final int ERR_SUCCESS = 0;
	public static final int ERR_FAILED = -1;
	public static final int ERR_IGNORED = -2;

	public static final int RET_AGREE = 1;
	public static final int RET_NOT_ENOUGH_VOTE = 0;
	public static final int RET_DISAGREE = -1;
}
