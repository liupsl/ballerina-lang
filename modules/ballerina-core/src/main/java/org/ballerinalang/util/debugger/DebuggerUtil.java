/*
*  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.ballerinalang.util.debugger;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BLangVM;

/**
 * Contains util methods for debugger.
 *
 * @since 0.95.7
 */
public class DebuggerUtil {
    /**
     * Helper method to run code in debug mode.
     *
     * @param bLangVM   to run the code.
     * @param context   of the code.
     * @param debugger  instance for debug purposes.
     */
    public static void runInDebugMode(BLangVM bLangVM, Context context, Debugger debugger) {
        DebugContext debugContext = new DebugContext();
        debugContext.setCurrentCommand(DebugCommand.RESUME);
        context.setDebugContext(debugContext);
        debugger.addDebugContext(debugContext);
        bLangVM.run(context);
        if (debugContext.isAtive()) {
            debugContext.setActive(false);
            debugger.releaseDebugSessionLock();
        }
    }

}
