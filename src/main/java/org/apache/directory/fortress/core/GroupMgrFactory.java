/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.fortress.core;

import org.apache.directory.api.util.Strings;
import org.apache.directory.fortress.core.impl.GroupMgrImpl;
import org.apache.directory.fortress.core.model.Session;
import org.apache.directory.fortress.core.util.ClassUtil;
import org.apache.directory.fortress.core.util.Config;
import org.apache.directory.fortress.core.util.VUtil;

/**
 * Creates an instance of the ConfigMgr object.
 * <p>
 * The default implementation class is specified as {@link org.apache.directory.fortress.core.impl.GroupMgrImpl} but can be 
 * overridden by adding the {@link org.apache.directory.fortress.core.GlobalIds#GROUP_IMPLEMENTATION} config property.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public final class GroupMgrFactory
{    
    private static final String CLS_NM = GroupMgrFactory.class.getName();
    private static final String CREATE_INSTANCE_METHOD = CLS_NM + ".createInstance";

    /**
     * Prevent instantiation.
     */
    private GroupMgrFactory()
    {
    }

    /**
     * Create and return a reference to {@link org.apache.directory.fortress.core.GroupMgr} object using HOME context.
     *
     * @return instance of {@link org.apache.directory.fortress.core.AdminMgr}.
     * @throws org.apache.directory.fortress.core.SecurityException in the event of failure during instantiation.
     */
    public static GroupMgr createInstance()
        throws SecurityException
    {
        return createInstance( GlobalIds.HOME );
    }

    /**
     * Create and return a reference to {@link GroupMgr} object.
     *
     * @param contextId maps to sub-tree in DIT, for example ou=contextId, dc=jts, dc = com.
     * @return instance of {@link GroupMgr}.
     * @throws org.apache.directory.fortress.core.SecurityException in the event of failure during instantiation.
     */
    public static GroupMgr createInstance(String contextId)
        throws SecurityException
    {
        VUtil.assertNotNull( contextId, GlobalErrIds.CONTEXT_NULL, CREATE_INSTANCE_METHOD );
        String groupClassName = Config.getInstance().getProperty( GlobalIds.GROUP_IMPLEMENTATION );
        
        if ( Strings.isEmpty( groupClassName ) )
        {
            groupClassName = GroupMgrImpl.class.getName();
        }

        GroupMgr groupMgr = (GroupMgr) ClassUtil.createInstance(groupClassName);
        groupMgr.setContextId(contextId);
        
        if(groupMgr instanceof GroupMgrImpl){
        	Config cfg = Config.getInstance();
        	if(!cfg.isRemoteConfigLoaded()){
        		cfg.loadRemoteConfig();
        	}
        }
        
        return groupMgr;
    }


    /**
     * Create and return a reference to {@link GroupMgr} object using HOME context.
     *
     * @param adminSess contains a valid Fortress A/RBAC Session object.
     * @return instance of {@link org.apache.directory.fortress.core.AdminMgr}.
     * @throws SecurityException in the event of failure during instantiation.
     */
    public static GroupMgr createInstance(Session adminSess)
        throws SecurityException
    {
        return createInstance( GlobalIds.HOME, adminSess );
    }

    /**
     * Create and return a reference to {@link GroupMgr} object.
     *
     * @param contextId maps to sub-tree in DIT, for example ou=contextId, dc=jts, dc = com.
     * @param adminSess contains a valid Fortress A/RBAC Session object.
     * @return instance of {@link org.apache.directory.fortress.core.AdminMgr}.
     * @throws SecurityException in the event of failure during instantiation.
     */
    public static GroupMgr createInstance(String contextId, Session adminSess)
        throws SecurityException
    {
        GroupMgr groupMgr = createInstance(contextId);
        groupMgr.setAdmin(adminSess);
        
        return groupMgr;
    }
}
