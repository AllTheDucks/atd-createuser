package com.alltheducks.createuser;

import blackboard.persist.PersistenceException;
import blackboard.platform.security.persist.SystemRoleDbPersister;
import blackboard.platform.security.persist.SystemRoleEntitlementDbPersister;

/**
 * Created by shane on 28/08/2016.
 */
public class BbBeanFactory {
    public static SystemRoleDbPersister getSystemRoleDbPersister() throws PersistenceException {
        return SystemRoleDbPersister.Default.getInstance();
    }
    public static SystemRoleEntitlementDbPersister getSystemRoleEntitlementDbPersister() throws PersistenceException {
        return SystemRoleEntitlementDbPersister.Default.getInstance();
    }
}
