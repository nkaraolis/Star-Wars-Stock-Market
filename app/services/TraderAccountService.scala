package services

import javax.inject.{Inject, Singleton}

import models.Trader

/**
  * Created by Nick Karaolis on 21/11/16.
  */
@Singleton
class TraderAccountService @Inject()(val mongoConnection: MongoConnection) {

  def retrieveTraderDetails: Trader = {
    Trader(0, "first", "last", "email", "user", "pass")
  }

  def updateTraderDetails(trader: Trader): Unit = {

  }

}
