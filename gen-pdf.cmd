#!/bin/bash
dot -Tpdf ProfileAdapterRepository.dot -o ProfileAdapterRepository.pdf
dot -Tpdf OrderRepository.dot -o OrderRepository.pdf
dot -Tpdf ProductCatalog.dot -o ProductCatalog.pdf
dot -Tpdf InventoryRepository.dot -o InventoryRepository.pdf
dot -Tpdf PriceLists.dot -o PriceLists.pdf
dot -Tpdf Giftlists.dot -o Giftlists.pdf
