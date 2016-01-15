package tech.oleks.atgviz.model;

import tech.oleks.atgviz.model.gsa.GsaTemplate;

import java.util.Map;
import java.util.Set;

/**
 * Created by alexm on 8/22/15.
 */
public class GraphGenerationContext {
    private Map<String, GsaTemplate> repositories;
    StringBuilder edges;
    Set<String> extDescriptors;
    RepositoryConfiguration configuration;


    public Map<String, GsaTemplate> getRepositories() {
        return repositories;
    }

    public void setRepositories(Map<String, GsaTemplate> repositories) {
        this.repositories = repositories;
    }

    public StringBuilder getEdges() {
        return edges;
    }

    public void setEdges(StringBuilder edges) {
        this.edges = edges;
    }

    public Set<String> getExtDescriptors() {
        return extDescriptors;
    }

    public void setExtDescriptors(Set<String> extDescriptors) {
        this.extDescriptors = extDescriptors;
    }

    public RepositoryConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(RepositoryConfiguration configuration) {
        this.configuration = configuration;
    }
}
