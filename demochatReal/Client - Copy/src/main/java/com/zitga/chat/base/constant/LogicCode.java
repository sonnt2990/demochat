package com.zitga.chat.base.constant;

import com.zitga.core.constants.BaseLogicCode;

public class LogicCode extends BaseLogicCode {
    // handshake
    public static final int HANDSHAKE_CLIENT_TIME_INVALID = 11;
    public static final int HANDSHAKE_CLIENT_VERSION_FORBIDDEN = 12;
    public static final int HANDSHAKE_BUNDLE_NAME_FORBIDDEN = 13;
    public static final int HANDSHAKE_SHARED_SECRET_INVALID = 14;
    public static final int HANDSHAKE_CHALLENGE_INVALID = 15;
    public static final int HANDSHAKE_NUMBER_CSV_FILE_MISMATCHED = 16;
    public static final int HANDSHAKE_CSV_HASH_MISMATCHED = 17;
}
