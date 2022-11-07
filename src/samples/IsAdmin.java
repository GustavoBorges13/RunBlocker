package samples;


import com.sun.jna.platform.win32.Advapi32Util;



// https://github.com/twall/jna#readme

//    you need 2 jars : jna-3.5.1.jar and platform-3.5.1.jar


import com.sun.jna.platform.win32.Advapi32;



import com.sun.jna.platform.win32.WinNT.PSIDByReference;

import com.sun.jna.platform.win32.WinNT.WELL_KNOWN_SID_TYPE;



// https://github.com/twall/jna#readme

//    you need 2 jars : jna-3.5.1.jar and platform-3.5.1.jar



public class IsAdmin {



    public static void main(String[] args) {

        boolean isAdmin = false;

        Advapi32Util.Account[] groups = Advapi32Util.getCurrentUserGroups();

        for (Advapi32Util.Account group : groups) {

           PSIDByReference sid = new PSIDByReference();

           Advapi32.INSTANCE.ConvertStringSidToSid(group.sidString, sid);

           if (Advapi32.INSTANCE.IsWellKnownSid(sid.getValue(),

                  WELL_KNOWN_SID_TYPE.WinBuiltinAdministratorsSid)) {

              isAdmin = true;

              break;

           }

        }

        if (isAdmin){

           System.out.println("Current User is ADMIN");

        }

        else{

           System.out.println("Current User is not ADMIN");

        }

    }

}