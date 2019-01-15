package com.tpaga.productstopay.presentation.productselect.model

import com.squareup.moshi.Json


data class ProductEntity(
    @field:Json(name = "cost")
    var cost: String,
    @field:Json(name = "expires_at")
    var expiresAt: String,
    @field:Json(name = "idempotency_token")
    var idempotencyToken: String,
    @field:Json(name = "order_id")
    var orderId: String,
    @field:Json(name = "purchase_description")
    var purchaseDescription: String,
    @field:Json(name = "purchase_details_url")
    var purchaseDetailsUrl: String,
    @field:Json(name = "purchase_items")
    var purchaseItems: List<PurchaseItem>,
    @field:Json(name = "terminal_id")
    var terminalId: String,
    @field:Json(name = "user_ip_address")
    var userIpAddress: String,
    @field:Json(name = "voucher_url")
    var voucherUrl: String
)

data class PurchaseItem(
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "value")
    var value: String
)