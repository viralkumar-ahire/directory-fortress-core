/*
 * Copyright (c) 2009-2012. Joshua Tree Software, LLC.  All Rights Reserved.
 */

package com.jts.fortress;

import com.jts.fortress.cfg.Config;
import com.jts.fortress.rbac.ClassUtil;
import com.jts.fortress.util.attr.VUtil;

/**
 * Creates an instance of the DelAccessMgr object.
 * <p/>
 * The default implementation class is specified as {@link GlobalIds#DELEGATED_ACCESS_DEFAULT_CLASS} but can be overridden by
 * adding the {@link GlobalIds#DELEGATED_ACCESS_IMPLEMENTATION} config property.
 * <p/>

 *
 * @author Shawn McKinney
 * @created December 3, 2010
 */
public class DelAccessMgrFactory
{
    private static String accessClassName = Config.getProperty(GlobalIds.DELEGATED_ACCESS_IMPLEMENTATION);
    private static final String CLS_NM = DelAccessMgrFactory.class.getName();

    /**
     * Create and return a reference to {@link DelAccessMgr} object.
     *
     * @return instance of {@link DelAccessMgr}.
     * @throws SecurityException in the event of failure during instantiation.
     */
    public static DelAccessMgr createInstance(String contextId)
        throws SecurityException
    {
        VUtil.assertNotNull(contextId, GlobalErrIds.CONTEXT_NULL, CLS_NM + ".createInstance");
        DelAccessMgr accessMgr;
        if (accessClassName == null || accessClassName.compareTo("") == 0)
        {
            accessClassName = GlobalIds.DELEGATED_ACCESS_DEFAULT_CLASS;
        }
        accessMgr = (DelAccessMgr) ClassUtil.createInstance(accessClassName);
        accessMgr.setContextId(contextId);
        return accessMgr;
    }
}