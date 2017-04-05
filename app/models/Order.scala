package models

import java.time.{LocalDateTime => LDT}
import java.time.LocalDateTime.now

/**
  * Created by Nick Karaolis on 05/04/17.
  */
case class Order(orderID: Int = 0, traderID: Int,
                 resources: Seq[Resource], currencies: Seq[Resource], orderTotal: BigDecimal,
                 orderDateTime: LDT = LDT.parse(now.format(dateTimeFormat)))