package com.zitgacore.chat.base.constant;

import com.zitga.core.constants.socket.BaseOpCode;

public class OpCode extends BaseOpCode {
	
	
	public static final int CLIENT_ADD = 20;
	
	public static final int CLIENT_REMOVE = 20;
	
	public static final int SERVER_MESSAGE_RECEIVED=30;
	
	public static final int CLIENT_MESSAGE_RECEIVED=30;
	
	// master
    public static final int MASTER_SERVER_ACTION = 2000;
}
