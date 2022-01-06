package com.subscription.expception;

/**
 * Service Exception class
 */
public class ServiceException  extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Status code */
    private String code;

    /**
     * Constructor
     *
     * @param code
     *     status code
     * @param message
     *     error message
     */
    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
