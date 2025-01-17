/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
package com.ibm.ws.jaxws.injection;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import com.ibm.websphere.ras.annotation.Sensitive;

public class WebServiceContextObjectFactory implements ObjectFactory {

    /** {@inheritDoc} */
    @Override
    public Object getObjectInstance(Object o, Name n, Context c, @Sensitive Hashtable<?, ?> envmt) throws Exception {
        return new WebServiceContextWrapper();
    }

}
