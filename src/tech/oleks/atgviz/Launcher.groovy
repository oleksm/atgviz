package tech.oleks.atgviz

import tech.oleks.atgviz.util.RepositoryUtil

/**
 * Created by alexm on 8/14/15.
 */
class Launcher {

    static void main(String... args) {
        def repos = RepositoryUtil.loadRepositories()
        println("Loaded repositories: ${repos.keySet()}")

        repos.entrySet().each { e ->
            def out = e.key.replaceAll(".+/", "") + ".dot"
            println ("Generating graph ${out} for ${e.key}")
            new GraphVizGenerator().generate(e.value, out, repos)
        }
   }
}
