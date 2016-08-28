<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<fmt:message var="errorPageTitle" key="createuser.errorPage.title" />
<fmt:message var="errorPageMessage" key="createuser.errorPage.message" />


<bbNG:learningSystemPage ctxId="ctx" title="${errorPageTitle}">
    <h2>${errorPageMessage}</h2>
</bbNG:learningSystemPage>