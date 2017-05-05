/**
 * AxisDirectoryArticleNotFoundFaultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package service.soap;

public class AxisDirectoryArticleNotFoundFaultException extends java.lang.Exception {
    private static final long serialVersionUID = 1492794703489L;
    private service.soap.AxisDirectoryStub.AxisDirectoryArticleNotFoundFault faultMessage;

    public AxisDirectoryArticleNotFoundFaultException() {
        super("AxisDirectoryArticleNotFoundFaultException");
    }

    public AxisDirectoryArticleNotFoundFaultException(java.lang.String s) {
        super(s);
    }

    public AxisDirectoryArticleNotFoundFaultException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public AxisDirectoryArticleNotFoundFaultException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        service.soap.AxisDirectoryStub.AxisDirectoryArticleNotFoundFault msg) {
        faultMessage = msg;
    }

    public service.soap.AxisDirectoryStub.AxisDirectoryArticleNotFoundFault getFaultMessage() {
        return faultMessage;
    }
}
