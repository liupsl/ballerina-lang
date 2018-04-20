/*
 *  Copyright (c) 2018 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package io.ballerina.transactions;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BlockingNativeCallableUnit;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;
import org.ballerinalang.util.transactions.LocalTransactionInfo;

/**
 * Native function ballerina.transactions:GetCurrentTransactionId.
 *
 * @since 0.970.0
 */
@BallerinaFunction(
        orgName = "ballerina", packageName = "transactions",
        functionName = "getCurrentTransactionId",
        returnType = {@ReturnType(type = TypeKind.STRING)}
)
public class GetCurrentTransactionId extends BlockingNativeCallableUnit {

    public void execute(Context ctx) {
        String currentTransactionId = "";
        LocalTransactionInfo localTransactionInfo = ctx.getLocalTransactionInfo();
        if (localTransactionInfo != null) {
            currentTransactionId = localTransactionInfo.getGlobalTransactionId() + ":" + localTransactionInfo
                    .getCurrentTransactionBlockId();
        }
        ctx.setReturnValues(new BString(currentTransactionId));
    }
}