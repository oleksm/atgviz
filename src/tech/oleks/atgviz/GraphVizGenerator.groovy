package tech.oleks.atgviz

import tech.oleks.atgviz.model.GraphGenerationContext
import tech.oleks.atgviz.model.gsa.GsaTemplate
import tech.oleks.atgviz.model.gsa.GsaTemplate.ItemDescriptor
import tech.oleks.atgviz.model.gsa.Property
import org.apache.commons.io.FileUtils

/**
 * Created by alexm on 8/21/15.
 */
class GraphVizGenerator {
    def generate(GsaTemplate t, def path, Map<String, GsaTemplate> rp) {

        def ctx = new GraphGenerationContext(repositories: rp, edges: new StringBuilder(), extDescriptors: new HashSet<String>(),
            configuration: Configuration.repos.find {c-> c.name == (rp.find{r -> r.value == t}.key) })
        def b = new StringBuilder()
            .append("digraph g {")
            .append('\n').append("graph [label=\"${ctx.configuration.name}\" fontsize=30 labelloc=\"t\" splines=true overlap=false ranksep=3 rankdir = \"LR\"];")
        // Nodes
        t.itemDescriptor.each {d ->
            if (!ctx.configuration.suppressedDescriptors.contains(d.name)) {
                b.append('\n').append("\"${d.name}\" [fontname=\"Lucida Console\" label = \"${itemDesc(d, t, ctx)}\" shape = \"record\"];")
            }
        }
        // External Item Descriptors
        ctx.extDescriptors.each {d ->
            b.append('\n').append("\"${d}\" [fontname=\"Lucida Console\" color=\"gray\" label = \"<f0>${d}\" shape = \"record\"];")
        }

        // Edges
        b.append(ctx.edges)

        b.append('\n').append("}")
        FileUtils.write(new File(path), b)

    }

    def itemDesc(ItemDescriptor d, GsaTemplate r, GraphGenerationContext ctx) {

        def suppressedProperties = ctx.configuration.suppressedProperties[d.name]

        // build and sort all properties map
        def pmap = new LinkedHashMap<Property, ItemDescriptor.Table>()

        // Persisted properties
        d.table.each { t ->
            t.property.sort {p1, p2 -> p1.name.compareToIgnoreCase(p2.name)}.each { p ->
                if (!suppressedProperties?.contains(p.name)) pmap.put(p, t)
            }
        }

        // Transient properties
        d.property.sort{p1, p2 -> p1.name.compareToIgnoreCase(p2.name)}.each { p ->
            if (!suppressedProperties?.contains(p.name)) pmap.put(p, null)
        }

        // Derivation
        def b = new StringBuilder("<f0>${d.name}")
        if (d.superType) {
            ctx.edges.append("\n\"${d.name}\":f0 -> \"${d.superType}\":f0 [splines=\"\" color=\"green\"]")

        }
        pmap.entrySet().eachWithIndex {e, i ->
            def p = e.key
            def t = e.value
            b.append(
                    t? "|{${fmt(p.name, 24)}|<f${i+1}>${fmt(t.name + '.' + p.columnName , 40)}}"
                     : "|<f${i+1}>${fmt(p.name, 70)}");

            // too much of visual dependencies look like a mess
            def refDesc
            def refColor
            def refRepo = p.repository
            if (p.itemType) {
                refDesc = p.itemType
                refColor = "black"
            }
            else if (p.componentItemType) {
                refDesc = p.componentItemType
                refColor = "orange"
            }
            // try to take it from manual references config
            else {
                def mref = ctx.configuration.manualReferences[p.name]
                if (mref) {
                    refDesc = mref.itemDescriptor
                    refRepo = mref.repository
                    refColor = "gray"
                }
            }

            if (refDesc && !ctx.configuration.suppressedDescriptors.contains(refDesc)) {
                def ext = false
                if (refRepo) {
                    def rp = ctx.repositories[refRepo]
                    // assuming there will be same reference
                    if (!rp || rp != r) {
                        refColor = "blue"
                        ctx.extDescriptors.add(refDesc)
                    }
                }
                ctx.edges.append("\n\"${d.name}\":f${i+1} -> \"${refDesc}\":f0 [splines=\"\" color=\"${refColor}\"]")
            }
        }
        b
    }

    def fmt(v, def l, def rt = false) {
        StringBuilder b = new StringBuilder()
        if (v) {
            l -= String.valueOf(v).length()
            if (!rt) {
                b.append(v)
            }
        }
        for (int i = 0; i < l; ++i) {
            b.append("\\ ")
        }
        if (rt && v) {
            b.append(v)
        }
        b
    }
}
