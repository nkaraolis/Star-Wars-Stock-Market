package controllers

import javax.inject.{Inject, Singleton}

import models.TraderDetailsForm
import play.api.mvc.{Action, Controller}
import services.TraderAccountService
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject() extends Controller {
  val traderAccountService = new TraderAccountService

  def displayDetails = Action {
    Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
  }

  def updateDetails: Unit = {
    traderAccountService.updateTraderDetails(traderAccountService.retrieveTraderDetails)
    TraderDetailsForm.traderDetailsForm.hasErrors
  }

  def registration = Action {
    implicit request =>
      val form = if (request2flash.get("error").isDefined)
        TraderDetailsForm.traderDetailsForm.bind(request2flash.data)
      else
        TraderDetailsForm.traderDetailsForm
      Ok(views.html.user.information.registration(form))
  }
}
