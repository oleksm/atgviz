#!/bin/bash
dot -Tsvg ProfileAdapterRepository.dot -o ProfileAdapterRepository.svg
dot -Tsvg OrderRepository.dot -o OrderRepository.svg
dot -Tsvg ProductCatalog.dot -o ProductCatalog.svg
dot -Tsvg InventoryRepository.dot -o InventoryRepository.svg
dot -Tsvg PriceLists.dot -o PriceLists.svg
dot -Tsvg Giftlists.dot -o Giftlists.svg
