package models

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Nick Karaolis on 15/11/16.
  */
class CurrencyModelSpec extends WordSpec with Matchers {

  "All currencies" should {
    "be covered by match statement" in {
      matchCurrency(new Druggat) shouldBe "Druggat"
      matchCurrency(new NovaCrystal) shouldBe "Nova Crystal"
      matchCurrency(new RepublicCredit) shouldBe "Republic Credit"
      matchCurrency(new Wupiupi) shouldBe "Wupiupi"
    }
  }

  def matchCurrency(currency: Currency): String = currency match {
    case currency: Druggat => currency.name
    case currency: NovaCrystal => currency.name
    case currency: RepublicCredit => currency.name
    case currency: Wupiupi => currency.name
  }
}
