package controllers

import javax.inject.{Inject, Singleton}

import models.{LoginForm, TraderDetailsForm}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.TraderAccountService

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val traderAccountService = new TraderAccountService

  def displayDetails = Action {
    Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
  }

  def submitUserDetailsUpdate = TODO

  def registration = Action {
    implicit request =>
      val form = if (request2flash.get("error").isDefined)
        TraderDetailsForm.traderDetailsForm.bind(request2flash.data)
      else
        TraderDetailsForm.traderDetailsForm
      Ok(views.html.user.information.registration(form))
  }

  def submitRegistration = TODO

  def login = Action {
    implicit request =>
      val form = if (request2flash.get("error").isDefined)
        LoginForm.loginForm.bind(request2flash.data)
      else
        LoginForm.loginForm
      Ok(views.html.user.information.login(form))
  }

  def submitLogin = TODO
}
