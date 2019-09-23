package tdt.sling.groovy.operations

import org.apache.sling.api.resource.Resource
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ResourceResolverFactory

ResourceResolverFactory resourceResolverFactory = osgi.getService(ResourceResolverFactory.class)
ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(null)


def count = 100
def destinationPath = '/content/tdt/site'
Resource destinationResource = resourceResolver.getResource(destinationPath)
for (int i = 0; i < count; i++) {
    resourceResolver.create(destinationResource, "${i}-tdt-sample", ['jcr:primaryType': 'nt:unstructured'])
}
resourceResolver.commit()