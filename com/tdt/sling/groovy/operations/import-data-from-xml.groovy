package tdt.sling.groovy.operations

import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ResourceResolverFactory

import javax.jcr.ImportUUIDBehavior
import javax.jcr.Session


Closure print = {message ->
    out.println(message.toString())
}


print('Script Started....')
ResourceResolverFactory resourceResolverFactory = osgi.getService(ResourceResolverFactory.class)
ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(null)
Session session = resourceResolver.adaptTo(Session)

def importXML = '<?xml version="1.0" encoding="UTF-8"?>\n' +
        '<sample-company xmlns:jcr="http://www.jcp.org/jcr/1.0" xmlns:nt="http://www.jcp.org/jcr/nt/1.0" xmlns:sling="http://www.sling.apache.org/sling/1.0" name="TheDigitalTent" jcr:primaryType="nt:unstructured" sling:resourceType="/apps/tdt">' +
            '<company-details phone="12313123123" email="random@gmail.com" jcr:primaryType="nt:unstructured" />'+
        '</sample-company>'
InputStream inputStream = new ByteArrayInputStream(importXML.getBytes())
session.importXML('/content/tdt/import', inputStream, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW)
session.save()
print('Script Finished!')