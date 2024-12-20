/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package componenttest.containers;

import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.ImageNameSubstitutor;

import com.ibm.websphere.simplicity.log.Log;

/**
 * Appends the Artifactory registry required for the mirror repositories.
 * Example: wasliberty-aws-docker-remote/docker/library/postgres:17.0-alpine -> [artifactory_registry_url]/wasliberty-aws-docker-remote/docker/library/postgres:17.0-alpine
 */
public class ArtifactoryRegistrySubstitutor extends ImageNameSubstitutor {

    private static final Class<?> c = ArtifactoryRegistrySubstitutor.class;

    @Override
    public DockerImageName apply(DockerImageName original) {
        if (!ArtifactoryRegistry.instance().isArtifactoryAvailable()) {
            throw new RuntimeException("Needed to append Artifactory registry to the docker image name: " + original.asCanonicalNameString()
                                       + System.lineSeparator() + "No Artfiactory registry was available because "
                                       + ArtifactoryRegistry.instance().getSetupException().getMessage());
        }

        if (!original.getRegistry().isEmpty()) {
            //TODO remove this workaround.  We should be pulling these from an artifactory mirror
            // but thus far we have never run into any pull rate limits or slow speeds with these registries
            // so the mirrors have not been set up yet.
            if (original.getRegistry().equalsIgnoreCase("icr.io") || original.getRegistry().equalsIgnoreCase("mcr.microsoft.com")) {
                Log.warning(c, "The registry (" + original.getRegistry() + ") was configured on the docker image name."
                               + System.lineSeparator() + "This registry will NOT be replaced and we WILL pull from a non-artifactory registry.");
                return original;
            }

            throw new RuntimeException("A registry (" + original.getRegistry() + ") was already configured on the docker image name."
                                       + System.lineSeparator() + "This substitutor cannot replace an existing registry.");
        }

        DockerImageName result = original;
        result = result.withRegistry(ArtifactoryRegistry.instance().getRegistry());

        Log.finer(c, "apply", original.asCanonicalNameString() + " --> " + result.asCanonicalNameString());
        return result;
    }

    @Override
    protected String getDescription() {
        return "Appends the Artifactory registry required for the mirror repositories.";
    }

}
