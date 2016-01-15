package tech.oleks.atgviz.model

/**
 * Created by alexm on 8/22/15.
 */
public class RepositoryConfiguration {
    String name
    String definitionPath
    List<String> suppressedDescriptors
    Map<String, List<String>> suppressedProperties
    Map<String, ItemTypeReference> manualReferences
}
