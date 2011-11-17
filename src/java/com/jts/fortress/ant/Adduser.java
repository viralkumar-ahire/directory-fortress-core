/*
 * Copyright (c) 2009-2011. Joshua Tree Software, LLC.  All Rights Reserved.
 */

package com.jts.fortress.ant;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is used by {@link FortressAntTask} to load {@link UserAnt}s used to drive {@link com.jts.fortress.AdminMgr#addUser(com.jts.fortress.rbac.User)}.
 * It is not intended to be callable by programs outside of the Ant load utility.  The class name itself maps to the xml tag used by load utility.
 * <p>This class name, 'Adduser', is used for the xml tag in the load script.</p>
 * <pre>
 * {@code
 * <target name="all">
 *     <FortressAdmin>
 *         <adduser>
 *           ...
 *         </adduser>
 *     </FortressAdmin>
 * </target>
 * }
 * </pre>
 *
 * @author smckinn
 * @created November 23, 2010
 */
public class Adduser
{
    final private List<UserAnt> users = new ArrayList<UserAnt>();

    /**
     * All Ant data entities must have a default constructor.
     */
    public Adduser()
    {
    }

    /**
     * <p>This method name, 'addUser', is used for derived xml tag 'user' in the load script.</p>
     * <pre>
     * {@code
     * <adduser>
     *     <!-- Bad User end time -->
     *     <user userId="demoUser1" password="password" description="Demo Test User 1" ou="demousrs1" cn="JoeUser1" sn="User1" pwPolicy="Test1" beginTime="0000" endTime="0800" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="1234567" timeout="60"/>
     *     <!-- Bad User day mask -->
     *     <user userId="demoUser3" password="password" description="Demo Test User 3" ou="demousrs1" cn="JoeUser3" sn="User3"  pwPolicy="Test1" beginTime="0000" endTime="0000" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="17" timeout="60"/>
     *     <!-- This one is good -->
     *     <user userId="demoUser4" password="password" description="Demo Test User 4" ou="demousrs1" cn="JoeUser4" sn="User4"  pwPolicy="Test1" beginTime="0000" endTime="0000" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="1234567" timeout="60"/>
     *     <!-- Bad User end time -->
     *     <user userId="demoUser5" password="password" description="Demo Test User 5" ou="demousrs1" cn="JoeUser5" sn="User5"  pwPolicy="Test1" beginTime="0000" endTime="0000" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="1234567" timeout="60"/>
     *     <!-- Bad User begin date -->
     *     <user userId="demoUser7" password="password" description="Demo Test User 7" ou="demousrs1" cn="JoeUser7" sn="User7"  pwPolicy="Test1" beginTime="0000" endTime="0000" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="1234567" timeout="60"/>
     *     <!-- Bad User end date -->
     *     <user userId="demoUser9" password="password" description="Demo Test User 9" ou="demousrs1" cn="JoeUser9" sn="User9"  pwPolicy="Test1" beginTime="0000" endTime="0000" beginDate="20090101" endDate="20990101" beginLockDate="none" endLockDate="none" dayMask="1234567" timeout="60"/>
     * </adduser>
     * }
     * </pre>
     *
     * @param user contains reference to data element targeted for insertion..
     */
    public void addUser(UserAnt user)
    {
        this.users.add(user);
    }

    /**
     * Used by {@link FortressAntTask#addUsers()} to retrieve list of Users as defined in input xml file.
     *
     * @return collection containing {@link UserAnt}s targeted for insertion.
     */
    public List<UserAnt> getUsers()
    {
        return this.users;
    }
}

