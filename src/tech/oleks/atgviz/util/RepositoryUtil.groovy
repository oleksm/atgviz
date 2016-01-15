package tech.oleks.atgviz.util

import tech.oleks.atgviz.Configuration
import tech.oleks.atgviz.model.gsa.GsaTemplate
import org.xml.sax.InputSource
import org.xml.sax.XMLReader

import javax.xml.bind.JAXBContext
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.sax.SAXSource

/**
 * Created by alexm on 8/14/15.
 */
class RepositoryUtil {
    static GsaTemplate loadDefinitionFile(def path) {
        def context = JAXBContext.newInstance(GsaTemplate.class)

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        spf.setFeature("http://xml.org/sax/features/validation", false);

        def parser = spf.newSAXParser()
        XMLReader reader = parser.getXMLReader()
        InputSource inputSource = new InputSource(new FileReader(path));
        def source = new SAXSource(reader, inputSource);

        def um = context.createUnmarshaller();
        um.unmarshal(source)
    }

    static Map<String, GsaTemplate> loadRepositories() {
        def repos = [:]
        Configuration.repos.each { c ->
            repos.put(c.name, loadDefinitionFile(c.definitionPath))
        }
        repos
    }
}
