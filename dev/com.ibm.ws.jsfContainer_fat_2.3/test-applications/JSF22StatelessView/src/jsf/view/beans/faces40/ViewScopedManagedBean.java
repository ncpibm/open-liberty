/*******************************************************************************
 * Copyright (c) 2018, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package jsf.view.beans.faces40;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("viewScopedManagedBean")
@ViewScoped
public class ViewScopedManagedBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private Timestamp timestamp;

    public ViewScopedManagedBean() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}