package com.tpaga.productstopay.presentation.productselect.model.response

import com.squareup.moshi.Json

data class Response(
    @Json(name = "cancelled_at")
    var cancelledAt: Any,
    @Json(name = "checked_by_merchant_at")
    var checkedByMerchantAt: Any,
    @Json(name = "cost")
    var cost: String,
    @Json(name = "delivery_notification_at")
    var deliveryNotificationAt: Any,
    @Json(name = "expires_at")
    var expiresAt: String,
    @Json(name = "idempotency_token")
    var idempotencyToken: String,
    @Json(name = "merchant_user_id")
    var merchantUserId: Any,
    @Json(name = "miniapp_user_token")
    var miniappUserToken: Any,
    @Json(name = "order_id")
    var orderId: String,
    @Json(name = "purchase_description")
    var purchaseDescription: String,
    @Json(name = "purchase_details_url")
    var purchaseDetailsUrl: String,
    @Json(name = "purchase_items")
    var purchaseItems: List<PurchaseItem>,
    @Json(name = "status")
    var status: String,
    @Json(name = "terminal_id")
    var terminalId: String,
    @Json(name = "token")
    var token: String,
    @Json(name = "tpaga_payment_url")
    var tpagaPaymentUrl: String,
    @Json(name = "user_ip_address")
    var userIpAddress: String,
    @Json(name = "voucher_url")
    var voucherUrl: String
)

data class PurchaseItem(
    @Json(name = "name")
    var name: String,
    @Json(name = "value")
    var value: String
)