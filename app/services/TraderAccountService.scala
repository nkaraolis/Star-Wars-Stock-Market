package services

import models.Trader

/**
  * Created by Nick Karaolis on 21/11/16.
  */
class TraderAccountService {

  def retrieveTraderDetails: Trader = {
    Trader("first", "last", "user", List.empty, List.empty)
  }

  def updateTraderDetails(trader: Trader): Unit = {

  }

}
