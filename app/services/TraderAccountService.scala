package services

import models.Trader

/**
  * Created by Nick Karaolis on 21/11/16.
  */
class TraderAccountService {

  def retrieveTraderDetails: Trader = {
    Trader(0,"first", "last", "user", Seq.empty, Seq.empty)
  }

  def updateTraderDetails(trader: Trader): Unit = {

  }

}
