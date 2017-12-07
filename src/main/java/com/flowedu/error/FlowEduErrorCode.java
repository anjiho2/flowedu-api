package com.flowedu.error;

public enum FlowEduErrorCode {

	 	NOT_AUTHENTICATE(401, "Not authenticated"),
		NOT_AUTHORIZED(403, "Not authorized"),
		INTERNAL_ERROR(500, "Internal server error"),
		BAD_REQUEST(400, "Bad request, parameter not accepted"),
	 	NOT_ALLOW_FILE_NAME_KOREAN(901, "not allow korean name"),
	 	CUSTOM_DATA_LIST_NULL(902, "data list is null!"),
		CUSTOM_PAYMENT_LOG_NULL(903, "payment log data is null!"),
		CUSTOM_PAYMENT_CANCEL_ERROR(904, "auth_type is CR or D2"),
		CUSTOM_VIDEO_RESIZE_NULL(905, "video resize error!");

		int code;
		String msg;
		
		FlowEduErrorCode(int code, String msg){
			this.msg = msg;
			this.code = code;
		}

	    public int code()
	    {
	        return code;
	    }

	    public String msg()
	    {
	        return msg;
	    }
}
