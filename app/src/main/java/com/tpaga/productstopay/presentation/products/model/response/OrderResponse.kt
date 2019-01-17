package com.tpaga.productstopay.presentation.products.model.response

import com.squareup.moshi.Json

data class OrderEntity(
    @field:Json(name = "cancelled_at")
    var cancelledAt: Any,
    @field:Json(name = "checked_by_merchant_at")
    var checkedByMerchantAt: Any,
    @field:Json(name = "cost")
    var cost: String,
    @field:Json(name = "delivery_notification_at")
    var deliveryNotificationAt: Any,
    @field:Json(name = "expires_at")
    var expiresAt: String,
    @field:Json(name = "idempotency_token")
    var idempotencyToken: String,
    @field:Json(name = "merchant_user_id")
    var merchantUserId: Any,
    @field:Json(name = "miniapp_user_token")
    var miniappUserToken: Any,
    @field:Json(name = "order_id")
    var orderId: String,
    @field:Json(name = "purchase_description")
    var purchaseDescription: String,
    @field:Json(name = "purchase_details_url")
    var purchaseDetailsUrl: String,
    @field:Json(name = "purchase_items")
    var purchaseItems: List<PurchaseItem>,
    @field:Json(name = "status")
    var status: String,
    @field:Json(name = "terminal_id")
    var terminalId: String,
    @field:Json(name = "token")
    var token: String,
    @field:Json(name = "tpaga_payment_url")
    var tpagaPaymentUrl: String,
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