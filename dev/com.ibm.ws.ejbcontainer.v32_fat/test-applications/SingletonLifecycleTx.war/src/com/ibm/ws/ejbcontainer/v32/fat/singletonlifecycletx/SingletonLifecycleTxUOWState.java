/*******************************************************************************
 * Copyright (c) 2014, 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.ejbcontainer.v32.fat.singletonlifecycletx;

import com.ibm.wsspi.uow.UOWManager;

public class SingletonLifecycleTxUOWState {
    public final long localId;
    public final int type;

    public SingletonLifecycleTxUOWState(UOWManager uowManager) {
        localId = uowManager.getLocalUOWId();
        type = uowManager.getUOWType();
    }
}
