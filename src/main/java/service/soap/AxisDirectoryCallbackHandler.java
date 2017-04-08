/**
 * AxisDirectoryCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package service.soap;


/**
 *  AxisDirectoryCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class AxisDirectoryCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public AxisDirectoryCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public AxisDirectoryCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for getContent method
     * override this method for handling normal response from getContent operation
     */
    public void receiveResultgetContent(
        service.soap.AxisDirectoryStub.GetContentResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getContent operation
     */
    public void receiveErrorgetContent(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getTitles method
     * override this method for handling normal response from getTitles operation
     */
    public void receiveResultgetTitles(
        service.soap.AxisDirectoryStub.GetTitlesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getTitles operation
     */
    public void receiveErrorgetTitles(java.lang.Exception e) {
    }
}
