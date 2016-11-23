package models

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Nick Karaolis on 17/11/16.
  */
class ResourceModelSpec extends WordSpec with Matchers {

  "All resources" should {
    "be covered by match statement" in {
      matchCurrency(new Alum) shouldBe "Alum"
      matchCurrency(new Chromium) shouldBe "Chromium"
      matchCurrency(new Coal) shouldBe "Coal"
      matchCurrency(new Exonium) shouldBe "Exonium"
      matchCurrency(new Ionite) shouldBe "Ionite"
      matchCurrency(new LightsaberCrystal) shouldBe "Lightsaber Crystal"
      matchCurrency(new Ore) shouldBe "Ore"
      matchCurrency(new Spice) shouldBe "Spice"
      matchCurrency(new Stygium) shouldBe "Stygium"
      matchCurrency(new Thorilide) shouldBe "Thorilide"
    }
  }

  def matchCurrency(resource: Resource): String = resource match {
    case resource: Alum => resource.name
    case resource: Chromium => resource.name
    case resource: Coal => resource.name
    case resource: Exonium => resource.name
    case resource: Ionite => resource.name
    case resource: LightsaberCrystal => resource.name
    case resource: Ore => resource.name
    case resource: Spice => resource.name
    case resource: Stygium => resource.name
    case resource: Thorilide => resource.name
  }
}
