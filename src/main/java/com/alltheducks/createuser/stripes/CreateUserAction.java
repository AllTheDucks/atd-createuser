package com.alltheducks.createuser.stripes;

import blackboard.data.ValidationException;
import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbPersister;
import blackboard.platform.plugin.PlugInUtil;
import blackboard.platform.role.PortalRoleManager;
import blackboard.platform.security.RoleEntitlement;
import blackboard.platform.security.SystemRole;
import blackboard.platform.security.persist.SystemRoleDbPersister;
import blackboard.platform.security.persist.SystemRoleEntitlementDbPersister;
import blackboard.platform.servlet.InlineReceiptUtil;
import com.alltheducks.bb.stripes.BlackboardActionBeanContext;
import com.alltheducks.bb.stripes.EntitlementRestrictions;
import com.alltheducks.bb.stripes.LoginRequired;
import com.alltheducks.bundleutils.BundleService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LoginRequired
@EntitlementRestrictions(entitlements = "atd.createuser.MODIFY", errorPage = "/error.jsp")
@UrlBinding("/createuser")
public class CreateUserAction implements ActionBean {

    final Logger logger = LoggerFactory.getLogger(CreateUserAction.class);

    private BlackboardActionBeanContext context;

    @SpringBean
    private SystemRoleDbPersister systemRoleDbPersister;
    @SpringBean
    private SystemRoleEntitlementDbPersister systemRoleEntitlementDbPersister;
    @SpringBean
    private UserDbPersister userDbPersister;
    @SpringBean
    private BundleService bundleService;

    @Validate(required = true)
    private String userName;
    @Validate(required = true)
    private String roleName;
    private String entitlements;

    @DefaultHandler
    @DontValidate
    public Resolution displayPage() {
        return new ForwardResolution("/WEB-INF/jsp/createuser.jsp");
    }

    public Resolution createUser() throws ValidationException, PersistenceException {

        logger.info("Creating user '{}' and role '{}' with entitlements:\r\n{}", userName, roleName, entitlements);

        SystemRole systemRole = new SystemRole();
        systemRole.setIdentifier(roleName);
        systemRole.setName(roleName);
        systemRole.setDescription(bundleService.getLocalisationString("createuser.systemrole.description"));
        systemRoleDbPersister.persist(systemRole);

        final String[] entitlementsList = entitlements.split("[\r\n]+");
        int entitlementCount = 0;
        for (String entitlement : entitlementsList) {
            if(!entitlement.isEmpty()) {
                RoleEntitlement roleEntitlement = new RoleEntitlement(entitlement);
                roleEntitlement.setRoleIdentifier(roleName);
                systemRoleEntitlementDbPersister.persist(roleEntitlement);
                entitlementCount++;
            }
        }

        User user = new User();
        user.setUserName(userName);
        user.setBatchUid(userName);
        user.setGivenName(userName);
        user.setFamilyName(userName);
        user.setSystemRoleIdentifier(roleName);
        user.setPassword(RandomStringUtils.randomAscii(32));

        userDbPersister.persist(user);

        final String url = InlineReceiptUtil.addSuccessReceiptToUrl("createuser",
                bundleService.getLocalisationString("createuser.create.success",
                        userName, roleName, entitlementCount));
        return new RedirectResolution(url, false);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(String entitlements) {
        this.entitlements = entitlements;
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }
}