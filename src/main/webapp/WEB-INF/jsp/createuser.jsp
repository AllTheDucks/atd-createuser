<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<fmt:message var="userStepTitle" key="createUser.createUserPage.userStep.title" />
<fmt:message var="entitlementsStep" key="createUser.createUserPage.entitlementsStep.title" />
<fmt:message var="entitlementsStepInstructions" key="createUser.createUserPage.entitlementsStep.instructions" />
<fmt:message var="userNameField" key="com.alltheducks.createuser.CreateUserAction.userName" />
<fmt:message var="roleNameField" key="com.alltheducks.createuser.CreateUserAction.roleName" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="atd-createuser-nav-createuser-system">
    <bbNG:cssBlock>
        <style type="text/css">
            span.fieldErrorText {
                margin-left: 1em;
                color: red;
            }
        </style>
    </bbNG:cssBlock>
    <stripes:form beanclass="com.alltheducks.createuser.stripes.CreateUserAction">
        <stripes:param name="createUser"/>

        <bbNG:dataCollection>
            <bbNG:step title="${userStepTitle}" instructions="">
                <bbNG:dataElement isRequired="true" label="${userNameField}">
                    <stripes:text name="userName" style="width:150px;" />
                    <stripes:errors field="userName"/>
                </bbNG:dataElement>
                <bbNG:dataElement isRequired="true" label="${roleNameField}">
                    <stripes:text name="roleName" style="width:150px;" />
                    <stripes:errors field="roleName"/>
                </bbNG:dataElement>
            </bbNG:step>
            <bbNG:step title="${entitlementsStep}" instructions="${entitlementsStepInstructions}">
                <bbNG:dataElement>
                    <stripes:textarea name="entitlements"  style="width:450px; height: 350px;"/>
                    <stripes:errors field="entitlements"/>
                </bbNG:dataElement>
            </bbNG:step>
            <bbNG:stepSubmit/>
        </bbNG:dataCollection>
    </stripes:form>

</bbNG:genericPage>