package tech.oleks.atgviz

import tech.oleks.atgviz.model.ItemTypeReference
import tech.oleks.atgviz.model.RepositoryConfiguration

/**
 * Created by alexm on 8/22/15.
 */
class Configuration {
    def static repos = [
            new RepositoryConfiguration(
                    name: "/atg/userprofiling/ProfileAdapterRepository",
                    definitionPath: "resource/userProfile.xml",
                    suppressedDescriptors: ["registrationBanner", "optextend", "scenarioXref", "scenarioDeletion", "individualScenarioTransition",
                    "scenarioTemplateInfo", "scenarioMigration", "scenarioMigrationInfo", "scenarioInfo", "collectiveScenarioTransition",
                    "collectiveScenario", "mailServer", "mailBatch", "organization", "roleFolder", "genericFolder", "role", "organizationalRole",
                    "businessProcessMarker", "couponPromotionLayaway", "mailing", "individualScenario", "profileSlot", "marker", "abandoned-order",
                    "gift-list"],
                    suppressedProperties: ["user":["linkedUsers", "giftlistAddresses", "homeAddress"]],
                    manualReferences: [:]
            ),
            new RepositoryConfiguration(
                    name: "/atg/commerce/order/OrderRepository",
                    definitionPath: "resource/orderRepository.xml",
                    suppressedDescriptors: ["marker", "commerceItemMarker"],
                    suppressedProperties: [:],
                    manualReferences: ["userId": new ItemTypeReference(repository:"/atg/userprofiling/ProfileAdapterRepository", itemDescriptor: "user"),
                                       "profileId": new ItemTypeReference(repository:"/atg/userprofiling/ProfileAdapterRepository", itemDescriptor: "user"),
                                       "orderId": new ItemTypeReference(repository:"/atg/commerce/order/OrderRepository", itemDescriptor: "order"),
                                       "order": new ItemTypeReference(repository:"/atg/commerce/order/OrderRepository", itemDescriptor: "order")
                    ]
            ),
            new RepositoryConfiguration(
                    name: "/atg/commerce/catalog/ProductCatalog",
                    definitionPath: "resource/productCatalog.xml",
                    suppressedDescriptors: [],
                    suppressedProperties: [:],
                    manualReferences: [:]
            ),
            new RepositoryConfiguration(
                    name: "/atg/commerce/inventory/InventoryRepository",
                    definitionPath: "resource/inventoryRepository.xml",
                    suppressedDescriptors: [],
                    suppressedProperties: [:],
                    manualReferences: ["skuId": new ItemTypeReference(repository:"/atg/commerce/catalog/ProductCatalog", itemDescriptor: "sku"),
                                       "catalogRefId": new ItemTypeReference(repository:"/atg/commerce/catalog/ProductCatalog", itemDescriptor: "sku")
                    ]
            ),
            new RepositoryConfiguration(
                    name: "/atg/commerce/pricing/priceLists/PriceLists",
                    definitionPath: "resource/priceLists.xml",
                    suppressedDescriptors: [],
                    suppressedProperties: [:],
                    manualReferences: [:]
            ),
            new RepositoryConfiguration(
                    name: "/atg/commerce/gifts/Giftlists",
                    definitionPath: "resource/giftLists.xml",
                    suppressedDescriptors: [],
                    suppressedProperties: [:],
                    manualReferences: [:]
            )
    ]

///atg/commerce/pricing/priceLists/PriceLists
}
