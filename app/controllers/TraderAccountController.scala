package controllers

import models.TraderDetailsForm
import play.api.mvc.Controller
import services.TraderAccountService

/**
  * Created by Nick Karaolis on 21/11/16.
  */

trait TraderAccountController extends Controller {
  val traderAccountService: TraderAccountService

  def displayDetails: Unit = {
    Ok(views.html.traderDetails(traderAccountService.retrieveTraderDetails))

  }

  def updateDetails: Unit = {
    traderAccountService.updateTraderDetails(traderAccountService.retrieveTraderDetails)
    TraderDetailsForm.traderDetailsForm.hasErrors
  }
}

object TraderAccountController extends TraderAccountController {
  val traderAccountService = new TraderAccountService
}
