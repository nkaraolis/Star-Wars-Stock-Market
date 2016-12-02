package models

/**
  * Created by Nick Karaolis on 15/11/16.
  */
sealed abstract class Resource {
  val value: BigDecimal
  val name: String
}

case class Alum() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Alum"
}

case class Chromium() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Chromium"
}

case class Coal() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Coal"
}

case class Exonium() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Exonium"
}

case class Ionite() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Ionite"
}

case class LightsaberCrystal() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Lightsaber Crystal"
}

case class Ore() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Ore"
}

case class Spice() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Spice"
}

case class Stygium() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Stygium"
}

case class Thorilide() extends Resource {
  override val value = BigDecimal.exact("0")
  override val name = "Thorilide"
}
