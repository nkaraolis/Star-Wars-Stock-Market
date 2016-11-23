package models

/**
  * Created by Nick Karaolis on 15/11/16.
  */
sealed trait Currency {
  val value: BigDecimal
  val name: String
}

case class RepublicCredit() extends Currency {
  override val value = BigDecimal.exact("0")
  override val name = "Republic Credit"
}

case class Wupiupi() extends Currency {
  override val value = BigDecimal.exact("0")
  override val name = "Wupiupi"
}

case class Druggat() extends Currency {
  override val value = BigDecimal.exact("0")
  override val name = "Druggat"
}

case class NovaCrystal() extends Currency {
  override val value = BigDecimal.exact("0")
  override val name = "Nova Crystal"
}
